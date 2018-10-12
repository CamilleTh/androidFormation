package com.societe.curseuradaptateur;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

public class CurseurAdaptateurActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        // la colonne recherchée
        String[] colonnes=new String[] { ContactsContract.Contacts.DISPLAY_NAME};

        int[] vers = new int[] { android.R.id.text1 };

        // création de l'adaptateur avec le curseur sur la donnée souhaitée
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor, colonnes, vers,0);
        this.setListAdapter(mAdapter);
    }

}
