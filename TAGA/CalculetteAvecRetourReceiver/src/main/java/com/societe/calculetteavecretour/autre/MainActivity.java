package com.societe.calculetteavecretour.autre;

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

import com.societe.calculetteavecretour.AddActivity;
import com.societe.calculetteavecretour.DivActivity;
import com.societe.calculetteavecretour.MoinsActivity;
import com.societe.calculetteavecretour.MulActivity;
import com.societe.calculetteavecretour.R;

public class MainActivity extends Activity {

	private Button boutonAdd, boutonMoins, boutonDiv, boutonMul;
	private EditText operande1, operande2;
	private TextView resultat, operation;
	
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
				Log.v("truc", String.valueOf(R.integer.CR_ADD));
				startActivityForResult(intent, R.integer.CR_ADD);
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
				startActivityForResult(intent, R.integer.CR_MOINS);
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
				startActivityForResult(intent, R.integer.CR_DIV);
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
				startActivityForResult(intent, R.integer.CR_MUL);
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
		switch (requestCode){
			case R.integer.CR_ADD :
				operation.setText(getResources().getText(R.string.operationPlus));
				break;
			case R.integer.CR_MOINS : 
				operation.setText(getResources().getText(R.string.operationMoins));
				break;
			case R.integer.CR_MUL :
				operation.setText(getResources().getText(R.string.operationMul));
				break;
			case R.integer.CR_DIV :
				operation.setText(getResources().getText(R.string.operationDiv));
				if (resultCode == RESULT_CANCELED) {
					resultat.setText(getResources().getText(R.string.labelDivisionErreur));
				}
				break;
		}
	}
}
