package lu.intech.calculetteintentservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.TextView;

import lu.intech.calculetteintentservice.R;

public class MyOperationReceiver extends BroadcastReceiver {

    private TextView ope;

    public MyOperationReceiver(TextView ope) {
        this.ope = ope;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("REC","MyOperationReceiver");
        Resources res = context.getResources();
        String nomAction = res.getString(R.string.myActionView);
        if (intent.getAction().equals(nomAction)){
            ope.setText( intent.getStringExtra("ope"));
        }
        //abortBroadcast();
    }
}
