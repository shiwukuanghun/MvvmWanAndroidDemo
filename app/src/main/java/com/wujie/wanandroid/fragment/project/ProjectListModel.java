package com.wujie.wanandroid.fragment.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wujie.wanandroid.bean.PageListDataBean;
import com.wujie.wanandroid.bean.ProjectItem;
import com.wujie.wanandroid.net.BaseObserver;
import com.wujie.wanandroid.net.RxHelper;
import com.wujie.wanandroid.net.RxRetrofit;

import java.util.List;

/**
 * @Author：created by WuChen
 * @Time：2022/2/27 15:44
 * @Description：
 **/
public class ProjectListModel extends ViewModel {
    private MutableLiveData<List<ProjectItem>> mProjectItemLiveData;

    public ProjectListModel() {
        mProjectItemLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<ProjectItem>> getProjectItemLiveData() {
        return mProjectItemLiveData;
    }

    public void getProjectList(int cid) {
        RxRetrofit.getApi()
                .getProjectList(cid)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<PageListDataBean<ProjectItem>>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(PageListDataBean<ProjectItem> projectItemPageListDataBean) {
                        List<ProjectItem> projectItemList = projectItemPageListDataBean.getDatas();
                        if (projectItemList != null && !projectItemList.isEmpty()) {
                            mProjectItemLiveData.setValue(projectItemList);
                        }
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                });
    }
}
