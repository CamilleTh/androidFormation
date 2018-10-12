package com.societe.clienthttpgetphp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texte = (TextView)findViewById(R.id.textView1);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.v("MESSAGE_PLB","GO");
        String reponse = getPage("http://192.168.43.179/androidGet.php?param1=3");
        texte.setText("--> " + reponse);
        Log.v("MESSAGE_PLB",reponse);
    }

    private String getPage(String adresse){
        StringBuffer reponse = new StringBuffer();
        BufferedReader bufferedReader = null;
        HttpURLConnection con = null;
        String ligneLue = null;

        try {
            URL url = new URL(adresse);
            con = (HttpURLConnection) url.openConnection();
            int reponseHttp = con.getResponseCode();
            Log.v("MESSAGE_PLB", "Code retour HTTP: " + reponseHttp);
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((ligneLue = bufferedReader.readLine()) != null){
                reponse.append(ligneLue);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MESSAGE_PLB", "Erreur", e);
        }
        return reponse.toString();
    }
}
