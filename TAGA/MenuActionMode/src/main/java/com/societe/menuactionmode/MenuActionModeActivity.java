package com.societe.menuactionmode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActionModeActivity extends Activity {

    private ActionMode actionMode;
    private TextView texte;
    private int compteur;

    private Callback callback = new Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            Log.v("MSG", "onCreateActionMode - " + (actionMode == MenuActionModeActivity.this.actionMode));
            actionMode.getMenuInflater().inflate(R.menu.menuactionmode,menu);
            actionMode.setSubtitle("machin");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            Log.v("MSG", "onPrepareActionMode - " + (actionMode == MenuActionModeActivity.this.actionMode));
            actionMode.setTitle("TRUC " + compteur);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Log.v("MSG", "onActionItemClicked - " + (actionMode == MenuActionModeActivity.this.actionMode));
            switch (menuItem.getItemId()){
                case R.id.action :
                case R.id.build :
                    actionMode.finish();
                    Toast.makeText(MenuActionModeActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            Log.v("MSG", "Destroy - " + (actionMode == MenuActionModeActivity.this.actionMode));
            compteur = 0;
            MenuActionModeActivity.this.actionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        texte = (TextView)findViewById(R.id.textView1);
        texte.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.v("MSG","clic");
                if (actionMode == null) {
                    actionMode = MenuActionModeActivity.this.startActionMode(callback);
                }
                compteur++;
                // Start the CAB using the ActionMode.Callback defined above
                // actionMode = MenuActionModeActivity.this.startActionMode(callback);
                actionMode.invalidate();
                // actionMode.setTitle("Truc " + compteur);
                return true;
            }
        });
    }
}
