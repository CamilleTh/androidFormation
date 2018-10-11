package lu.intech.appel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class MainActivity extends Activity {


    private static final int CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appeler();
    }

    private void appeler(){
        Uri uri = Uri.parse("tel:555555555");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);

        try{
            if(Build.VERSION.SDK_INT > 22){
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        Toast.makeText(this, "Please say yes !", Toast.LENGTH_LONG  );

                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CODE  );

                        // on lance onRequestPermissionsResult
                    }
                }else {
                    // Permission déjà accordé
                    startActivity(intent);
                }
            }else {
                // Ancienne versio on lance direct
                startActivity(intent);
            }
        }catch (Exception e){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODE :

                    String permission = permissions[0];
                    int grantResult = grantResults[0];

                    if (permission.equals(Manifest.permission.CALL_PHONE)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {

                            Uri uri = Uri.parse("tel:555555555");
                            Intent intent = new Intent(Intent.ACTION_CALL, uri);
                            startActivity(intent);

                            break;
                        } else {

                            // PERMISSION REFUSED
                        }
                    }



        }
    }
}
