package lu.intech.calculetteintentservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import lu.intech.calculetteintentservice.R;

public class MyResultReceiver extends BroadcastReceiver {


    private TextView result;

    public MyResultReceiver(TextView result) {
        this.result = result;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("REC","MyResultReceiver");
        Resources res = context.getResources();
        String nomAction = res.getString(R.string.myActionView);
        if (intent.getAction().equals(nomAction)){
            Double resu = intent.getDoubleExtra("res", 0);
            result.setText(resu.toString());
        }
        //abortBroadcast();
    }
}
