package lu.intech.calculetteavecretour.operator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import lu.intech.calculetteavecretour.MainActivity;

public class DivActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();

        Double op1 = thisIntent.getDoubleExtra("ope1", 0);
        Double op2 = thisIntent.getDoubleExtra("ope2",0);

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("ope","-");

        if(op2 == 0)  setResult(RESULT_CANCELED,intent);
        else {
            setResult(RESULT_OK,intent);
            Double result = op1 / op2;
            intent.putExtra("res",result);

        }


        finish();
    }
}
