package com.societe.menuflottant;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MenuFlottantActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.societe.menuflottant.R.layout.main);
        // la vue est enregistrée comme pouvant déclencher
        // un context_menu contextuel.
        registerForContextMenu(findViewById(com.societe.menuflottant.R.id.textView1));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        switch (v.getId()){
            // Pour chaque vue ayant un context_menu contextuel,
            // son context_menu contextuel est construit de la même
            // manière qu'un sous-context_menu.
            case com.societe.menuflottant.R.id.textView1:
                getMenuInflater().inflate(com.societe.menuflottant.R.menu.context_menu, menu);
                menu.setHeaderTitle(com.societe.menuflottant.R.string.app_name);
                menu.setHeaderIcon(com.societe.menuflottant.R.mipmap.ic_launcher);
			/*context_menu.add(0, MENU_CONTEXTUEL1, Menu.NONE, R.string.menucontextuel1);
			context_menu.add(0, MENU_CONTEXTUEL2, Menu.NONE, R.string.menucontextuel2);
			context_menu.add(0, MENU_CONTEXTUEL3, Menu.NONE, R.string.menucontextuel3);
			context_menu.add(0, MENU_CONTEXTUEL4, Menu.NONE, R.string.menucontextuel4);
			context_menu.add(0, MENU_CONTEXTUEL5, Menu.NONE, R.string.menucontextuel5);
			context_menu.add(0, MENU_CONTEXTUEL6, Menu.NONE, R.string.menucontextuel6);*/
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Chaque item d'un context_menu contextuel possède
        // un identifiant unique sur lequel un
        // traitement est associé.
        switch (item.getItemId()){
            case com.societe.menuflottant.R.id.context_menu1:
            case com.societe.menuflottant.R.id.context_menu2:
                Toast.makeText(MenuFlottantActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
