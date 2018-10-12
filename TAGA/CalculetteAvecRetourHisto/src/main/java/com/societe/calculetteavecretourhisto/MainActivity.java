package com.societe.calculetteavecretourhisto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private EditText operande1, operande2;
    private Button boutonPlus, boutonHisto;
    private TextView resultat;

    private double ope1;
    private double ope2;
    private ArrayList<String> histo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operande1 = (EditText) findViewById(R.id.operande1);
        operande2 = (EditText) findViewById(R.id.operande2);
        boutonPlus = (Button) findViewById(R.id.boutonPlus);
        boutonHisto = (Button) findViewById(R.id.boutonHisto);
        resultat = (TextView) findViewById(R.id.resultat);
        histo = new ArrayList<String>();

        boutonPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ope1 = Double.parseDouble(operande1.getText().toString());
                ope2 = Double.parseDouble(operande2.getText().toString());
                Log.v("MSG", ope1 + " --- " + ope2);
                Intent intent = new Intent(MainActivity.this, ResultatActivity.class);
                intent.putExtra("operande1", ope1);
                intent.putExtra("operande2", ope2);
                startActivityForResult(intent, R.integer.codeResultat);
                //resultat.setText("" + resultat.getText()+(ope1+ope2));
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        double result = data.getDoubleExtra("resultat",0);
        String labelResultat = getResources().getText(R.string.label_resultat).toString();
        StringBuffer bf = new StringBuffer();
        bf.append(ope1);
        switch(requestCode){ //code pédagogique
            case R.integer.codeResultat:
                switch(resultCode) { // Branchement sur la valeur retournée
                    case RESULT_OK:
                        resultat.setText(labelResultat + result);
                        bf.append('+').append(ope2).append('=').append(result);
                        break;
                }
        }
        histo.add(bf.toString());
        Log.v("Message:", "Ajout de l'opération :" + bf);
    }
}














