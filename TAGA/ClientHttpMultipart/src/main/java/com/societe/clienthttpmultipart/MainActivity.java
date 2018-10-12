package com.societe.clienthttpmultipart;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String url = "http://192.168.43.179/upload.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StringBuffer stringBuffer = new StringBuffer("");
        BufferedReader bufferedReader = null;

        SendHttpRequestTask t = new SendHttpRequestTask();
        String[] params = new String[]{url};
        t.execute(params);
        finish();
    }

    private class SendHttpRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];

            File fichier = new File(Environment.getExternalStorageDirectory().getPath() +"/DCIM/Camera/IMG_20161026_115817.jpg");

            int size = (int) fichier.length();
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(fichier));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                HttpClient client = new HttpClient(url);
                client.connectForMultipart();
                client.addFormPart("info", "photo");
                client.addFilePart("fichier", "", bytes);
                client.finishMultipart();
                String data = client.getResponse();
            }
            catch(Throwable t) {
                t.printStackTrace();
            }

            return null;
        }
    }
}
