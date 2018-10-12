package com.societe.calculetteavecretourapplicationcontext.autre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.societe.calculetteavecretourapplicationcontext.AddActivity;
import com.societe.calculetteavecretourapplicationcontext.DivActivity;
import com.societe.calculetteavecretourapplicationcontext.MoinsActivity;
import com.societe.calculetteavecretourapplicationcontext.MulActivity;
import com.societe.calculetteavecretourapplicationcontext.R;

public class MainActivity extends Activity {

	private Button boutonAdd, boutonMoins, boutonDiv, boutonMul;
	private EditText operande1, operande2;
	private TextView resultat, operation;

	private static final int CR_ADD;
	private static final int CR_MOINS;
	private static final int CR_DIV;
	private static final int CR_MUL;

	static{
		CR_ADD = CalculetteApplication.getAppContext().getResources().getInteger(R.integer.CR_ADD);
		CR_MOINS = CalculetteApplication.getAppContext().getResources().getInteger(R.integer.CR_MOINS);
		CR_DIV = CalculetteApplication.getAppContext().getResources().getInteger(R.integer.CR_DIV);
		CR_MUL = CalculetteApplication.getAppContext().getResources().getInteger(R.integer.CR_MUL);
	}
	
	private void activerBoutons(boolean actif){
		boutonAdd.setEnabled(actif);
		boutonMoins.setEnabled(actif);
		boutonDiv.setEnabled(actif);
		boutonMul.setEnabled(actif);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		boutonAdd = (Button)findViewById(R.id.buttonAdd);
		boutonMoins = (Button)findViewById(R.id.buttonMoins);
		boutonDiv = (Button)findViewById(R.id.buttonDiv);
		boutonMul = (Button)findViewById(R.id.ButtonMul);
		operande1 = (EditText)findViewById(R.id.operande1);
		operande2 = (EditText)findViewById(R.id.operande2);
		resultat = (TextView)findViewById(R.id.resultat);
		operation = (TextView)findViewById(R.id.operation);	
		activerBoutons(false);

		operande1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				resultat.setText(null);
				operation.setText(null);
				activerBoutons(operande1.length() > 0 && operande2.length() > 0);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});

		operande2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				resultat.setText(null);
				operation.setText(null);
				activerBoutons(operande1.length() > 0 && operande2.length() > 0);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});

		boutonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				double op1 = Double.parseDouble(operande1.getText().toString());
				double op2 = Double.parseDouble(operande2.getText().toString());
				intent.putExtra("op1", op1);
				intent.putExtra("op2",op2);
				//startActivityForResult(intent, getResources().getInteger(R.integer.CR_ADD));
				Log.v("truc", String.valueOf(CR_ADD));
				startActivityForResult(intent, CR_ADD);
			}
		});

		boutonMoins.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MoinsActivity.class);
				double op1 = Double.parseDouble(operande1.getText().toString());
				double op2 = Double.parseDouble(operande2.getText().toString());
				intent.putExtra("op1", op1);
				intent.putExtra("op2",op2);
				startActivityForResult(intent, CR_MOINS);
			}
		});

		boutonDiv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DivActivity.class);
				double op1 = Double.parseDouble(operande1.getText().toString());
				double op2 = Double.parseDouble(operande2.getText().toString());
				intent.putExtra("op1", op1);
				intent.putExtra("op2",op2);
				startActivityForResult(intent, CR_DIV);
			}
		});
		
		boutonMul.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MulActivity.class);
				double op1 = Double.parseDouble(operande1.getText().toString());
				double op2 = Double.parseDouble(operande2.getText().toString());
				intent.putExtra("op1", op1);
				intent.putExtra("op2",op2);
				startActivityForResult(intent, CR_MUL);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			
		String labelResultat = getResources().getText(R.string.labelResultat).toString();
		// Le code de requète est utilisè pour identifier l'activité appelée
		if (data != null){
		    resultat.setText(labelResultat + data.getDoubleExtra("Resultat", 0));
		}
		Log.v("MSG", "RequestCode = " + requestCode);
        if (requestCode == CR_ADD){
            operation.setText(getResources().getText(R.string.operationPlus));
            return;
        }
        if (requestCode == CR_MOINS){
            operation.setText(getResources().getText(R.string.operationMoins));
            return;
        }
        if (requestCode == CR_MUL){
            operation.setText(getResources().getText(R.string.operationMul));
            return;
        }
        if (requestCode == CR_DIV){
            operation.setText(getResources().getText(R.string.operationDiv));
            if (resultCode == RESULT_CANCELED) {
                resultat.setText(getResources().getText(R.string.labelDivisionErreur));
            }
            return;
        }
	}
}
