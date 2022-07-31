package com.wujie.wanandroid.utils;

import android.text.TextUtils;

/**
 * @Author：created by WuChen
 * @Time：2022/5/18 22:39
 * @Description：
 **/
public class LoginUtils {
    public static boolean isLogin() {
        String loginInfo = (String) SpUtils.get(Constant.LoginInfo, "");
        if (TextUtils.isEmpty(loginInfo)) {
            return false;
        } else {
            return true;
        }
    }
}
