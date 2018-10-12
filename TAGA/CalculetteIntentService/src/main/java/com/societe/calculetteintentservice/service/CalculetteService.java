package com.societe.calculetteintentservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.societe.calculetteintentservice.exceptions.DivisionParZeroException;

public class CalculetteService extends IntentService {

    public CalculetteService(){
        super ("Thread Calculette");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        double resultat = 0;
        char operateur = intent.getCharExtra("operation",'\0');
        double ope1 = intent.getDoubleExtra("op1", 0);
        double ope2 = intent.getDoubleExtra("op2", 0);
        Intent intentResultat = new Intent("com.plb.action.Resultat");
        intentResultat.putExtra("operateur",operateur);
        switch (operateur){
            case '+':
                resultat = additionner(ope1, ope2);
                break;
            case '*':
                resultat = multiplier(ope1, ope2);
                break;
            case '-':
                resultat = soustraire(ope1, ope2);
                break;
            case '/':
                try {
                    resultat = diviser(ope1, ope2);
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