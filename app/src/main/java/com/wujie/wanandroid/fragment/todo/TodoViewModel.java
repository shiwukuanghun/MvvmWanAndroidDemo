package com.wujie.wanandroid.fragment.todo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wujie.wanandroid.bean.PageListDataBean;
import com.wujie.wanandroid.bean.TodoBean;
import com.wujie.wanandroid.net.BaseObserver;
import com.wujie.wanandroid.net.RxHelper;
import com.wujie.wanandroid.net.RxRetrofit;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/8/6 15:57
 * @Description：
 **/
public class TodoViewModel extends ViewModel {
    private static final String TAG = "TodoViewModel";
    private MutableLiveData<Integer> mType;
    private MutableLiveData<Integer> mPriority;
    private int mPage;
    private MutableLiveData<List<TodoBean>> mDataList;
    private boolean mIsRefresh;

    public TodoViewModel() {
        mType = new MutableLiveData<>(0); // 要设置默认值，不然直接getValue会空指针
        mPriority = new MutableLiveData<>(0);
        mDataList = new MutableLiveData<>();
    }

    public void setType(int type) {
        mType.setValue(type);
        loadTodoList();
    }

    public void setPriority(int priority) {
        mPriority.setValue(priority);
        loadTodoList();
    }

    public MutableLiveData<List<TodoBean>> getDataList() {
        return mDataList;
    }

    public boolean isRefresh() {
        return mIsRefresh;
    }

    public void refreshTodoData(boolean isRefresh) {
        if (isRefresh) {
            mPage = 1;
            mIsRefresh = true;
        } else {
            mPage++;
            mIsRefresh = false;
        }
        loadData();
    }

    public void loadTodoList() {
        mPage = 1;
        mIsRefresh = true;
        loadData();
    }

    private void loadData() {
        RxRetrofit.getApi()
                .getTodoList(mPage, 0, mType.getValue(), mPriority.getValue(), 0)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<PageListDataBean<TodoBean>>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(PageListDataBean<TodoBean> todoBeanPageListDataBean) {
                        mDataList.postValue(todoBeanPageListDataBean.getDatas());
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                });
    }
}
