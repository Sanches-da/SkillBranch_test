package com.skill_branch.test.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatusChecker {
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetwork = cm.getActiveNetworkInfo();

        return (actNetwork!=null && actNetwork.isConnectedOrConnecting());
    }
}
