package com.wujie.wanandroid.fragment.todo;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.ccj.poptabview.FilterConfig;
import com.ccj.poptabview.base.BaseFilterTabBean;
import com.ccj.poptabview.bean.FilterGroup;
import com.ccj.poptabview.bean.FilterTabBean;
import com.ccj.poptabview.listener.OnPopTabSetListener;
import com.ccj.poptabview.loader.PopEntityLoaderImp;
import com.ccj.poptabview.loader.ResultLoaderImp;
import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.databinding.FragmentTodoBinding;

import java.util.ArrayList;
import java.util.List;

public class TodoFragment extends BaseFragment<FragmentTodoBinding> implements OnPopTabSetListener {

    private com.wujie.wanandroid.databinding.FragmentTodoBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_todo;
    }

    @Override
    public void init(FragmentTodoBinding binding) {
        super.init(binding);
        setHasOptionsMenu(true);  // 加这一行才能显示右上角的“+”号
        mBinding = binding;
        addMyMethod();
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

    }
}