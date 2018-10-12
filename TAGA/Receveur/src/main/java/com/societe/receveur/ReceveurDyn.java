package com.societe.receveur;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.Toast;

public class ReceveurDyn extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Resources res = context.getResources();
        String nomAction = res.getString(R.string.nomActionView);
        if (intent.getAction().equals(nomAction)){
            Toast.makeText(context, "BroadCastReceiver dyn déclenché ! ",
                    Toast.LENGTH_LONG).show();
        }
        // abortBroadcast();
    }
}
