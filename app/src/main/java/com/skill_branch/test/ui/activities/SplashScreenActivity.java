package com.skill_branch.test.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.skill_branch.test.R;
import com.skill_branch.test.mvp.presenters.ISplashScreenPresenter;
import com.skill_branch.test.mvp.presenters.SplashScreenPresenter;
import com.skill_branch.test.mvp.views.ISplashScreenView;
import com.skill_branch.test.utils.GameOfThronesApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.skill_branch.test.utils.GameOfThronesApplication.getContext;

public class SplashScreenActivity extends AppCompatActivity implements ISplashScreenView{
    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    private SplashScreenPresenter mPresenter = SplashScreenPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        mPresenter.takeView(this);
        mPresenter.initView();


    }


    private void showSnackbar(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }


    //region =================== ISplashScreenView =================
    @Override
    public void showMessage(String message) {
        showSnackbar(message);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public ISplashScreenPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

//endregion =================== ISplashScreenView =================
}
