package com.scha.calculetteaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


//Projet Stéphanie Martel
// scha@ilex.fr

import com.scha.calculetteaidl.Calculetteaidl.Stub;

public class ServiceCalc extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.v("SCHA-service", "Bind OK");
        return mBinder;
    }

    private final Stub mBinder = new Stub() {

        @Override
        public float add(float f1, float f2) throws RemoteException{
            return f1 + f2;
        }

        @Override
        public float sous(float f1, float f2) throws RemoteException{
            return f1 - f2;
        }

        //il faut faire un RemoteEcxception et non juste Excpetion car l'appel sera distant
        @Override
        public float div(float f1, float f2) throws RemoteException {
            if (f2 == 0)
                throw new IllegalStateException("Division par 0");
            return f1 / f2;
        }

    };
}
