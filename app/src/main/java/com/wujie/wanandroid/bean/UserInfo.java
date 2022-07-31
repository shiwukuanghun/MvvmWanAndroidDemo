package com.wujie.wanandroid.bean;

import static com.wujie.wanandroid.utils.Constant.UserInfoTable.TABLE_NAME;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/26 15:09
 * @Description：
 **/
@Entity(tableName = TABLE_NAME)
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @Ignore
    public boolean admin;
    @Ignore
    public List<?> chapterTops;
    public int coinCount;
    @Ignore
    public List<?> collectIds;
    @Ignore
    public String email;
    @Ignore
    public String icon;
    @Ignore
    public String nickname;
    public String password;
    @Ignore
    public String publicName;
    @Ignore
    public String token;
    public int type;
    public String username;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<?> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public List<?> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<?> collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
