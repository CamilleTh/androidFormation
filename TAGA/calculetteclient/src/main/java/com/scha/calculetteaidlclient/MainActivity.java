package com.scha.calculetteaidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scha.calculetteaidl.Calculetteaidl;

public class MainActivity extends Activity {

    private  float diff;
    private Operation op;
    private Calculetteaidl servCalc;

    private ServiceConnection connexion = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // servCalc = ((ServiceCalc.ServiceLiaison)service).getService();
            Log.v("scha", "OnServiceConnect");
            servCalc = Calculetteaidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            servCalc = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind vers le service


        Button boutonPlus = (Button)findViewById(R.id.plusButton);
        Button boutonMoins = (Button)findViewById(R.id.moinsbutton);
        Button boutonDivise = (Button)findViewById(R.id.diviseButton);
        TextView resView = (TextView) findViewById(R.id.resultat);
        //receveur = new MyReceiver(resView);

        Intent intent = new Intent();
        intent.setAction("monservice");
        intent.setPackage("com.scha.calculetteaidl");
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        if(bindService(intent,connexion, Context.BIND_AUTO_CREATE))
        {
            Log.v("SCHA-main", "Liaison avec le service OK");
        }
        else {
            Log.v("SCHA-main", "Liaison avec le service KO");
        }

        boutonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("SCHA-main", "Click plus");
                EditText num1Saisie = (EditText) findViewById(R.id.num1);
                EditText num2Saisie = (EditText) findViewById(R.id.num2);
                String Saisienum1Str = num1Saisie.getText().toString();
                String Saisienum2Str = num2Saisie.getText().toString();

                if( !Saisienum1Str.isEmpty() && !Saisienum1Str.isEmpty())
                    op = new Operation(Float.parseFloat(Saisienum1Str), Float.parseFloat(Saisienum2Str), Operation.OP_PLUS);
                else
                    op = new Operation(0,0,Operation.OP_PLUS);

                //intent.putExtra("operation",op);
                Log.v("SCHA-main", "Evoie de la demande au service");
                //Demande de calcul au service
                //Intent intent = new Intent(MainActivity.this,ServiceCalc.class);

                float somme = 0;
                try {
                    somme = servCalc.add(op.operand1,op.operand2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                TextView resView = (TextView) findViewById(R.id.resultat);
                resView.setText(Float.toString(somme));

            }
        });


        boutonMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("SCHA-main", "Click moins");
                EditText num1Saisie = (EditText) findViewById(R.id.num1);
                EditText num2Saisie = (EditText) findViewById(R.id.num2);
                String Saisienum1Str = num1Saisie.getText().toString();
                String Saisienum2Str = num2Saisie.getText().toString();

                if( !Saisienum1Str.isEmpty() && !Saisienum1Str.isEmpty())
                    op = new Operation(Float.parseFloat(Saisienum1Str), Float.parseFloat(Saisienum2Str), Operation.OP_MOINS);
                else
                    op = new Operation(0,0,Operation.OP_MOINS);

                Log.v("SCHA-main", "Lancement du thread");
                //A faire si le temps de traitement du service est long pour ne pas bloquer l'interface graphique
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("SCHA-thread", "Thread : demande soustraction");
                        try {
                            diff = servCalc.sous(op.operand1,op.operand2);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        //permet de revenir dans l'interface graphique (activity) pour la modifier
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.v("SCHA-thread", "retour sur UI");
                                TextView resView = (TextView) findViewById(R.id.resultat);
                                resView.setText(Float.toString(diff));
                            }
                        });
                    }
                }).start();


            }
        });

        boutonDivise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources res = getResources();
                String MonAction = res.getString(R.string.action_name);
                Intent intent = new Intent(MonAction);
                EditText num1Saisie = (EditText) findViewById(R.id.num1);
                EditText num2Saisie = (EditText) findViewById(R.id.num2);
                String Saisienum1Str = num1Saisie.getText().toString();
                String Saisienum2Str = num2Saisie.getText().toString();
                Operation op;
                if( Saisienum1Str.isEmpty() || Saisienum1Str.isEmpty())
                    op = new Operation(0,0,Operation.OP_DIVISE);
                else
                    op = new Operation(Float.parseFloat(Saisienum1Str), Float.parseFloat(Saisienum2Str), Operation.OP_DIVISE);

                intent.putExtra("operation",op);

                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                //sendOrderedBroadcast(intent,null);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        Resources res = getResources();
        String MonAction = res.getString(R.string.action_name);
        IntentFilter filterIntent = new IntentFilter(MonAction);
        //Pour un envoie au process local uniquement
        //LocalBroadcastManager.getInstance(this).registerReceiver(receveur, filterIntent);
        //registerReceiver(receveur,filterIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connexion);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView resView = (TextView) findViewById(R.id.resultat);
        String res= "not init";
        switch (requestCode) {
            case R.integer.codeRetourCalculette:
                if (data != null) {
                    res = data.getStringExtra("res");
                } else
                {
                    resView.setText("Pas d'intent");
                    return;
                }
                switch (resultCode) {
                    case RESULT_OK:
                        resView.setText(res);
                        return;
                    case RESULT_CANCELED:
                        String err = "Erreur : ";
                        err = err.concat(res);
                        resView.setText(err);
                        return;
                    default:
                        resView.setText("Code d'erreur inconnu");
                        return;
                }
            default:
                resView.setText("Code d'erreur inconnu");
                return;
        }
    }


}