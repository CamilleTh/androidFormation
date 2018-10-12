package com.societe.premiereappli;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("MSG", "Methode onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MSG", "Methode onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MSG", "Methode onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MSG", "Methode onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MSG", "Methode onDestroy()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("PLB_MSG", "Methode onCreate()");
        Log.v("BLP_message", "Ici mon message");
    }
}
