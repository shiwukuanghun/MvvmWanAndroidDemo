package com.wujie.wanandroid;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wujie.wanandroid.activity.login.LoginActivity;
import com.wujie.wanandroid.bean.UserInfo;
import com.wujie.wanandroid.databinding.ActivityMainBinding;
import com.wujie.wanandroid.db.repository.UserInfoRepository;

import java.util.Optional;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private static final String TAG = "MainActivity";

    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(ActivityMainBinding binding) {
        setSupportActionBar(binding.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.navigation_navigation, R.id.navigation_project)
                .setOpenableLayout(binding.drawLayout).build();
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewBottom, mNavController);
        NavigationUI.setupWithNavController(binding.navView, mNavController);

        TextView tvNickName = binding.navView.getHeaderView(0).findViewById(R.id.tv_nike_name);
        binding.navView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });

        Observable.create((ObservableOnSubscribe<Optional<UserInfo>>) emitter -> emitter.onNext(UserInfoRepository.getInstance().getUserInfo()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfo -> {
                    UserInfo info = userInfo.orElse(null);
                    if (info != null) {
                        Log.d(TAG, "accept: " + info.username);
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}