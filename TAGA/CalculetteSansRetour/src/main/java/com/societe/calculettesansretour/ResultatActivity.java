package com.societe.calculettesansretour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ResultatActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        double op1 = getIntent().getDoubleExtra("oper1", 0);
        double op2 = getIntent().getDoubleExtra("oper2", 0);
        Log.v("MSG", "operande1:" + op1 + " -- operande2:" + op2);
        double somme = op1 + op2;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("resultat", somme);
        startActivity(intent);
        Log.v("MSG", ""+somme);
        //finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MSG", "onDestroy() de ResultatActivity");
    }
}
