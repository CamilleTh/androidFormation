package com.societe.calculetteintentserviceparcelable.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.societe.calculetteintentserviceparcelable.exceptions.DivisionParZeroException;

import metier.Operation;

public class CalculetteService extends IntentService {

    public CalculetteService(){
        super ("Thread Calculette");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        double resultat = 0;

        Operation op = intent.getParcelableExtra("operation");
        Intent intentResultat = new Intent("com.plb.action.Resultat");

        switch (op.getOperateur()){
            case "+":
                intentResultat.putExtra("operateur",'+');
                resultat = additionner(op.getOperande1(), op.getOperande2());
                break;
            case "*":
                intentResultat.putExtra("operateur",'*');
                resultat = multiplier(op.getOperande1(), op.getOperande2());
                break;
            case "-":
                intentResultat.putExtra("operateur",'-');
                resultat = soustraire(op.getOperande1(), op.getOperande2());
                break;
            case "/":
                intentResultat.putExtra("operateur",'/');
                try {
                    resultat = diviser(op.getOperande1(), op.getOperande2());
                } catch (DivisionParZeroException e) {
                    intentResultat.putExtra("Erreur", "DivisionParZero");
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentResultat);
                    return;
                }
                break;
        }
        intentResultat.putExtra("Resultat", resultat);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentResultat);
    }



    private double additionner(final double op1, final double op2) {
        Log.v("Msg :","Avant sleep");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.v("Msg :","Fin de additionner");
        return op1+op2;
    }

    private double soustraire(final double op1, final double op2){
//		try {
//			Thread.sleep(200000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return  op1 - op2;
    }

    private double multiplier(final double op1, final double op2){
//		try {
//			Thread.sleep(200000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        return  op1 * op2;
    }

    private double diviser(final double op1, final double op2) throws DivisionParZeroException{
        if (op2 == 0){
            throw new DivisionParZeroException();
        }
        return op1/op2;
    }
}