package com.societe.widgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class Widgets extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // On récupère notre case à cocher pour intercepter l’événement
        // d’état (cochée ou pas)
        ((CheckBox)findViewById(R.id.checkBox1)).setOnCheckedChangeListener(
                new CheckBox.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        afficheToast("Case cochée ? : " + ((isChecked)?"Oui":"Non"));
                    }
                });
        // On récupère notre sélectionneur de date (DatePicker) pour attraper
        // l’événement du changement de date
        // Attention, le numéro de mois commence a 0 dans Android, mais pas les jours.
        // Donc si vous voulez mettre le mois de Mai, vous devrez fournir 4 et non 5
        ((DatePicker)findViewById(R.id.datePicker1)).init(2017, 0, 1, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // On affiche la nouvelle date qui vient d’être changée dans notre
                // DatePicker
                afficheToast("La date a changé\nAnnée : " + year + " | Mois : " + monthOfYear + " | Jour : " + dayOfMonth);
            }
        });
        // On récupère notre barre de vote pour attraper la nouvelle note que
        // sélectionnera l’utilisateur
        ((RatingBar)findViewById(R.id.ratingBar1)).setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        // On affiche la nouvelle note sélectionnée par l’utilisateur
                        afficheToast("Nouvelle note : " + rating);
                    }
                });
        // On récupère notre groupe de bouton radio pour attraper le choix de
        // l’utilisateur
        ((RadioGroup)findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // On affiche le choix de l’utilisateur
                        afficheToast("Vous avez répondu : "
                                + ((RadioButton)findViewById(checkedId)).getText());
                    }
                });
        // On récupère notre Bouton Image pour attraper le clic effectué par
        // l’utilisateur
        ImageButton imb = (ImageButton)findViewById(R.id.imageButton1);

        imb.setOnClickListener(

                new OnClickListener() {
                    public void onClick(View v) {
                        // On affiche un message pour signalé que le bouton image a été pressé
                        afficheToast("Bouton Image pressé !!!");
                    }
                });
    }

    public void afficheToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.widget, menu);
        return true;
    }*/
}
