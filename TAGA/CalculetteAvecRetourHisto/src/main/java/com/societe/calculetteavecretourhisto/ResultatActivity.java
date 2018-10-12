package com.societe.calculetteavecretourhisto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ResultatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        double op1 = intent.getDoubleExtra("operande1", 0);
        double op2 = intent.getDoubleExtra("operande2", 0);

        Intent intent1 = new Intent();
        intent1.putExtra("resultat", op1+op2);
        setResult(RESULT_OK, intent1);
        finish();
    }


}
