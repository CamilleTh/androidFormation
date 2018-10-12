package com.societe.calculetteavecretourapplicationcontext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MulActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		double op1 = getIntent().getDoubleExtra("op1", 0);
		double op2 = getIntent().getDoubleExtra("op2", 0);
		double produit = op1 * op2;
		Log.v("MSG", "--> Mul");
		Intent i = new Intent();
		i.putExtra("Resultat", produit);
		setResult(RESULT_OK,i);
		finish();
	}
}
