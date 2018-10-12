package com.societe.calculetteservice.activites;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.societe.calculetteservice.R;
import com.societe.calculetteservice.exceptions.DivisionParZeroException;
import com.societe.calculetteservice.services.CalculetteService;

public class MainActivity extends Activity {

    private double result, op1, op2;

    private Button boutonAdd, boutonMoins, boutonDiv, boutonMul;
    private EditText operande1, operande2;
    private TextView resultat, operation;
    private CalculetteService calculetteService;

    private ServiceConnection connexion = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calculetteService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calculetteService = ((CalculetteService.ServiceLiaison) service).getService();
        }
    };

    private void activerBoutons(boolean actif) {
        boutonAdd.setEnabled(actif);
        boutonMoins.setEnabled(actif);
        boutonDiv.setEnabled(actif);
        boutonMul.setEnabled(actif);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, CalculetteService.class);
        if (bindService(intent, connexion, Context.BIND_AUTO_CREATE)) {

            final Resources res = getResources();
            boutonAdd = (Button) findViewById(R.id.buttonPlus);
            boutonMoins = (Button) findViewById(R.id.buttonMoins);
            boutonDiv = (Button) findViewById(R.id.buttonDiv);
            boutonMul = (Button) findViewById(R.id.buttonMul);
            operande1 = (EditText) findViewById(R.id.operande1);
            operande2 = (EditText) findViewById(R.id.operande2);
            resultat = (TextView) findViewById(R.id.resultat);
            operation = (TextView) findViewById(R.id.operation);

            operande1.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    resultat.setText(null);
                    operation.setText(null);
                    activerBoutons(operande1.length() > 0 && operande2.length() > 0);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            operande2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    resultat.setText(null);
                    operation.setText(null);
                    activerBoutons(operande1.length() > 0 && operande2.length() > 0);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            boutonAdd.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // activerBoutons(false);
                    op1 = Double.parseDouble(operande1.getText().toString());
                    op2 = Double.parseDouble(operande2.getText().toString());
                    new Thread (new Runnable() {
                        @Override
                        public void run() {
                            result = calculetteService.additionner(op1, op2);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resultat.setText(res.getText(R.string.label_resultat) + " " + result );
                                    operation.setText(res.getText(R.string.operationPlus));
                                    // activerBoutons(true);
                                }
                            });
                        }
                    }).start();

                }
            });

            boutonMoins.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    double op1 = Double.parseDouble(operande1.getText().toString());
                    double op2 = Double.parseDouble(operande2.getText().toString());
                    operation.setText(res.getText(R.string.operationMoins));
                    resultat.setText(res.getText(R.string.label_resultat) + " "  + calculetteService.soustraire(op1, op2));
                }
            });

            boutonDiv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    double op1 = Double.parseDouble(operande1.getText().toString());
                    double op2 = Double.parseDouble(operande2.getText().toString());
                    operation.setText(res.getText(R.string.operationDiv));
                    try {
                        resultat.setText(res.getText(R.string.label_resultat) + " "  + calculetteService.diviser(op1, op2));
                    } catch (DivisionParZeroException e) {
                        Log.v("MSG", "-------> " + e.getMessage());
                        resultat.setText(res.getText(R.string.label_resultat).toString() + e.getMessage());
                        //resultat.setText(res.getText(R.string.label_resultat).toString() + res.getText(R.string.labelDivisionErreur));
                    }
                }
            });

            boutonMul.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    double op1 = Double.parseDouble(operande1.getText().toString());
                    double op2 = Double.parseDouble(operande2.getText().toString());
                    operation.setText(res.getText(R.string.operationMul));
                    resultat.setText(res.getText(R.string.label_resultat) + " "  + calculetteService.multiplier(op1, op2));
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connexion);
    }
}