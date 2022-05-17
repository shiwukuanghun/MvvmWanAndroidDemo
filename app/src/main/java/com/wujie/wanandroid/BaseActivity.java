package com.wujie.wanandroid;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @Author：created by WuChen
 * @Time：2022/2/25 22:44
 * @Description：
 **/
public abstract class BaseActivity <DB extends ViewDataBinding> extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB binding = DataBindingUtil.setContentView(this, getLayoutId());
        mContext = this;
        initView(binding);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(DB binding);
}
