package com.skill_branch.test.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.skill_branch.test.R;
import com.skill_branch.test.data.House;
import com.skill_branch.test.data.database.DataManager;
import com.skill_branch.test.utils.ConstantManager;
import com.skill_branch.test.utils.GameOfThronesApplication;
import com.skill_branch.test.utils.NetworkStatusChecker;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private TimerTask checkData;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        checkData = new TimerTask() {
            @Override
            public void run() {
                startMainActivity();
            }
        };

        Timer timer = new Timer();
        timer.schedule(checkData, 0, ConstantManager.SPLASH_DELAY);


    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (!NetworkStatusChecker.isNetworkAvailable(getApplicationContext())) {
            showSnackbar("Internet connection not availiable!");
        } else {
            DataManager.getInstance().findHouse(ConstantManager.STARK_ID); //Stark
            DataManager.getInstance().findHouse(ConstantManager.LANNISTER_ID); //Lannister
            DataManager.getInstance().findHouse(ConstantManager.TARGARYEN_ID); //Targaryen
        }

    }

    private void startMainActivity()  {

        if (!NetworkStatusChecker.isNetworkAvailable(getApplicationContext())) {
            showSnackbar("Internet connection not availiable!");
        } else {
            if (House.isSparkLoaded<0) {
                DataManager.getInstance().findHouse(ConstantManager.STARK_ID); //Stark
                return;
            }else if (House.isSparkLoaded==0) return;
            if (House.isLannisterLoaded<0) {
                DataManager.getInstance().findHouse(ConstantManager.LANNISTER_ID); //Lannister
            }else if (House.isLannisterLoaded==0) return;
            if (House.isTargaryenLoaded<0) {
                DataManager.getInstance().findHouse(ConstantManager.TARGARYEN_ID); //Targaryen
            }else if (House.isTargaryenLoaded==0) return;
        }
        checkData.cancel();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void showSnackbar(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

}
