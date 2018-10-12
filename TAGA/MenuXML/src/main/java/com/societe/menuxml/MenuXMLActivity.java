package com.societe.menuxml;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuXMLActivity extends Activity {

    private int compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v("MSG", "onCreateOptionsMenu()");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_parametre:
                Toast.makeText(MenuXMLActivity.this, R.string.menuparametre, Toast.LENGTH_LONG).show();
                compteur++;
                invalidateOptionsMenu();
                return true;
            case R.id.menu_quitter:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        Log.v("MSG", "onPrepareOptionsMenu()");
//        menu.findItem(R.id.menu_parametre).setTitle(R.string.menuparametre)
        String texteMenu = getResources().getString(R.string.menuparametre);
        menu.findItem(R.id.menu_parametre).setTitle(texteMenu + " " + compteur);
        return true;
    }
}
