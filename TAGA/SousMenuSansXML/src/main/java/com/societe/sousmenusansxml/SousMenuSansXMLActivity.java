package com.societe.sousmenusansxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class SousMenuSansXMLActivity extends Activity {

    private final static int MENU_PARAMETRE = 1;
    private final static int MENU_QUITTER = 2;
    private final static int SOUSMENU_PARAM1 = 1001;
    private final static int SOUSMENU_PARAM2 = 1002;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Création du sous avec comme item d'entrée MENU_PARAMETRE.
        SubMenu sousMenuParam = menu.addSubMenu(0, MENU_PARAMETRE, Menu.NONE, R.string.menuparametre);

        // Ajout des items dans le sous-menu.
        sousMenuParam.add(0, SOUSMENU_PARAM1, Menu.NONE, R.string.sousmenuparametre1).setIcon(R.mipmap.ic_launcher);
        sousMenuParam.add(0, SOUSMENU_PARAM2, Menu.NONE, R.string.sousmenuparametre2).setIcon(R.mipmap.ic_launcher);

        menu.add(0, MENU_QUITTER, Menu.NONE, R.string.menuquitter);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_PARAMETRE:
                Toast.makeText(SousMenuSansXMLActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            case MENU_QUITTER:
                finish();
                return true;
            case SOUSMENU_PARAM1:
            case SOUSMENU_PARAM2:
                Toast.makeText(SousMenuSansXMLActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
