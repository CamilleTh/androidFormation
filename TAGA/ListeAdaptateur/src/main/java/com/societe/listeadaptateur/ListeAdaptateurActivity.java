package com.societe.listeadaptateur;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListeAdaptateurActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView lv = (ListView)findViewById(R.id.listView1);
        String[] valeurs = getResources().getStringArray(R.array.nombres);
        ArrayAdapter<String> adaptateur = new ArrayAdapter<String>(this,R.layout.liste_item, valeurs);
        lv.setAdapter(adaptateur);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Builder adb = new Builder(ListeAdaptateurActivity.this);
                // le titre de la boite de dialogue
                adb.setTitle("Sélection Item");
                // le message de la boite
                adb.setMessage("Votre choix : "+ ((TextView) view).getText());
                // la boite possède un bouton OK
                adb.setPositiveButton("Ok", null);
                // Affichage la boite de dialogue
                adb.show();
            }
        });
    }
}
