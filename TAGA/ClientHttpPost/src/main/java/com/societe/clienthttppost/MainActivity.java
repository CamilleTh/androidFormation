package com.societe.clienthttppost;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
	private TextView texte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		texte = (TextView)findViewById(R.id.textView1);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Log.v("MESSAGE","GO");
		String reponse = getPage("http://192.168.43.179/androidPost.php");
		texte.setText("--> " + reponse);
		Log.v("MESSAGE",reponse);
	}

	private String getPage(String adresse){
		StringBuffer reponse = new StringBuffer();
		BufferedReader bufferedReader = null;
		HttpURLConnection con = null;
		String ligneLue = null;
		
		String urlParam="param1=3&param2=4";
		
		try {
			URL url = new URL(adresse);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(urlParam);
			ds.flush();
			ds.close();
			int reponseHttp = con.getResponseCode();
			Log.v("MESSAGE", "Code retour HTTP: " + reponseHttp);
			bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((ligneLue = bufferedReader.readLine()) != null){
				reponse.append(ligneLue);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse.toString();
	}
}
