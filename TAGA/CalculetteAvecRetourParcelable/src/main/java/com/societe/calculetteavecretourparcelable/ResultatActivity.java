package com.societe.calculetteavecretourparcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.societe.calculetteavecretourparcelable.metier.Operation;

public class ResultatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Operation op = intent.getParcelableExtra("operation");
        Intent data = new Intent();
        switch (op.getOperateur()){
            case '+':
                data.putExtra("Resultat", op.getOperande1()+op.getOperande2());
                setResult(RESULT_OK,data);
                break;
            case '-':
                data.putExtra("Resultat", op.getOperande1()-op.getOperande2());
                setResult(RESULT_OK,data);
                break;
            case '*':
                data.putExtra("Resultat", op.getOperande1()*op.getOperande2());
                setResult(RESULT_OK,data);
                break;
            case '/':
                if (op.getOperande2() == 0) {
                    setResult(RESULT_CANCELED);
                } else {
                    data.putExtra("Resultat", op.getOperande1()/op.getOperande2());
                    setResult(RESULT_OK,data);
                }
                break;
        }
        finish();
    }
}
