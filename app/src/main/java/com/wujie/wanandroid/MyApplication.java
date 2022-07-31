package com.wujie.wanandroid;

import android.app.Application;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
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

    /**
     * 设置上拉加载和下拉刷新的样式
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });

        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            layout.setEnableFooterFollowWhenLoadFinished(true);
            layout.setEnableAutoLoadMore(false);
        });
    }
}
