package com.wujie.wanandroid.adapter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.bean.HomeBean;
import com.wujie.wanandroid.databinding.ItemHomeBinding;
import com.wujie.wanandroid.utils.ContextUtil;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/26 21:50
 * @Description：
 **/
public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseDataBindingHolder<ItemHomeBinding>> {
    private static final String TAG = "HomeAdapter";
    public HomeAdapter(@Nullable List<HomeBean> data) {
        super(R.layout.item_home, data);
        Log.e(TAG, "HomeAdapter: ================");
    }

    @Override
    protected void onItemViewHolderCreated(@NonNull BaseDataBindingHolder<ItemHomeBinding> viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemHomeBinding> itemHomeBindingBaseDataBindingHolder, HomeBean homeBean) {
        Log.e(TAG, "convert: ===============");
        ItemHomeBinding binding = itemHomeBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            Log.e(TAG, "convert: binding != null");
            binding.setHomeBean(homeBean);
            binding.executePendingBindings();
        } else {
            Toast.makeText(ContextUtil.getContext(), "为空", Toast.LENGTH_SHORT).show();
        }
    }
}
