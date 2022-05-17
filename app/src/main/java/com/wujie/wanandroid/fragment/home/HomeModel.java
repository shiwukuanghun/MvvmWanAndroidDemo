package com.wujie.wanandroid.fragment.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wujie.wanandroid.bean.HomeBean;
import com.wujie.wanandroid.bean.PageListDataBean;
import com.wujie.wanandroid.net.BaseObserver;
import com.wujie.wanandroid.net.RxHelper;
import com.wujie.wanandroid.net.RxRetrofit;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/26 21:20
 * @Description：
 **/
public class HomeModel extends ViewModel {
    private MutableLiveData<List<HomeBean>> mHomeList;

    public HomeModel() {
        this.mHomeList = new MutableLiveData<>();
    }

    public MutableLiveData<List<HomeBean>> getHomeList() {
        return mHomeList;
    }

    public void getHomeBeanList() {
        RxRetrofit.getApi().getHomeList(1)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<PageListDataBean<HomeBean>>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(PageListDataBean<HomeBean> homeBeanPageListDataBean) {
                        List<HomeBean> homeBeanList = homeBeanPageListDataBean.getDatas();
                        if (homeBeanList != null && !homeBeanList.isEmpty()) {
                            mHomeList.setValue(homeBeanList);
                        }
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                });
    }
}
