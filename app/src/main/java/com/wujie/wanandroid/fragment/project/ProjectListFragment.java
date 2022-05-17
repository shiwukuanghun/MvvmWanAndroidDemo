package com.wujie.wanandroid.fragment.project;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.adapter.ProjectListAdapter;
import com.wujie.wanandroid.bean.ProjectItem;
import com.wujie.wanandroid.databinding.FragmentProjectListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/27 15:40
 * @Description：
 **/
public class ProjectListFragment extends BaseFragment<FragmentProjectListBinding> {

    private List<ProjectItem> mProjectItemList;
    private ProjectListAdapter mProjectListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    public void init(FragmentProjectListBinding binding) {
        super.init(binding);

        ProjectListModel projectListModel = new ViewModelProvider(this).get(ProjectListModel.class);
        binding.rvProject.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rvProject.setHasFixedSize(true);
        mProjectItemList = new ArrayList<>();
        mProjectListAdapter = new ProjectListAdapter(mProjectItemList);
        binding.rvProject.setAdapter(mProjectListAdapter);
        Bundle bundle = getArguments();
        int cid = bundle.getInt("cid");
        projectListModel.getProjectList(cid);
        projectListModel.getProjectItemLiveData().observe(this, new Observer<List<ProjectItem>>() {
            @Override
            public void onChanged(List<ProjectItem> projectItems) {
                mProjectItemList.addAll(projectItems);
                mProjectListAdapter.notifyDataSetChanged();
            }
        });
    }

    public static ProjectListFragment instantiate(int cid) {
        ProjectListFragment projectListFragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        projectListFragment.setArguments(bundle);
        return projectListFragment;
    }
}
