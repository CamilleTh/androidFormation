package com.societe.handler;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.Toast;

public class Tache extends Thread implements Callback{

    private MainActivity activite;
    private Handler handler;
    private Handler handlerTache = new Handler(this);
    private boolean continuer = true;

    public Tache(MainActivity activite) {
        this.activite = activite;
        handler = activite.getHandler();
    }

    public Tache(Handler handler){
        this.handler = handler;
    }

    public Handler getHandlerTache() {
        return handlerTache;
    }

    public void setContinuer(boolean continuer) {
        this.continuer = continuer;
    }

    @Override
    public boolean handleMessage(Message msg) {
        setContinuer(false);
        return true;
    }

    public void run(){
        int progress = 0;
        Message message;
        //Handler handler = activite.getHandler();

        handler.post(toastDebut);
        while (progress++ <=100 && continuer){
            // Simulation d'une tâche.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message = handler.obtainMessage();
            message.arg1 = progress;
            handler.sendMessage(message);
        }
        if (continuer)
            handler.post(toastFin);
        else {
            handler.post(toastAnnuler);
            message = handler.obtainMessage();
            message.arg1 = -1;
            handler.sendMessage(message);
        }
    }

    private Runnable toastDebut = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(activite, "Début de la tâche",Toast.LENGTH_LONG).show();
        }};

    // Le Runnable du Toast de fin de tâche.
    private Runnable toastFin = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(activite, "Tâche terminée", Toast.LENGTH_LONG).show();
        }};

    private Runnable toastAnnuler = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(activite, "Tâche annulée", Toast.LENGTH_LONG).show();
        }};
}
