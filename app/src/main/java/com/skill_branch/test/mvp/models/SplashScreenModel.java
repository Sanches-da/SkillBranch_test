package com.skill_branch.test.mvp.models;


import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.data.database.HouseModel;
import com.skill_branch.test.data.network.AsyncLoad;
import com.skill_branch.test.utils.ConstantManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SplashScreenModel{
    private DataManager mDataManager;
    private AsyncLoad process;

    public SplashScreenModel() {
        mDataManager = DataManager.getInstance();
    }

    public void prepareData(){
        process = mDataManager.LoadDataFromNet();
    }

    public boolean isDataLoaded(){
        return process.isLoadFinished();
    }

}
