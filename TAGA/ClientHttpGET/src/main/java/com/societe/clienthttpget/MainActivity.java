package com.societe.clienthttpget;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // le chargement d'une page devrait normalement
        // être réalisée dans un thread spécifique.
        Log.v("MESSAGE_PLB","GO");
        //String page = getPage("http://www.android.com");
        String page = getPage("http://10.0.2.2");
        Log.v("MESSAGE_PLB","OK");
        // String page = getPage("http://192.168.43.179");
        Log.v("MESSAGE_PLB", page);
    }

    public String getPage(String adresse) {
        StringBuffer stringBuffer = new StringBuffer("");
        BufferedReader bufferedReader = null;
        HttpURLConnection con = null;
        String ligneLue = null;
        try {
            URL url = new URL(adresse);
            con = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((ligneLue = bufferedReader.readLine()) != null) {
                stringBuffer.append(ligneLue);
                stringBuffer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ( bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuffer.toString();
    }
}
