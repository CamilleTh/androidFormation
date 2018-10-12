package com.societe.sousmenuxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class SousMenuXMLActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
//        menu.findItem(R.id.sous_parametre1).setIcon(R.mipmap.ic_launcher);
//        menu.findItem(R.id.sous_parametre2).setIcon(R.mipmap.ic_launcher);

        SubMenu sousMenuParam = menu.findItem(R.id.menu_parametre).getSubMenu();
        sousMenuParam.getItem(0).setIcon(R.mipmap.ic_launcher);
        sousMenuParam.getItem(1).setIcon(R.mipmap.ic_launcher);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_parametre:
                Toast.makeText(SousMenuXMLActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_quitter:
                finish();
                return true;
            case R.id.sous_parametre1:
            case R.id.sous_parametre2:
                Toast.makeText(SousMenuXMLActivity.this, item.getTitle(),
                        Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
