package com.wujie.wanandroid.activity.login;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wujie.wanandroid.BaseActivity;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.bean.UserInfo;
import com.wujie.wanandroid.databinding.ActivityLoginBinding;
import com.wujie.wanandroid.utils.ContextUtil;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final String TAG = "LoginActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(ActivityLoginBinding binding) {
        LoginModel loginModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(LoginModel.class);
        binding.setLoginModel(loginModel);
        loginModel.getUserInfo().observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                Log.d(TAG, "onChanged: 当前线程 = " + Thread.currentThread().getName());
                Toast.makeText(ContextUtil.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}