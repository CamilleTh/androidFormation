package com.societe.calculette2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.societe.metier.Operandes;

public class ResultatActivity extends Activity {

    private TextView operande1, operande2, resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        operande1 = (TextView)findViewById(R.id.operande1_l);
        operande2 = (TextView) findViewById(R.id.operande2_l);
        resultat = (TextView) findViewById(R.id.Resultat_l);

        Intent intent = getIntent();
        Operandes ops = intent.getParcelableExtra("ops");
//        double op1 = intent.getDoubleExtra("operande1", 0);
//        double op2 = intent.getDoubleExtra("operande2", 0);
        double op1 = ops.getOperande1();
        double op2 = ops.getOperande2();
        operande1.setText(String.valueOf(op1));
        operande2.setText(String.valueOf(op2));
        resultat.setText(String.valueOf(op1+op2));
        Intent intent2 = new Intent(this,MainActivity.class);
        intent2.putExtra("resultat", op1+op2);
        //startActivity(intent2);
        // finish();
    }


}
