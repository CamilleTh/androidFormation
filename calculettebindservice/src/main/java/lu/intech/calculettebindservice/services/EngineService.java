package lu.intech.calculettebindservice.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EngineService extends Service {

    private final IBinder binder = new BindService();

    public class BindService extends Binder {
        public EngineService getService(){
            return EngineService.this;
        }
    }

    public EngineService() { }

    @Override
    public IBinder onBind(Intent intent) {
      return binder;
    }

    // API

    public Result add(double d1, double d2){
        try{
            Thread.sleep(10000);
        }catch (Exception e){

        }
        return new Result('+',d1+d2);
    }

    public Result sub(double d1, double d2){
        return new Result('-',d1-d2);
    }

    public Result mul(double d1, double d2){
        return new Result('x',d1*d2);
    }

    public Result div(double d1, double d2)  {
        if(d2 == 0) throw new ArithmeticException();
        return new Result('/',d1/d2);
    }

}
