package lu.intech.calculetteintentservice.services;

import android.app.IntentService;
import android.content.Intent;

import lu.intech.calculetteintentservice.MainActivity;
import lu.intech.calculetteintentservice.Operation;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class EngineService extends IntentService {

    public EngineService() {
        super("EngineService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try{
            Thread.sleep(5000);
        }catch (Exception e ){

        }

        if (intent != null) {
            Operation operation = intent.getParcelableExtra("operation");

            Intent intent2 = new Intent(this, MainActivity.class);

            Double result = 0.0;
            switch (operation.getOperator()){
                case '+':
                    result = operation.getOpe1() + operation.getOpe2();
                    intent2.putExtra("ope","+");
                    break;
                case '-':
                    result = operation.getOpe1() - operation.getOpe2();
                    intent2.putExtra("ope","-");
                    break;
                case '/':
                    if(operation.getOpe2() == 0) {
                        intent2.putExtra("ope", "ERROR DIV BY ZERO");
                    }else {
                        result = operation.getOpe1() / operation.getOpe2();
                        intent2.putExtra("ope","/");
                    }

                    break;
                case  'x':
                    result = operation.getOpe1() * operation.getOpe2();
                    intent2.putExtra("ope","*");
                    break;
            }

            intent2.putExtra("res",result);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent2);
        }
    }
}
