package com.wujie.wanandroid.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.bean.TodoBean;
import com.wujie.wanandroid.databinding.ItemTodoBinding;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/8/6 16:13
 * @Description：
 **/
public class TodoAdapter extends BaseQuickAdapter<TodoBean, BaseDataBindingHolder<ItemTodoBinding>> {
    public TodoAdapter() {
        super(R.layout.item_todo);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder<ItemTodoBinding> itemTodoBindingBaseDataBindingHolder, TodoBean todoBean) {
        ItemTodoBinding binding = itemTodoBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            binding.setTodoBean(todoBean);
        }
    }
}
