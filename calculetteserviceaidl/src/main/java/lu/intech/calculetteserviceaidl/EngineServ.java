package lu.intech.calculetteserviceaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


public class EngineServ extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private lu.intech.calculetteserviceaidl.EngineService.Stub mBinder = new  lu.intech.calculetteserviceaidl.EngineService.Stub() {

        @Override
        public double add(double d1, double d2) throws RemoteException {
            return d1 + d2;
        }

        @Override
        public double sub(double d1, double d2) throws RemoteException {
            return d1 - d2;
        }

        @Override
        public double mul(double d1, double d2) throws RemoteException {
            return d1 * d2;
        }

        @Override
        public double div(double d1, double d2) throws RemoteException {
            if(d2 == 0) throw  new NullPointerException();
            return d1 / d2;
        }
    };

}
