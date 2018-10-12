package com.societe.calculetteavecretourapplicationcontext.autre;

import android.app.Application;
import android.content.Context;

public class CalculetteApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
