package com.societe.appel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.v("---->","OK1");
        switch (requestCode){
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v("MSG------>","OK2");
                    Uri uri = Uri.parse("tel:00000000");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    try {
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = Uri.parse("tel:00000000");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            Log.v ("---->", "PERMISSION_GRANTED");
            startActivity(intent);
            finish();
        } else {
            Log.v ("---->", "PERMISSION_DENIED");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},10);
        }
     }
}
