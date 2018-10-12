package com.societe.ongletavecactivites;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class OngletsAvecActivitesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        Drawable icone = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            icone = getDrawable(R.drawable.ic_onglet);
        } else {
            Resources res = getResources();
            icone = res.getDrawable(R.drawable.ic_onglet);
        }

        Log.v("MSG", icone == null ? "NULL" : "OK !!");
        Log.v("MSG", tabHost == null ? "NULL tabHost" : "OK  tabHost!!");

          // Les onglets de l'activité

        TabHost.TabSpec spec;  // Objet TabSpec réutilisable pour chaque onglet
        Intent intent;  // Objet Intent réutilisable pour chaque onglet

        // Création de l'Intent pour lancer l'activité associée à l'onglet
        intent = new Intent(this, ArtistesActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        // Initialise TabSpec pour chaque onglet et l'ajoute à TabHost
        spec = tabHost.newTabSpec("artistes");
        spec.setIndicator("Artistes", icone);
        spec.setContent(intent);

        tabHost.addTab(spec);

        // Même chose pour les autres onglets
        intent = new Intent(this, AlbumsActivity.class);
        spec = tabHost.newTabSpec("albums").setIndicator("Albums",
                icone)
                .setContent(intent);

        tabHost.addTab(spec);

        intent = new Intent(this, ChansonsActivity.class);
        spec = tabHost.newTabSpec("Chansons").setIndicator("Chansons", icone)
                .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(2); // se positionne sur l'onglet 3

    }
}
