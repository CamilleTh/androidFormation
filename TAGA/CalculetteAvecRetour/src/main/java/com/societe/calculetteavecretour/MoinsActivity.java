package com.societe.calculetteavecretour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MoinsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		double op1 = getIntent().getDoubleExtra("op1", 0);
		double op2 = getIntent().getDoubleExtra("op2", 0);
		double diff = op1 - op2;
		Intent i = new Intent();
		i.putExtra("Resultat", diff);
		setResult(RESULT_OK,i);
		finish();
	}
}
