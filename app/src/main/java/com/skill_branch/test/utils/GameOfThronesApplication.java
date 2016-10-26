package com.skill_branch.test.utils;



        import android.app.Application;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;

        import com.facebook.stetho.Stetho;
        import com.skill_branch.test.data.database.DaoMaster;
        import com.skill_branch.test.data.database.DaoSession;

        import org.greenrobot.greendao.database.Database;

public class GameOfThronesApplication extends Application{

    private static SharedPreferences sSharedPreferences;
    private static Context sContext;
    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sContext = getApplicationContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "got-db");
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();

        Stetho.initializeWithDefaults(this);
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    public static Context getContext() {
        return sContext;
    }

    public static DaoSession getDaoSession() {
        return sDaoSession;
    }
}
