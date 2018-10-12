package com.societe.persistancebd;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.societe.persistancebd.bean.Stagiaire;
import com.societe.persistancebd.bean.StagiaireDAO;

import java.util.ArrayList;

public class PersistanceBDActivity extends Activity {
    private StagiaireDAO stagiaireDAO;

    private ArrayList<Stagiaire> stagiaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Crée une instance de notre adaptateur
        stagiaireDAO = new StagiaireDAO(this);
        // Ouvre la base de données
        stagiaireDAO.open();

        // Interaction avec le bouton d'alimentation de la base de données
        Button boutonAlimBase = (Button) findViewById(R.id.bouton_alimente_base_donnee);
        boutonAlimBase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alimenterBase();
                alimenterListeStagiaires();
            }
        });

        // Alimentation de la liste des Stagiaires en bases de données
        alimenterListeStagiaires();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fermeture de la base.
        stagiaireDAO.close();
    }

    private void alimenterListeStagiaires() {
        // Récupère les données
        stagiaires = stagiaireDAO.getAllStagiaires();
        Log.v("STAGIAIRES", stagiaires.toString());
        // Met à jour l'interface utilisateur
        ListView liste = (ListView) findViewById(R.id.list_stagiaires);
        ArrayAdapter<Stagiaire> StagiairesAdapter = new ArrayAdapter<Stagiaire>(this,
                android.R.layout.simple_list_item_1, stagiaires);
        liste.setAdapter(StagiairesAdapter);
        liste.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
                Builder adb = new Builder(PersistanceBDActivity.this);
                Stagiaire s = stagiaires.get(position);
                // le titre de la boite de dialogue
                adb.setTitle("ID : " + s.getId());
                // le message de la boite
                adb.setMessage("NOM : "+ s.getNom() + "\nPRENOM : " + s.getPrenom());
                // la boite possède un bouton OK
                adb.setPositiveButton("Ok", null);
                // Affichage la boite de dialogue
                adb.show();
            }
        });
    }

    /**
     * Alimente la base de données SQLite avec quelques stagiaires.
     */
    private void alimenterBase() {
        Stagiaire s1 = new Stagiaire("Nom1", "Prenom1");
        Stagiaire s2 = new Stagiaire("Nom2", "Prenom2");
        Stagiaire s3 = new Stagiaire("Nom3", "Prenom3");
        stagiaireDAO.insertStagiaire(s1);
        stagiaireDAO.insertStagiaire(s2);
        stagiaireDAO.insertStagiaire(s3);
        s1 = stagiaireDAO.getStagiaire(1);
        Log.v("MSG", s1.getNom());
    }
}