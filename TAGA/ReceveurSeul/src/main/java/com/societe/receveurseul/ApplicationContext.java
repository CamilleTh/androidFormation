package com.societe.receveurseul;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;

public class ApplicationContext extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Resources res = context.getResources();
        IntentFilter filtreIntent = new IntentFilter(res.getString(R.string.nomActionView));
        filtreIntent.addCategory(Intent.CATEGORY_DEFAULT);
        filtreIntent.setPriority(35);
        ReceveurSeul rs = new ReceveurSeul();
        registerReceiver(rs, filtreIntent);
    }

    public static Context getAppContext() {
        return context;
    }
}
