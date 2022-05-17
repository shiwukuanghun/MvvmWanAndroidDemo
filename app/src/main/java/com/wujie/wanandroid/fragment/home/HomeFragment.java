package com.wujie.wanandroid.fragment.home;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.adapter.HomeAdapter;
import com.wujie.wanandroid.bean.HomeBean;
import com.wujie.wanandroid.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private static final String TAG = "HomeFragment";

    private List<HomeBean> mHomeBeanList;
    private HomeAdapter mHomeAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(FragmentHomeBinding binding) {
        super.init(binding);

        HomeModel homeModel = new ViewModelProvider(this).get(HomeModel.class);
        binding.setLifecycleOwner(this);
//        binding.setHomeModel(homeModel);
        binding.rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvHome.setHasFixedSize(true);
        mHomeBeanList = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mHomeBeanList);
        binding.rvHome.setAdapter(mHomeAdapter);
        homeModel.getHomeBeanList();
        homeModel.getHomeList().observe(this, new Observer<List<HomeBean>>() {
            @Override
            public void onChanged(List<HomeBean> homeBeans) {
                Log.e(TAG, "onChanged: homeBeans.size = " + homeBeans.size());
                mHomeBeanList.addAll(homeBeans);
                mHomeAdapter.notifyDataSetChanged();
            }
        });
    }
}