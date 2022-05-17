package com.wujie.wanandroid.fragment.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wujie.wanandroid.bean.ProjectType;
import com.wujie.wanandroid.net.BaseObserver;
import com.wujie.wanandroid.net.RxHelper;
import com.wujie.wanandroid.net.RxRetrofit;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/27 15:21
 * @Description：
 **/
public class ProjectModel extends ViewModel {
    private MutableLiveData<List<ProjectType>> mProjectTypeList;

    public ProjectModel() {
        mProjectTypeList = new MutableLiveData<>();
    }

    public MutableLiveData<List<ProjectType>> getProjectTypeList() {
        return mProjectTypeList;
    }

    public void getProjectType() {
        RxRetrofit.getApi()
                .getProjectType()
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<List<ProjectType>>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(List<ProjectType> projectTypes) {
                        if (projectTypes != null && !projectTypes.isEmpty()) {
                            mProjectTypeList.setValue(projectTypes);
                        }
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                });
    }
}
