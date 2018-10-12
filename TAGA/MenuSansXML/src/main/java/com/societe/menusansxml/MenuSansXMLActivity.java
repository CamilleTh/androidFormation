package com.societe.menusansxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuSansXMLActivity extends Activity {

    private final static int MENU_PARAMETRE = 1;
    private final static int MENU_QUITTER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_PARAMETRE, Menu.NONE, R.string.menuparametre).setIcon(R.mipmap.ic_launcher);
        menu.add(0, MENU_QUITTER, Menu.NONE, R.string.menuquitter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_PARAMETRE:
                Toast.makeText(MenuSansXMLActivity.this, R.string.menuparametre, Toast.LENGTH_LONG).show();
                return true;
            case MENU_QUITTER:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
