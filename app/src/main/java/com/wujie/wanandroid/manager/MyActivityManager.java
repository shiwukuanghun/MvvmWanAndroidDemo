package com.wujie.wanandroid.manager;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * @Author：created by WuChen
 * @Time：2022/8/6 10:17
 * @Description：
 **/
public class MyActivityManager {
    private static MyActivityManager sInstance = new MyActivityManager();

    private WeakReference<Activity> mCurrentActivityWeakRef;

    private MyActivityManager() {
    }

    public static MyActivityManager getInstance() {
        return sInstance;
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (mCurrentActivityWeakRef != null) {
            currentActivity = mCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    /**
     * 保存Activity
     *
     * @param activity
     */
    public void setCurrentActivity(Activity activity) {
        mCurrentActivityWeakRef = new WeakReference<>(activity);
    }
}
