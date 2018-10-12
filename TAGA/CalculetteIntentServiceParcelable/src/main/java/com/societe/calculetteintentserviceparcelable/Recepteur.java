package com.societe.calculetteintentserviceparcelable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class Recepteur extends BroadcastReceiver {

    private TextView resultat, operation;

    public Recepteur(){}

    public Recepteur (TextView tv, TextView op){
        resultat = tv;
        operation = op;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("Msg :", "Receveur");
        switch(intent.getCharExtra("operateur",'\0')){
            case '+':
                operation.setText(context.getResources().getText(R.string.operationPlus).toString());
                break;
            case '-':
                operation.setText(context.getResources().getText(R.string.operationMoins).toString());
                break;
            case '*':
                operation.setText(context.getResources().getText(R.string.operationMul).toString());
                break;
            case '/':
                operation.setText(context.getResources().getText(R.string.operationDiv).toString());
                break;
        }
        if (!intent.hasExtra("Erreur")){
            resultat.setText(context.getResources().getText(R.string.label_resultat).toString() + intent.getDoubleExtra("Resultat", 0));
        } else {
            String erreur = intent.getStringExtra("Erreur");
            if (erreur.equals("DivisionParZero")){
                resultat.setText(context.getResources().getText(R.string.label_resultat).toString() + context.getResources().getText(R.string.labelDivisionErreur));
            }
        }
    }
}
