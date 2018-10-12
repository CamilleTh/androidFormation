package com.societe.preferencescategorie;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferencesCategorieActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
