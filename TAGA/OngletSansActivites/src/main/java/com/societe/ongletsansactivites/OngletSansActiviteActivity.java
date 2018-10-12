package com.societe.ongletsansactivites;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class OngletSansActiviteActivity extends Activity {

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Onglet 1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Onglet 1");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Onglet 2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Onglet 2");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Onglet 3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Onglet 3");
        host.addTab(spec);
    }
}
