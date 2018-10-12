package com.societe.tacheasynchrone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    private ProgressBar barre;
    private Button lancerBouton, annulerBouton;
    private Tache tache;
//    private MyHandler handler = new MyHandler(this);

    public ProgressBar getBarre() {
        return barre;
    }

    public Button getLancerBouton() {
        return lancerBouton;
    }

    public Button getAnnulerBouton() {
        return annulerBouton;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barre = (ProgressBar)findViewById(R.id.progressBar1);
        barre.getKeepScreenOn();
        barre.setVisibility(View.INVISIBLE);
        lancerBouton =(Button)findViewById(R.id.button1);
        annulerBouton =(Button)findViewById(R.id.annulerBouton);
        annulerBouton.setVisibility(View.INVISIBLE);

        lancerBouton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Tache tache = new Tache(MainActivity.this);
                tache = new Tache(MainActivity.this);
                tache.execute();
                barre.setVisibility(View.VISIBLE);
                lancerBouton.setEnabled(false);
                annulerBouton.setVisibility(View.VISIBLE);
            }
        });

        annulerBouton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                barre.setVisibility(View.INVISIBLE);
                lancerBouton.setEnabled(true);
                annulerBouton.setVisibility(View.INVISIBLE);
                tache.cancel(false);
            }
        });
    }
}
