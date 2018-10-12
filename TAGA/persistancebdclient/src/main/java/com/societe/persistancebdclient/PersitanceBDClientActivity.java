package com.societe.persistancebdclient;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PersitanceBDClientActivity extends Activity {

    private ArrayList<Stagiaire> stagiaires;

    private static final int COLONNE_ID_INDEX = 0;
    private static final int COLONNE_NOM_INDEX = 1;
    private static final int COLONNE_PRENOM_INDEX = 2;
    private static ContentResolver cr;
    private  ListView lv;
    private ArrayAdapter<Stagiaire> stagaiaireAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cr = getContentResolver();
        Uri uri = Uri.parse("content://societe.com.android.provider.noms/noms");
        Cursor curseur = cr.query(uri, null, null, null, null);
        stagiaires = cursorToStagiaires(curseur);
        Log.v ("Provider",stagiaires.toString());
        lv = (ListView)findViewById(R.id.listView1);
        stagaiaireAdapter = new ArrayAdapter<Stagiaire>(this, 	android.R.layout.simple_list_item_1, stagiaires);
        lv.setAdapter(stagaiaireAdapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
                Builder adb = new Builder(PersitanceBDClientActivity.this);
                Stagiaire s = stagiaires.get(position);
                // le titre de la boite de dialogue
                adb.setTitle("ID : " + s.getId());
                // le message de la boite
                adb.setMessage("NOM : "+ s.getNom() + "\nPRENOM : " + s.getPrenom());
                // la boite possède un bouton OK
                adb.setPositiveButton("Ok", null);
                // Affichage la boite de dialogue
                adb.show();
                cr.delete(Uri.parse("content://societe.com.android.provider.noms/noms/"+s.getId()), null, null);
                stagaiaireAdapter.remove(s);
                lv.invalidate();
            }
        });
    }

    private ArrayList<Stagiaire> cursorToStagiaires(Cursor c) {
        // Si la requête ne renvoie pas de résultat
        if (c.getCount() == 0)
            return new ArrayList<Stagiaire>();

        ArrayList<Stagiaire> stagiaires = new ArrayList<Stagiaire>(c.getCount());
        c.moveToFirst();
        do {
            Stagiaire stagiaire = new Stagiaire(c.getInt(COLONNE_ID_INDEX),
                    c.getString(COLONNE_NOM_INDEX),
                    c.getString(COLONNE_PRENOM_INDEX));
            stagiaires.add(stagiaire);
        } while (c.moveToNext());
        // Ferme le curseur pour libérer les ressources
        c.close();
        return stagiaires;
    }

	/*  private Stagiaire cursorToStagiaire(Cursor c) {
		// Si la requête ne renvoie pas de résultat
		if (c.getCount() == 0)
			return null;

		Stagiaire stagiaire = new Stagiaire(c.getInt(COLONNE_ID_INDEX),
				c.getString(COLONNE_NOM_INDEX),
				c.getString(COLONNE_PRENOM_INDEX));
		c.close();
		return stagiaire;
	}*/
}
