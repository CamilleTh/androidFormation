package com.societe.premiereapplicationsansxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		/* tous les paramètres XML qui sont sur le modèle <nom>_<quelquechose>
	       sont transmis via un objet LayoutParam */

        LayoutParams paramMain = new LayoutParams (LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        LayoutParams paramTexte = new LayoutParams 	(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

	    /* Le layout est créé pour l'activité */
        RelativeLayout ecran = new RelativeLayout(this);

	    /* Calcul de la densité de l'écran */
        float densite = getApplicationContext().getResources().getDisplayMetrics().density;
	    /* Calcul en px de la marge avec la valeur de 16dp */
        int margePx = (int)(16 * densite);
	    /* Etablissement de la marge */
        ecran.setPadding(margePx, margePx, margePx, margePx);

	    /* Les paramètres de remplissage sont transmis au Layout */
        ecran.setLayoutParams(paramMain);

	    /* Un objet TextView est créé pour l'activité */
        TextView texte = new TextView(this);
	    /* Les paramètres de remplissage sont transmis à l'objet TextView */
        texte.setLayoutParams(paramTexte);
	    /* La gravité est texte dans l'espace occupé est établie */
        texte.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);

	    /* l'objet TextView est ajouté comme composant graphique
	       rattaché au layout */
        ecran.addView(texte);

	    /* identique au code utilisant la déclaration XML */
        setContentView(ecran);
        texte.setText("Bonjour !");
    }
}
