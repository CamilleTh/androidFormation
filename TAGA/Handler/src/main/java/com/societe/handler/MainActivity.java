package com.societe.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity implements Callback{

    private ProgressBar barre;
    private Button lancerBouton, annulerBouton;
    private Tache tache;
//    private MyHandler handler = new MyHandler(this);

    private Handler handler = new Handler(this);

    public Handler getHandler() {
        return handler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 != -1) {
            int progression = msg.arg1;
            barre.incrementProgressBy(progression);
        }

        if (msg.arg1 >= 100 || msg.arg1 == -1){
            barre.setProgress(0);
            barre.setVisibility(View.INVISIBLE);
            lancerBouton.setEnabled(true);
            annulerBouton.setVisibility(View.INVISIBLE);
        }
        return false;
    }

    private static class MyHandler extends Handler{
        private final WeakReference<MainActivity> activite;

        public MyHandler(MainActivity activite) {
            this.activite = new WeakReference<MainActivity>(activite);
        }

        public void handleMessage(Message msg) {
            int progression = msg.arg1;
            activite.get().barre.incrementProgressBy(progression);
            if (msg.arg1 >= 100){
                activite.get().barre.setProgress(0);
            }
        }
    }

//    private Handler handler2 = new Handler(){
//
//        @Override
//        public void handleMessage(Message msg) {
//            int progression = msg.arg1;
//            barre.incrementProgressBy(progression);
//            Log.v("----> activite", "" + progression);
//            if (msg.arg1 >= 100){
//                barre.setProgress(0);
//            }
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
                tache.start();
                barre.setVisibility(View.VISIBLE);
                lancerBouton.setEnabled(false);
                annulerBouton.setVisibility(View.VISIBLE);
            }
        });

        annulerBouton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // tache.setContinuer(false);
                Message msgAnnule = tache.getHandlerTache().obtainMessage();
                tache.getHandlerTache().sendMessage(msgAnnule);
                barre.setVisibility(View.INVISIBLE);
                lancerBouton.setEnabled(true);
                annulerBouton.setVisibility(View.INVISIBLE);
            }
        });
    }
}

