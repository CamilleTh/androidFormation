package com.societe.preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class PreferencesActivity extends PreferenceActivity {
    /** Called when the activity is first created. */

    private OnSharedPreferenceChangeListener listener;

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        //addPreferencesFromResource(R.xml.preferences);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(this);
        listener = new OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                  String key) {
                sharedPreferences.edit().commit();
                Log.v("Message"," --> préférences modifiées !" + key);
                Log.v("Message", sharedPreferences.getAll().toString());
                if (key.equals("checkBox")){
                    Toast.makeText(PreferencesActivity.this, "Clé : " + key + " - Valeur : " + sharedPreferences.getBoolean(key, true),
                            Toast.LENGTH_SHORT).show();
                } else
                if (key.equals ("editText") || key.equals("preferences_pays"))
                    Toast.makeText(PreferencesActivity.this, "Clé : " + key + " - Valeur : " + sharedPreferences.getString(key, "VIDE"),
                            Toast.LENGTH_SHORT).show();
            }
        };
        Log.v("Message", prefs.getAll().toString());
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }
}
