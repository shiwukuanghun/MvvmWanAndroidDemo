package com.wujie.wanandroid.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Time：2019/1/26 0026 下午 14:04
 * Author：WuChen
 * Description：
 **/
public class CommonUtils {

    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        //0-190
        int green =random.nextInt(150);
        //0-190
        int blue =random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }

}
