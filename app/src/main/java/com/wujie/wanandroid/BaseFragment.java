package com.wujie.wanandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @Author：created by WuChen
 * @Time：2022/2/25 23:05
 * @Description：
 **/
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DB binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = binding.getRoot();
        mContext = getContext();
        init(binding);
        return view;
    }

    public abstract int getLayoutId();

    public void init(DB binding) {
    }
}
