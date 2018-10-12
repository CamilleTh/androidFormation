package com.societe.calculette1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                int ope1 = Integer.parseInt(operande1.getText().toString());
                int ope2 = Integer.parseInt(operande2.getText().toString());
                Log.v("MSG", ope1 + " --- " + ope2);
                resultat.setText("" + resultat.getText()+(ope1+ope2));
            }
        });

    }
}
