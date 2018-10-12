package com.societe.receveur;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bouton = (Button)findViewById(R.id.button);
        bouton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                String nomAction = res.getString(R.string.nomActionView);
                Intent intent = new Intent(nomAction);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });
    }
}
