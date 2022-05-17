package com.wujie.wanandroid.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.bean.ProjectItem;
import com.wujie.wanandroid.databinding.ItemProjectBinding;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/27 15:20
 * @Description：
 **/
public class ProjectListAdapter extends BaseQuickAdapter<ProjectItem, BaseDataBindingHolder<ItemProjectBinding>> {
    public ProjectListAdapter(@Nullable List<ProjectItem> data) {
        super(R.layout.item_project, data);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDataBindingHolder<ItemProjectBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingUtil.bind(holder.itemView);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemProjectBinding> itemProjectBindingBaseDataBindingHolder, ProjectItem projectItem) {
        ItemProjectBinding binding = itemProjectBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            binding.setProjectItem(projectItem);
            binding.executePendingBindings();
        }
    }
}
