package com.societe.tacheasynchrone;


import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

public class Tache extends AsyncTask<Void, Integer, Void> {
	
	private MainActivity activite;
	
	public Tache(MainActivity activite) {
		this.activite = activite;
	}

	@Override
    protected void onPreExecute() {
        activite.getBarre().setVisibility(View.VISIBLE);
        activite.getAnnulerBouton().setVisibility(View.VISIBLE);
        activite.getLancerBouton().setEnabled(false);
        Toast.makeText(activite, "Début de la tâche", Toast.LENGTH_LONG).show();
    }
 
    @Override
    protected void onProgressUpdate(Integer... values){
        // Mise à jour de la ProgressBar
        activite.getBarre().setProgress(values[0]);
    }
 
    @Override
    protected Void doInBackground(Void... arg0) {
        int progress = 0;
        
        while (progress++ <=100 && !isCancelled()){
        	//simulation d'une tâche
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            //la méthode publishProgress met à jour l'interface en invoquant la méthode onProgressUpdate
            publishProgress(progress);
        }
        return null;
    }
 
    @Override
    protected void onPostExecute(Void result) {
    	activite.getBarre().setProgress(0);
        activite.getBarre().setVisibility(View.INVISIBLE);
        activite.getAnnulerBouton().setVisibility(View.INVISIBLE);
        activite.getLancerBouton().setEnabled(true);
        Toast.makeText(activite, "Tâche terminée", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        activite.getBarre().setProgress(0);
        activite.getBarre().setVisibility(View.INVISIBLE);
        activite.getAnnulerBouton().setVisibility(View.INVISIBLE);
        activite.getLancerBouton().setEnabled(true);
        Toast.makeText(activite, "Tâche annulée", Toast.LENGTH_LONG).show();
    }
}
