package com.wujie.wanandroid.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wujie.wanandroid.bean.UserInfo;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/5/17 23:33
 * @Description：
 **/
@Dao
public interface UserInfoDao {
    @Insert
    void insert(UserInfo... userInfo);

    @Query("SELECT * FROM userInfo")
    List<UserInfo> getUserInfoList();
}
