package com.skill_branch.test.mvp.presenters;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.mvp.models.SplashScreenModel;
import com.skill_branch.test.mvp.views.ISplashScreenView;
import com.skill_branch.test.ui.activities.MainActivity;
import com.skill_branch.test.utils.AppConfig;
import com.skill_branch.test.utils.ConstantManager;
import com.skill_branch.test.utils.GameOfThronesApplication;
import com.skill_branch.test.utils.NetworkStatusChecker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SplashScreenPresenter implements ISplashScreenPresenter {
    public static SplashScreenPresenter selfInstance = new SplashScreenPresenter();
    private SplashScreenModel mModel;
    private ISplashScreenView mView;

    public SplashScreenPresenter() {
        mModel = new SplashScreenModel();
    }

    public static SplashScreenPresenter getInstance() {
        return selfInstance;
    }

    @Override
    public void takeView(ISplashScreenView view) {
        this.mView = view;
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void initView() {
        loadData();
    }

    @Nullable
    @Override
    public ISplashScreenView getView() {
        return this.mView;
    }

    @Override
    public void loadData() {
        if (getView()!=null) {
            if (!NetworkStatusChecker.isNetworkAvailable(GameOfThronesApplication.getContext())) {
                getView().showMessage("Internet connection not availiable!");
            } else {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        this.cancel();
                        startMainActivity();
                    }
                },3000);


                mModel.prepareData();
            }
        }

    }

    private void startMainActivity()  {
        if (getView()!=null) {
            long timeOut = AppConfig.MAX_READ_TIMEOUT;
            while (!mModel.isDataLoaded() && timeOut>0){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    timeOut -= 10;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getView().startMainActivity();
        }
    }
}
