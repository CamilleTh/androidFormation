package lu.intech.calculetteavecretourparcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class EngineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Operation operation = getIntent().getParcelableExtra("operation");

        double d1 = operation.getOpe1();
        double d2 = operation.getOpe2();
        char operator = operation.getOperator();
        Intent intent = new Intent(this, MainActivity.class);

        Double result = 0.0;

        switch (operator){
           case '+':
               result = d1 + d2;
               intent.putExtra("ope","+");
               break;
            case '-':
                result = d1 - d2;
                intent.putExtra("ope","-");
                break;
            case '/':
                if(d2 == 0) {
                    setResult(RESULT_CANCELED,intent);
                    finish();
                }
                result = d1 / d2;
                intent.putExtra("ope","/");
                break;
            case  'x':
                result = d1 * d2;
                intent.putExtra("ope","*");
                break;

       }

        intent.putExtra("res",result);
        setResult(RESULT_OK,intent);
        finish();
    }
}
