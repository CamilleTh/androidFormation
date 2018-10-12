package com.societe.menuactionmodemul;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuActionModeMulActivity extends Activity {

    private SelectionAdapter selectionAdapter;
    private StagiaireAdapter stagiaireAdapter;
    private ListView listStagiaires;
    private ArrayList<Stagiaire> stagiaires;
    private int nbSelectionnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initListeStagiaires();
        afficherListStagiaires();
    }

    private void initListeStagiaires(){
        String[] st = getResources().getStringArray(R.array.stagiaires);
        if (stagiaires == null)
            stagiaires = new ArrayList<>();
        for (String nomStagiaire : st){
            stagiaires.add(new Stagiaire(nomStagiaire, false));
        }
    }

    private void afficherListStagiaires() {
        stagiaireAdapter = new StagiaireAdapter(this, R.layout.stagiaire_item, stagiaires);
        selectionAdapter = new SelectionAdapter(this, R.layout.stagiaire_info, stagiaires);

        listStagiaires = (ListView) findViewById(R.id.listeStagiaires);
        listStagiaires.setAdapter(stagiaireAdapter);
        listStagiaires.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listStagiaires.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StagiaireAdapter sa = (StagiaireAdapter) parent.getAdapter();
                Log.v("MSG", sa.getNomStagiaire(view));
                Builder adb = new Builder(MenuActionModeMulActivity.this);
                // le titre de la boite de dialogue
                adb.setTitle("Sélection Stagiaire");
                // le message de la boite
                adb.setMessage("Votre choix : " + sa.getNomStagiaire(view));
                // la boite possède un bouton OK
                adb.setPositiveButton("Ok", null);
                // Affichage la boite de dialogue
                adb.show();
            }
        });

        listStagiaires.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            private boolean toutSelectionne = false;

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                stagiaires.get(position).setSelectionne(checked);
                listStagiaires.setSelection(position);
                nbSelectionnes = checked == true ? nbSelectionnes+1:nbSelectionnes-1;
                if (nbSelectionnes > 0)
                    actionMode.setTitle(String.valueOf(nbSelectionnes));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.stagiaire_menu, menu);
                listStagiaires.setAdapter(selectionAdapter);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tous :
                        if (toutSelectionne == false) {
                            nbSelectionnes = 0;
                            for (Stagiaire stagiaire : stagiaires) {
                                int position = stagiaires.indexOf(stagiaire);
                                listStagiaires.setItemChecked(position, true);
                                actionMode.setTitle(String.valueOf(nbSelectionnes));
                                stagiaire.setSelectionne(true);
                            }
                            toutSelectionne = true;
                            menuItem.setIcon(android.R.drawable.ic_notification_clear_all);
                        } else {
                            nbSelectionnes = stagiaires.size();
                            for (Stagiaire stagiaire : stagiaires) {
                                int position = stagiaires.indexOf(stagiaire);
                                listStagiaires.setItemChecked(position, false);
                                stagiaire.setSelectionne(false);
                            }
                            toutSelectionne = false;
                            menuItem.setIcon(android.R.drawable.ic_menu_add);
                        }
                        selectionAdapter.notifyDataSetChanged();
                        return true;
                    case R.id.delete:
                        nbSelectionnes = 0;
                        Iterator<Stagiaire> iter = stagiaires.iterator();
                        while (iter.hasNext()){
                            if (iter.next().isSelectionne())
                                iter.remove();
                        }
                        actionMode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                listStagiaires.setAdapter(stagiaireAdapter);
                for (Stagiaire stagiaire : stagiaires){
                        stagiaire.setSelectionne(false);
                }
                toutSelectionne = false;
                MenuActionModeMulActivity.this.nbSelectionnes = 0;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_quitter:
                finish();
                return true;
            case R.id.reinit:
                stagiaires.clear();
                initListeStagiaires();
                stagiaireAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
