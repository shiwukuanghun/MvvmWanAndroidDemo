package com.wujie.wanandroid;

import android.app.Application;

import com.wujie.wanandroid.net.RxRetrofit;
import com.wujie.wanandroid.utils.Constant;
import com.wujie.wanandroid.utils.ContextUtil;
import com.wujie.wanandroid.utils.SpUtils;

/**
 * @Author：created by WuChen
 * @Time：2022/2/26 12:31
 * @Description：
 **/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ContextUtil.setContext(this);
        SpUtils.init(this);
        RxRetrofit.getInstance().initRetrofit(Constant.BaseUrl);
    }
}
