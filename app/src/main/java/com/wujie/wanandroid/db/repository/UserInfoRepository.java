package com.wujie.wanandroid.db.repository;

import android.content.Context;
import android.util.Log;

import com.wujie.wanandroid.bean.UserInfo;
import com.wujie.wanandroid.db.WanDatabase;
import com.wujie.wanandroid.db.dao.UserInfoDao;
import com.wujie.wanandroid.utils.ContextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author：created by WuChen
 * @Time：2022/5/17 23:33
 * @Description：
 **/
public class UserInfoRepository {
    private static final String TAG = "UserInfoRepository";

    private final UserInfoDao mUserInfoDao;

    private UserInfoRepository() {
        WanDatabase wanDatabase = WanDatabase.getInstance(ContextUtil.getContext());
        mUserInfoDao = wanDatabase.getUserInfoDao();
    }

    private static class SingleHolder {
        private static final UserInfoRepository INSTANCE = new UserInfoRepository();
    }

    public static UserInfoRepository getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void insert(UserInfo userInfo) {
        mUserInfoDao.insert(userInfo);
    }

    public Observable<Optional<UserInfo>> getUserInfo2() {
        List<UserInfo> userInfoList = mUserInfoDao.getUserInfoList();
        Log.d(TAG, "getUserInfo2: currentThread=" + Thread.currentThread().getName());
//        List<UserInfo> userInfoList = new ArrayList<>();
        if (userInfoList == null || userInfoList.isEmpty()) {
            return Observable.just(Optional.empty());
        }
        return Observable.just(Optional.ofNullable(userInfoList.get(0)));
    }

    public List<UserInfo> getUserInfo3() {
        Log.d(TAG, "getUserInfo3: currentThread=" + Thread.currentThread().getName());
        List<UserInfo> userInfoList = mUserInfoDao.getUserInfoList();
        return userInfoList;
    }

    public Optional<UserInfo> getUserInfo() {
        List<UserInfo> userInfoList = mUserInfoDao.getUserInfoList();
        if (userInfoList == null || userInfoList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(userInfoList.get(0));
    }
}
