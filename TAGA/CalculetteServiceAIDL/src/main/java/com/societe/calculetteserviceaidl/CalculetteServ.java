package com.societe.calculetteserviceaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.societe.calculetteserviceaidl.CalculetteService.Stub;

public class CalculetteServ extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Stub mBinder = new Stub() {
        @Override
        public double additionner(double operande1, double operande2) throws RemoteException {
            Log.v("CalculetteServ", "additionner");
            return operande1 + operande2;
        }

        @Override
        public double soustraire(double operande1, double operande2) throws RemoteException {
            Log.v("CalculetteServ", "soustraire");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return operande1 - operande2;
        }

        @Override
        public double diviser(double operande1, double operande2) throws RemoteException {
           Log.v("CalculetteServ", "diviser");
            if (operande2 == 0){
                Log.v("CalculetteServ", "operande2 zéro");
                throw new IllegalStateException("Division par zéro");
            }
            return operande1 / operande2;
        }

        @Override
        public double multiplier(double operande1, double operande2) throws RemoteException {
            Log.v("CalculetteServ", "multiplier");
            return operande1 * operande2;
        }
    };
}
