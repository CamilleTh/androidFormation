package lu.intech.calculetteavecretour.operator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.logging.Logger;

import lu.intech.calculetteavecretour.MainActivity;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();

        Double op1 = thisIntent.getDoubleExtra("ope1", 0);
        Double op2 = thisIntent.getDoubleExtra("ope2",0);


        Double result = op1 + op2;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("res",result);
        intent.putExtra("ope","+");
        setResult(RESULT_OK,intent);

        /*try {
            Thread.sleep(60000);
        }catch (InterruptedException e){

        }*/

        finish();
    }
}
