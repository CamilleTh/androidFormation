package lu.intech.calculettesansretour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Logger;

public class CalculetteActivity extends Activity {

    private String TAG = "CalculetteActivityTag";
    private EditText number1;
    private EditText number2;
    private Button calcul;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculette);
        bindView();

        // MODE 1
        Double res = getIntent().getDoubleExtra("res", 0);
        Log.v(TAG,res.toString());
        result.setText(res.toString());


        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double d1;
                double d2;
                try {
                    d1 = Double.parseDouble(number1.getText().toString());
                    d2 = Double.parseDouble(number2.getText().toString());
                }catch (NumberFormatException e) {
                    d1 = 0;
                    d2 = 0;
                }

                Log.v(TAG,d1+ "+" +d2);

                Intent intent = new Intent(CalculetteActivity.this, ResultatActivity.class);
                intent.putExtra("ope1",d1);
                intent.putExtra("ope2",d2);


                // MODE 2
                //startActivityForResult(intent, R.integer.codeRetour);

                // MODE 1
                startActivity(intent);
            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case R.integer.codeRetour:
                switch (resultCode){
                    case RESULT_OK:
                        Double d = data.getDoubleExtra("res", 0);
                        result.setText(d.toString());
                        break;
                    case RESULT_CANCELED :
                        result.setText("ERROR");
                }
            break;

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // MODE 3 AVEC SingleTask dans Manifest
        super.onNewIntent(intent);
        Double res = intent.getDoubleExtra("res", 0);
        Log.v(TAG,res.toString());
        result.setText(res.toString());
    }*/

    private void bindView(){
        number1 = (EditText)findViewById(R.id.number);
        number2 = (EditText)findViewById(R.id.number2);
        result = (TextView)findViewById(R.id.result);
        calcul = (Button)findViewById(R.id.button);

    }


}