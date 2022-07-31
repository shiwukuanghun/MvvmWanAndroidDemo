package com.wujie.wanandroid.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wujie.wanandroid.bean.UserInfo;
import com.wujie.wanandroid.db.dao.UserInfoDao;

/**
 * @Author：created by WuChen
 * @Time：2022/5/17 23:18
 * @Description：
 **/
@Database(entities = {UserInfo.class}, version = 1, exportSchema = false)
public abstract class WanDatabase extends RoomDatabase {
    private static final String DB_NAME = "wan.db";
    private static WanDatabase sWanDatabase;

    public static synchronized WanDatabase getInstance(Context context) {
        if (sWanDatabase == null) {
            sWanDatabase = Room.databaseBuilder(context.getApplicationContext(),
                     WanDatabase.class,
                    DB_NAME)
                    .build();
        }
        return sWanDatabase;
    }

    public abstract UserInfoDao getUserInfoDao();
}
