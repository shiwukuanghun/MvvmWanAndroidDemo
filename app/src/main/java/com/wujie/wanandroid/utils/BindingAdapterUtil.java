package com.wujie.wanandroid.utils;

import android.widget.ImageView;
import android.widget.TextView;

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

    @BindingAdapter("todoGrade")
    public static void setTodoGrade(ImageView imageView, int priority) {
        if (priority == 0) {
            imageView.setImageResource(R.mipmap.ic_star);
        } else {
            imageView.setImageResource(R.mipmap.ic_star_outline);
        }
    }

    @BindingAdapter("todoStatus")
    public static void setTodoStatus(TextView textView, int status) {
        if (status == 0) {
            textView.setText("未完成");
            textView.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.app_color_theme_1));
        } else {
            textView.setText("已完成");
            textView.setTextColor(ContextUtil.getContext().getResources().getColor(R.color.color_blue));
        }
    }
}
