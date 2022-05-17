package com.wujie.wanandroid.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.wujie.wanandroid.R;

/**
 * @Author：created by WuChen
 * @Time：2022/2/27 16:30
 * @Description：
 **/
public class BindingAdapterUtil {
    @BindingAdapter("loadImage")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null) {
            Glide.with(ContextUtil.getContext())
                    .load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
}
