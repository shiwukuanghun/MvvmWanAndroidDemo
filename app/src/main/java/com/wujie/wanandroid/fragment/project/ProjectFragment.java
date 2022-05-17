package com.wujie.wanandroid.fragment.project;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.adapter.ProjectTypeAdapter;
import com.wujie.wanandroid.bean.ProjectType;
import com.wujie.wanandroid.databinding.FragmentProjectBinding;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends BaseFragment<FragmentProjectBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void init(FragmentProjectBinding binding) {
        super.init(binding);

        ProjectModel projectModel = new ViewModelProvider(this).get(ProjectModel.class);
        binding.setLifecycleOwner(this);
        projectModel.getProjectType();
        projectModel.getProjectTypeList().observe(this, new Observer<List<ProjectType>>() {
            @Override
            public void onChanged(List<ProjectType> projectTypes) {
                List<Fragment> fragmentList = new ArrayList<>();
                List<String> titleList = new ArrayList<>();
                for (ProjectType projectType : projectTypes) {
                    fragmentList.add(ProjectListFragment.instantiate(projectType.getId()));
                    titleList.add(projectType.getName());
                }
                ProjectTypeAdapter projectTypeAdapter = new ProjectTypeAdapter(getChildFragmentManager(), fragmentList, titleList);
                binding.vpProject.setAdapter(projectTypeAdapter);
                binding.tblProject.setupWithViewPager(binding.vpProject);
            }
        });
    }
}