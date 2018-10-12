package com.societe.calculetteavecretour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DivActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		double op1 = intent.getDoubleExtra("op1", 0);
		double op2 = intent.getDoubleExtra("op2", 0);


		if (op2 == 0) {
			setResult(RESULT_CANCELED);
		} else {
			Intent i = new Intent();
			double quotient = op1 / op2;
			i.putExtra("Resultat", quotient);
			setResult(RESULT_OK,i);
		}
		finish();
	}
}
