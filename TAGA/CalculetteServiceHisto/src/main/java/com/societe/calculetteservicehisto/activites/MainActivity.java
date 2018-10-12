package com.societe.calculetteservicehisto.activites;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.societe.calculetteservicehisto.R;
import com.societe.calculetteservicehisto.exceptions.DivisionParZeroException;
import com.societe.calculetteservicehisto.services.CalculetteService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends Activity {

    double result, op1, op2;
    private ArrayList<String> histo;

    private Button boutonAdd, boutonMoins, boutonDiv, boutonMul, boutonHisto;
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
            boutonHisto = (Button) findViewById(R.id.histo);
            histo = new ArrayList<>();

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
                    activerBoutons(false);
                    op1 = Double.parseDouble(operande1.getText().toString());
                    op2 = Double.parseDouble(operande2.getText().toString());
                    final StringBuffer sb = new StringBuffer();
                    sb.append(op1).append(res.getText(R.string.labelBoutonAdd)).append(op2).append("=");
                    new Thread (new Runnable() {
                        @Override
                        public void run() {
                            result = calculetteService.additionner(op1, op2);
                            sb.append(result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resultat.setText(res.getText(R.string.label_resultat) + " " + result );
                                    operation.setText(res.getText(R.string.operationPlus));
                                    histo.add(sb.toString());
                                    activerBoutons(true);
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
                    StringBuffer sb = new StringBuffer();
                    sb.append(op1).append(res.getText(R.string.labelBoutonMoins)).append(op2).append("=");
                    double result = calculetteService.soustraire(op1, op2);
                    sb.append(result);
                    histo.add(sb.toString());
                    resultat.setText(res.getText(R.string.label_resultat) + " "  + result);
                }
            });

            boutonDiv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    double op1 = Double.parseDouble(operande1.getText().toString());
                    double op2 = Double.parseDouble(operande2.getText().toString());
                    operation.setText(res.getText(R.string.operationDiv));
                    StringBuffer sb = new StringBuffer();
                    sb.append(op1).append(res.getText(R.string.labelBoutonDiv)).append(op2).append("=");
                    try {
                        double result = calculetteService.diviser(op1, op2);
                        resultat.setText(res.getText(R.string.label_resultat) + " "  + result);
                        sb.append(result);
                    } catch (DivisionParZeroException e) {
                        resultat.setText(res.getText(R.string.label_resultat).toString() + res.getText(R.string.labelDivisionErreur));
                        sb.append(res.getText(R.string.labelDivisionErreur));
                    }
                    histo.add(sb.toString());
                }
            });

            boutonMul.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    double op1 = Double.parseDouble(operande1.getText().toString());
                    double op2 = Double.parseDouble(operande2.getText().toString());
                    operation.setText(res.getText(R.string.operationMul));
                    StringBuffer sb = new StringBuffer();
                    sb.append(op1).append(res.getText(R.string.labelBoutonMul)).append(op2).append("=");
                    double result = calculetteService.multiplier(op1, op2);
                    sb.append(result);
                    histo.add(sb.toString());
                    resultat.setText(res.getText(R.string.label_resultat) + " "  + result);
                }
            });

            boutonHisto.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (MainActivity.this, HistoActivity.class);
                    intent.putStringArrayListExtra("histo", histo);
                    startActivity(intent);
                }
            });

            try {
                FileInputStream histoSav = openFileInput("histosav.data");
                BufferedReader bf = new BufferedReader(new InputStreamReader(histoSav));
                String operation = null;
                while ((operation = bf.readLine()) != null) {
                    histo.add(operation);
                }
                bf.close();
                histoSav.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connexion);
        try {
            FileOutputStream histoSav = openFileOutput(("histosav.data"),MODE_PRIVATE);
            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(histoSav));
            for (String operation : histo){
                bf.write(operation+'\n');
            }
            bf.close();
            histoSav.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}