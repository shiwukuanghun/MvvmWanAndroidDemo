package com.wujie.wanandroid.fragment.todo;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ccj.poptabview.FilterConfig;
import com.ccj.poptabview.base.BaseFilterTabBean;
import com.ccj.poptabview.bean.FilterGroup;
import com.ccj.poptabview.bean.FilterTabBean;
import com.ccj.poptabview.listener.OnPopTabSetListener;
import com.ccj.poptabview.loader.PopEntityLoaderImp;
import com.ccj.poptabview.loader.ResultLoaderImp;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.adapter.TodoAdapter;
import com.wujie.wanandroid.bean.TodoBean;
import com.wujie.wanandroid.databinding.FragmentTodoBinding;

import java.util.ArrayList;
import java.util.List;

public class TodoFragment extends BaseFragment<FragmentTodoBinding> implements OnPopTabSetListener {
    private static final String TAG = "TodoFragment";

    private com.wujie.wanandroid.databinding.FragmentTodoBinding mBinding;
    private TodoViewModel mTodoViewModel;
    private List<TodoBean> mTodoDataList;
    private TodoAdapter mTodoAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_todo;
    }

    @Override
    public void init(FragmentTodoBinding binding) {
        super.init(binding);
        setHasOptionsMenu(true);  // 加这一行才能显示右上角的“+”号
        mBinding = binding;
        mTodoViewModel = new ViewModelProvider(this, 
                new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(TodoViewModel.class);
        mBinding.setViewModel(mTodoViewModel);
        mBinding.setLifecycleOwner(this);
        initRefreshLayout();
        initAdapter();
        addMyMethod();
        mTodoViewModel.loadTodoList();
    }

    private void initRefreshLayout() {
        mBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            mTodoViewModel.refreshTodoData(true);
        });
        mBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mTodoViewModel.refreshTodoData(false);
        });
    }

    private void initAdapter() {
        mBinding.rvTodo.setHasFixedSize(true);
        mBinding.rvTodo.setLayoutManager(new LinearLayoutManager(getContext()));
        mTodoDataList = new ArrayList<>();
        mTodoAdapter = new TodoAdapter();
        mBinding.rvTodo.setAdapter(mTodoAdapter);
        mTodoViewModel.getDataList().observe(this, new Observer<List<TodoBean>>() {
            @Override
            public void onChanged(List<TodoBean> todoBeans) {
                if (mTodoViewModel.isRefresh()) {
                    mTodoAdapter.setNewInstance(todoBeans);
                    mBinding.refreshLayout.finishRefresh();
                } else {
                    mTodoAdapter.addData(todoBeans);
                    mBinding.refreshLayout.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            NavHostFragment.findNavController(this).navigate(R.id.addTodoFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addMyMethod() {
        FilterGroup filterGroup1 = getStatusData("状态");
        FilterGroup filterGroup3 = getPriorityData("优先级");
        mBinding.popView.setOnPopTabSetListener(this)
                .setPopEntityLoader(new PopEntityLoaderImp()).setResultLoader(new ResultLoaderImp())
                .addFilterItem(filterGroup1.getTab_group_name(), filterGroup1.getFilter_tab(), filterGroup1.getTab_group_type(), filterGroup1.getSingle_or_mutiply())
                .addFilterItem(filterGroup3.getTab_group_name(), filterGroup3.getFilter_tab(), filterGroup3.getTab_group_type(), filterGroup3.getSingle_or_mutiply());
    }

    private FilterGroup getStatusData(String groupName) {
        FilterGroup filterGroup = new FilterGroup();
        filterGroup.setTab_group_name(groupName);
        filterGroup.setTab_group_type(FilterConfig.TYPE_POPWINDOW_SINGLE);
        filterGroup.setSingle_or_mutiply(FilterConfig.FILTER_TYPE_SINGLE);
        List<BaseFilterTabBean> singleFilterList = new ArrayList<>();
        for (int i = -1; i < 4; i++) {
            //一级filter
            FilterTabBean singleFilterBean = new FilterTabBean();
            switch (i) {
                case -1:
                    singleFilterBean.setTab_name("全部");
                    break;
                case 0:
                    singleFilterBean.setTab_name("学习");
                    break;
                case 1:
                    singleFilterBean.setTab_name("工作");
                    break;
                case 2:
                    singleFilterBean.setTab_name("生活");
                    break;
                case 3:
                    singleFilterBean.setTab_name("其他");
                    break;
                default:
                    break;
            }
            singleFilterList.add(singleFilterBean);
        }
        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;
    }

    private FilterGroup getPriorityData(String groupName) {

        FilterGroup filterGroup = new FilterGroup();

        filterGroup.setTab_group_name(groupName);
        filterGroup.setTab_group_type(FilterConfig.TYPE_POPWINDOW_SINGLE);
        filterGroup.setSingle_or_mutiply(FilterConfig.FILTER_TYPE_SINGLE);

        List<BaseFilterTabBean> singleFilterList = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            FilterTabBean singleFilterBean = new FilterTabBean();
            singleFilterBean.setTab_id(String.valueOf(i));
            switch (i) {
                case -1:
                    singleFilterBean.setTab_name("全部");
                    break;
                case 0:
                    singleFilterBean.setTab_name("重要");
                    break;
                case 1:
                    singleFilterBean.setTab_name("一般");
                    break;
                default:
                    break;
            }
            singleFilterList.add(singleFilterBean);
        }
        filterGroup.setFilter_tab(singleFilterList);
        return filterGroup;
    }

    @Override
    public void onPopTabSet(int index, String lable, Object params, String value) {
        if (value != null) {
            if (index == 0) {
                if (value.equals("全部")) {
                    mTodoViewModel.setType(0);
                } else if (value.equals("学习")) {
                    mTodoViewModel.setType(1);
                } else if (value.equals("工作")) {
                    mTodoViewModel.setType(2);
                } else if (value.equals("生活")) {
                    mTodoViewModel.setType(3);
                } else if (value.equals("其他")) {
                    mTodoViewModel.setType(4);
                }
            } else {
                if (value.equals("全部")) {
                    mTodoViewModel.setPriority(0);
                } else if (value.equals("重要")) {
                    mTodoViewModel.setPriority(1);
                } else if (value.equals("一般")) {
                    mTodoViewModel.setPriority(2);
                }
            }
        }
    }
}