package com.skill_branch.test.mvp.views;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.skill_branch.test.mvp.presenters.ISplashScreenPresenter;


public interface ISplashScreenView {
    void showMessage(String message);

    void showProgress();
    void hideProgress();

    ISplashScreenPresenter getPresenter();

    void startMainActivity();

}
