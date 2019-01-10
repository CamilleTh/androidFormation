package lu.intech.calculettesansretour;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ResultatActivity extends Activity {

    private String TAG = "ResultatActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);

        Intent thisIntent = getIntent();

        Double op1 = thisIntent.getDoubleExtra("ope1", 0);
        Double op2 = thisIntent.getDoubleExtra("ope2",0);

        Double result = op1 + op2;
        Log.v(TAG,result.toString());

        Intent intent = new Intent(this, CalculetteActivity.class);
        intent.putExtra("res",result);

        if(CalculetteActivity.MODE == 1){
            // MODE 1
            startActivity(intent);
        }else {  // MODE 2, 3
            if(result < 0)  setResult(RESULT_CANCELED,intent);
            else setResult(RESULT_OK,intent);
            finish();
        }




    }






}
