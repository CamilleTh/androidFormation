package com.societe.premierevenement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PremierEvenementActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button bouton = (Button)findViewById(R.id.button1);
        bouton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(PremierEvenementActivity.this, "Bouton cliqué !",Toast.LENGTH_LONG).show();
            }
        });
    }
}
