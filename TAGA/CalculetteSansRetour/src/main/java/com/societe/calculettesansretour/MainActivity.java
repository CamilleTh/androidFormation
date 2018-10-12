package com.societe.calculettesansretour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText operande1;
    private EditText operande2;
    private Button boutonPlus;
    private TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operande1 = (EditText)findViewById(R.id.operande1);
        operande2 = (EditText)findViewById(R.id.operande2);
        boutonPlus = (Button)findViewById(R.id.button);
        resultat = (TextView)findViewById(R.id.textView);

		Intent i = getIntent();
		if (i.hasExtra("resultat")){
			double resultatop = i.getDoubleExtra("resultat", 0);
			resultat.setText(resultat.getText()+" " + resultatop);
		}

        boutonPlus.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String op1 = operande1.getText().toString();
                String op2 = operande2.getText().toString();
                double ope1 = Double.parseDouble(op1);
                double ope2 = Double.parseDouble(op2);

                Log.v("MSG", "operande1:" + ope1 + " -- operande2:" + ope2);

                Intent intent = new Intent(MainActivity.this, ResultatActivity.class);
                intent.putExtra("oper1", ope1);
                intent.putExtra("oper2", ope2);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        double result = intent.getDoubleExtra("resultat", 0);
        Log.v("MSG", "Resultat : " + result);
        resultat.setText("Resultat : " + result);
    }
}
