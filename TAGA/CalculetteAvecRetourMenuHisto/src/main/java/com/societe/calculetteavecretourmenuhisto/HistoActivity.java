package com.societe.calculetteavecretourmenuhisto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class HistoActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ArrayList<String> histo = intent.getStringArrayListExtra("histo");
        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, histo);
        setListAdapter(adaptateur);
    }
}
