package com.wujie.wanandroid.activity;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wujie.wanandroid.bean.UserInfo;
import com.wujie.wanandroid.net.BaseObserver;
import com.wujie.wanandroid.net.RxHelper;
import com.wujie.wanandroid.net.RxRetrofit;
import com.wujie.wanandroid.utils.ContextUtil;

/**
 * @Author：created by WuChen
 * @Time：2022/2/26 12:38
 * @Description：
 **/
public class LoginModel extends ViewModel {
    private MutableLiveData<String> mUsername;
    private MutableLiveData<String> mPassword;
    private MutableLiveData<UserInfo> mUserInfo;

    public LoginModel() {
        mUsername = new MutableLiveData<>();
        mPassword = new MutableLiveData<>();
        mUserInfo = new MutableLiveData<>();
    }

    public MutableLiveData<String> getUsername() {
        return mUsername;
    }

    public MutableLiveData<String> getPassword() {
        return mPassword;
    }

    public MutableLiveData<UserInfo> getUserInfo() {
        return mUserInfo;
    }

    public void login() {
        if (TextUtils.isEmpty(mUsername.getValue()) || TextUtils.isEmpty(mPassword.getValue())) {
            Toast.makeText(ContextUtil.getContext(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
//        Toast.makeText(ContextUtil.getContext(), "username = " + mUsername.getValue(), Toast.LENGTH_SHORT).show();
        RxRetrofit.getApi().login(mUsername.getValue(), mPassword.getValue())
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<UserInfo>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(UserInfo userInfo) {
                        mUserInfo.setValue(userInfo);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                });
    }
}
