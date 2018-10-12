package com.societe.calculetteintentserviceparcelable;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.societe.calculetteintentserviceparcelable.service.CalculetteService;

import metier.Operation;

public class MainActivity extends Activity {

    double result,op1,op2;

    private Button boutonAdd, boutonMoins, boutonDiv, boutonMul;
    private EditText operande1, operande2;
    private TextView resultat, operation;

    private void activerBoutons(boolean actif){
        boutonAdd.setEnabled(actif);
        boutonMoins.setEnabled(actif);
        boutonDiv.setEnabled(actif);
        boutonMul.setEnabled(actif);
    }

    private Recepteur recepteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonAdd = (Button)findViewById(R.id.buttonPlus);
        boutonMoins = (Button)findViewById(R.id.buttonMoins);
        boutonDiv = (Button)findViewById(R.id.buttonDiv);
        boutonMul = (Button)findViewById(R.id.buttonMul);
        operande1 = (EditText)findViewById(R.id.operande1);
        operande2 = (EditText)findViewById(R.id.operande2);
        resultat = (TextView)findViewById(R.id.resultat);
        operation = (TextView)findViewById(R.id.operation);
        recepteur = new Recepteur(resultat, operation);

        operande1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultat.setText(null);
                operation.setText(null);
                activerBoutons(operande1.length() > 0 && operande2.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        operande2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultat.setText(null);
                operation.setText(null);
                activerBoutons(operande1.length() > 0 && operande2.length() > 0);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        boutonAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                op1 = Double.parseDouble(operande1.getText().toString());
                op2 = Double.parseDouble(operande2.getText().toString());
                Intent intent = new Intent (MainActivity.this, CalculetteService.class);
                intent.putExtra("operation", new Operation(op1,op2,"+"));
                //operation.setText(getResources().getText(R.string.operationPlus));
                startService(intent);
            }
        });

        boutonMoins.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                double op1 = Double.parseDouble(operande1.getText().toString());
                double op2 = Double.parseDouble(operande2.getText().toString());
                Intent intent = new Intent (MainActivity.this, CalculetteService.class);
                intent.putExtra("operation", new Operation(op1,op2,"-"));;
                //operation.setText(getResources().getText(R.string.operationMoins));
                startService(intent);
            }
        });

        boutonDiv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                double op1 = Double.parseDouble(operande1.getText().toString());
                double op2 = Double.parseDouble(operande2.getText().toString());
                Intent intent = new Intent (MainActivity.this, CalculetteService.class);
                intent.putExtra("operation", new Operation(op1,op2,"/"));
                //operation.setText(getResources().getText(R.string.operationDiv));
                startService(intent);
            }
        });

        boutonMul.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                double op1 = Double.parseDouble(operande1.getText().toString());
                double op2 = Double.parseDouble(operande2.getText().toString());
                Intent intent = new Intent (MainActivity.this, CalculetteService.class);
                intent.putExtra("operation", new Operation(op1,op2,"*"));
                // operation.setText(getResources().getText(R.string.operationMul));
                startService(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.plb.action.Resultat");
        LocalBroadcastManager.getInstance(this).registerReceiver(recepteur, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(recepteur);
    }
}
