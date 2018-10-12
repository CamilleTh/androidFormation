package com.societe.receveur;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.List;

public class MainActivity extends Activity {

    private ReceveurDyn receveurDyn = new ReceveurDyn();
    private String nomAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bouton1 = (Button)findViewById(R.id.button1);
        Resources res = getResources();
        nomAction = res.getString(R.string.nomActionView);
        bouton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TRUC", "Non ordonné");
                Intent intent = new Intent(nomAction);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });

        Button bouton2 = (Button)findViewById(R.id.button2);
        bouton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TRUC", "ordonné");
                Intent intent = new Intent(nomAction);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendOrderedBroadcast(intent,null);
            }
        });

        Button bouton3 = (Button)findViewById(R.id.button);
        bouton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TRUC", "Local");
                Intent intent = new Intent(nomAction);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
            }
        });

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> l = pm.getInstalledApplications(0);
        for (ApplicationInfo ai : l){
            Log.v("TRUC", ai.toString());
        }
        try {
            ApplicationInfo ai = pm.getApplicationInfo("com.societe.receveurseul",0);
            Log.v("TRUC",pm.getApplicationLabel(ai).toString());
            if ((ai.flags & ApplicationInfo.FLAG_STOPPED) > 0){
                Log.v("TRUC","STOP");
            }else{
                Log.v("TRUC","GO");
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filtreIntent = new IntentFilter(nomAction);
        filtreIntent.addCategory(Intent.CATEGORY_DEFAULT);
        filtreIntent.setPriority(35);
        registerReceiver(receveurDyn, filtreIntent);
        LocalBroadcastManager.getInstance(this).registerReceiver(receveurDyn, filtreIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receveurDyn);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receveurDyn);
    }
}
