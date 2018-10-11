package lu.intech.calculetteintentservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lu.intech.calculetteintentservice.services.EngineService;

public class MainActivity extends Activity {


    private String TAG = "MainActivityTag";
    private EditText number1;
    private EditText number2;
    private Button add;
    private Button sub;
    private Button mul;
    private Button div;
    private TextView result;
    private TextView ope;

    private boolean number1Ok = false;
    private boolean number2Ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindview();
    }

    private void bindview(){
        number1 = (EditText)findViewById(R.id.number);
        number2 = (EditText)findViewById(R.id.number2);
        result = (TextView)findViewById(R.id.result);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        ope = (TextView) findViewById(R.id.ope);

        enabledButton(false);

        number1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number1Ok = s.length() > 0;
                enabledButton(number1Ok && number2Ok);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        number2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number2Ok = s.length() > 0;
                enabledButton(number1Ok && number2Ok);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double    d1 = Double.parseDouble(number1.getText().toString());
                double    d2 = Double.parseDouble(number2.getText().toString());

                Log.v(TAG,d1+ "+" +d2);

                Intent intent = new Intent(MainActivity.this, EngineService.class);
                Operation op = new Operation(d1,d2,'+');
                intent.putExtra("operation" ,op);

                startService(intent);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double    d1 = Double.parseDouble(number1.getText().toString());
                double    d2 = Double.parseDouble(number2.getText().toString());


                Intent intent = new Intent(MainActivity.this, EngineService.class);
                Operation op = new Operation(d1,d2,'-');
                intent.putExtra("operation" ,op);

                startService(intent);

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double    d1 = Double.parseDouble(number1.getText().toString());
                double    d2 = Double.parseDouble(number2.getText().toString());

                Intent intent = new Intent(MainActivity.this, EngineService.class);
                Operation op = new Operation(d1,d2,'x');
                intent.putExtra("operation" ,op);

                startService(intent);

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double    d1 = Double.parseDouble(number1.getText().toString());
                double    d2 = Double.parseDouble(number2.getText().toString());

                Intent intent = new Intent(MainActivity.this, EngineService.class);
                Operation op = new Operation(d1,d2,'/');
                intent.putExtra("operation" ,op);

                startService(intent);

            }
        });

    }

    private void enabledButton(boolean b){
        add.setEnabled(b);
        sub.setEnabled(b);
        mul.setEnabled(b);
        div.setEnabled(b);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.v("TOTO","OnNewIntent");
        ope.setText(intent.getStringExtra("ope"));
        Double d = intent.getDoubleExtra("res", 0);
        result.setText(d.toString());
    }
}
