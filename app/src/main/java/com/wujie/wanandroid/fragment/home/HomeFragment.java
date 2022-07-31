package com.wujie.wanandroid.fragment.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ms.banner.Banner;
import com.ms.banner.BannerConfig;
import com.ms.banner.holder.BannerViewHolder;
import com.ms.banner.holder.HolderCreator;
import com.wujie.wanandroid.BaseFragment;
import com.wujie.wanandroid.GlideApp;
import com.wujie.wanandroid.R;
import com.wujie.wanandroid.adapter.HomeAdapter;
import com.wujie.wanandroid.bean.BannerBean;
import com.wujie.wanandroid.bean.HomeBean;
import com.wujie.wanandroid.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {
    private static final String TAG = "HomeFragment";

    private List<HomeBean> mHomeBeanList;
    private HomeAdapter mHomeAdapter;
    private Banner mBanner;

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
        initBanner();
        binding.rvHome.setAdapter(mHomeAdapter);
        homeModel.getHomeBeanList();
        homeModel.getBanner();
        homeModel.getHomeList().observe(this, new Observer<List<HomeBean>>() {
            @Override
            public void onChanged(List<HomeBean> homeBeans) {
                Log.e(TAG, "onChanged: homeBeans.size = " + homeBeans.size());
                mHomeBeanList.addAll(homeBeans);
                mHomeAdapter.notifyDataSetChanged();
            }
        });
        homeModel.getBannerList().observe(this, new Observer<List<BannerBean>>() {
            @Override
            public void onChanged(List<BannerBean> bannerBeanList) {
                Log.d(TAG, "onChanged: bannerBeanList.size="+ bannerBeanList.size());
                if (bannerBeanList != null && bannerBeanList.size() > 0) {
                    List<String> uriList = new ArrayList<>();
                    for (int i = 0; i < bannerBeanList.size(); i++) {
                        uriList.add(bannerBeanList.get(i).getImagePath());
                    }
                    mBanner.setPages(uriList, new HolderCreator<BannerViewHolder>() {
                        @Override
                        public BannerViewHolder createViewHolder() {
                            return new CustomViewHolder();
                        }
                    }).setAutoPlay(true)
                            .setBannerStyle(BannerConfig.CUSTOM_INDICATOR)
                            .start();
                }
            }
        });
    }

    private void initBanner() {
        View bannerView = LayoutInflater.from(getContext()).inflate(R.layout.home_banner, null);
        mBanner = bannerView.findViewById(R.id.banner);
        mHomeAdapter.addHeaderView(bannerView);
    }

    static class CustomViewHolder implements BannerViewHolder<String> {
        private ImageView mIvBanner;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mIvBanner = (ImageView) view.findViewById(R.id.iv_banner);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            GlideApp.with(context).load(data).centerCrop().into(mIvBanner);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }
}