package com.wujie.wanandroid.fragment.todo;

import android.app.DatePickerDialog;
import android.util.Log;
import android.widget.DatePicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wujie.wanandroid.bean.TodoBean;
import com.wujie.wanandroid.manager.MyActivityManager;
import com.wujie.wanandroid.net.BaseObserver;
import com.wujie.wanandroid.net.RxHelper;
import com.wujie.wanandroid.net.RxRetrofit;
import com.wujie.wanandroid.utils.TimeUtils;

import java.util.Calendar;

/**
 * @Author：created by WuChen
 * @Time：2022/7/31 21:22
 * @Description：
 **/
public class AddTodoViewModel extends ViewModel {
    private static final String TAG = "AddTodoViewModel";
    private DatePickerDialog mDateDialog;
    private int mYear;
    private int mMonthOfYear;
    private int mDayOfMonth;
    private MutableLiveData<TodoBean> mAddData;

    /**
     * 标题内容
     */
    public MutableLiveData<String> mTitle;
    public MutableLiveData<String> mContent;

    /**
     * 计划完成时间
     */
    public MutableLiveData<String> date;


    /**
     * 分类
     */
    public MutableLiveData<Integer> mType;

    /**
     * 级别
     */
    public MutableLiveData<Integer> mPriority;

    public AddTodoViewModel() {
        mAddData = new MutableLiveData<>();
        mTitle = new MutableLiveData<>();
        mContent = new MutableLiveData<>();
        date = new MutableLiveData<>(TimeUtils.getNowDateString());
        mType = new MutableLiveData<>(4);
        mPriority = new MutableLiveData<>(0);
        initDatePicker();
    }

    public LiveData<TodoBean> getAdddata() {
        return mAddData;
    }

    private void initDatePicker() {
        // 通过Calendar对象来获取年、月、日、时、分的信息
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
        mYear = calendar.get(Calendar.YEAR);
        mMonthOfYear = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mDateDialog = new DatePickerDialog(MyActivityManager.getInstance().getCurrentActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
                String text = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                Log.d(TAG, "onDateSet: date = "  + text);
                // 因为没写binding.setLifecycleOwner(this);导致这里不生效
                date.postValue(text);
            }
        }, mYear, mMonthOfYear, mDayOfMonth);
    }

    public void changeToDoPriority() {
        if (mPriority.getValue() == 0) {
            mPriority.setValue(1);
        } else {
            mPriority.postValue(0);
        }
    }

    /**
     * 改变优先级
     */
    public void changeDoneDate() {
        mDateDialog.show();
    }

    public void addData() {
        RxRetrofit.getApi()
                .addTodo(mTitle.getValue(), mContent.getValue(), date.getValue(), mType.getValue(), mPriority.getValue())
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<TodoBean>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(TodoBean todoBean) {
                        mAddData.postValue(todoBean);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                });

    }
}
