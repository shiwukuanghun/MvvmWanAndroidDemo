package com.wujie.wanandroid.utils;

import android.content.Context;

/**
 * @Author：created by WuChen
 * @Time：2021/11/19 19:16
 * @Description：
 **/
public class ContextUtil {
    private static Context sContext;;

    public static Context getContext() {
        return sContext;
    }

    public static void setContext(Context context) {
        sContext = context;
    }
}
