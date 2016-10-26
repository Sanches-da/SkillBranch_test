package com.skill_branch.test.mvp.presenters;

import android.support.annotation.Nullable;

import com.skill_branch.test.mvp.views.ISplashScreenView;


public interface ISplashScreenPresenter {
    void takeView(ISplashScreenView view);
    void dropView();
    void initView();

    @Nullable
    ISplashScreenView getView();

    void loadData();
}
