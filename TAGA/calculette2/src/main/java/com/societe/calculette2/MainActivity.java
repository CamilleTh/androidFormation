package com.societe.calculette2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.societe.metier.Operandes;

public class MainActivity extends Activity {

    private EditText operande1, operande2;
    private Button boutonPlus;
    private TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operande1 = (EditText)findViewById(R.id.operande1);
        operande2 = (EditText)findViewById(R.id.operande2);
        boutonPlus = (Button)findViewById(R.id.boutonPlus);
        resultat = (TextView) findViewById(R.id.resultat);

        boutonPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                double ope1 = Double.parseDouble(operande1.getText().toString());
                double ope2 = Double.parseDouble(operande2.getText().toString());
                Log.v("MSG", ope1 + " --- " + ope2);
                Intent intent = new Intent(MainActivity.this, ResultatActivity.class);
                Operandes ops = new Operandes(ope1,ope2);
//                intent.putExtra("operande1", ope1);
//                intent.putExtra("operande2", ope2);
                intent.putExtra("ops", ops);
                startActivity(intent);
                //resultat.setText("" + resultat.getText()+(ope1+ope2));
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("resultat")){
            resultat.setText("" + resultat.getText()+(intent.getDoubleExtra("resultat",0)));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra("resultat")){
            resultat.setText("" + resultat.getText()+(intent.getDoubleExtra("resultat",0)));
        }
    }
}
