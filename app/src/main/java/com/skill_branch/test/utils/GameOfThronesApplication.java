package com.skill_branch.test.utils;



        import android.app.Application;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;

public class GameOfThronesApplication extends Application{

    private static SharedPreferences sSharedPreferences;
    private static Context sContext;
//    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sContext = getApplicationContext();

//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "devintensive-db");
//        Database db = helper.getReadableDb();
//        sDaoSession = new DaoMaster(db).newSession();

    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    public static Context getContext() {
        return sContext;
    }

//    public static DaoSession getDaoSession() {
//        return sDaoSession;
//    }
}
