package lu.intech.calculettebindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lu.intech.calculettebindservice.services.EngineService;
import lu.intech.calculettebindservice.services.Result;

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

    private EngineService engineService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindview();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            engineService = null;
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            engineService = ((EngineService.BindService)service).getService();
        }
    };

    private void bindview(){
        number1 = (EditText)findViewById(R.id.number);
        number2 = (EditText)findViewById(R.id.number2);
        result = (TextView)findViewById(R.id.result);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        ope = (TextView) findViewById(R.id.ope);
        result = (TextView)findViewById(R.id.result);

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

        Intent intent = new Intent(this,EngineService.class);
        if(bindService(intent, connection, Context.BIND_AUTO_CREATE)){
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    double    d1 = Double.parseDouble(number1.getText().toString());
                    double    d2 = Double.parseDouble(number2.getText().toString());

                    Log.v(TAG,d1+ "+" +d2);

                    Result r = engineService.add(d1,d2);
                    result.setText(r.getResultat()+"");
                    ope.setText(r.getOperation()+"");


                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double    d1 = Double.parseDouble(number1.getText().toString());
                    double    d2 = Double.parseDouble(number2.getText().toString());

                    Result r = engineService.sub(d1,d2);
                    result.setText(r.getResultat()+"");
                    ope.setText(r.getOperation()+"");


                }
            });

            mul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double    d1 = Double.parseDouble(number1.getText().toString());
                    double    d2 = Double.parseDouble(number2.getText().toString());

                    Result r = engineService.mul(d1,d2);
                    result.setText(r.getResultat()+"");
                    ope.setText(r.getOperation()+"");

                }
            });

            div.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double    d1 = Double.parseDouble(number1.getText().toString());
                    double    d2 = Double.parseDouble(number2.getText().toString());

                    try{
                        Result r = engineService.div(d1,d2);
                        result.setText(r.getResultat()+"");
                        ope.setText(r.getOperation()+"");
                    }catch (ArithmeticException e){
                        result.setText("");
                        ope.setText(R.string.divbyzero);
                    }
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void enabledButton(boolean b){
        add.setEnabled(b);
        sub.setEnabled(b);
        mul.setEnabled(b);
        div.setEnabled(b);
    }
}
