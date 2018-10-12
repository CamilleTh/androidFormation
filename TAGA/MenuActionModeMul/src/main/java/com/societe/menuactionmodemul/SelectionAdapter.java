package com.societe.menuactionmodemul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

class ViewTenant{
    CheckBox stagiaireBox;
}

class SelectionAdapter extends ArrayAdapter<Stagiaire> {

    public SelectionAdapter(Context context, int textViewResourceId, ArrayList<Stagiaire> stagiaires) {
        super(context, textViewResourceId, stagiaires);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewTenant tenant = null;
        final ListView l = (ListView)parent;

        if (convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.stagiaire_info, null);
            tenant = new ViewTenant();
            tenant.stagiaireBox = (CheckBox)convertView.findViewById((R.id.checkBox));
            convertView.setTag(tenant);

            tenant.stagiaireBox.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Stagiaire stagiaire = (Stagiaire) cb.getTag();
                    stagiaire.setSelectionne(cb.isChecked());
                    l.setItemChecked(position,cb.isChecked());
                    Toast.makeText(getContext(), "Clic sur Checkbox: " + stagiaire.getNom() + " est " + stagiaire.isSelectionne(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            tenant = (ViewTenant) convertView.getTag();
        }
        Stagiaire stagiaire = getItem(position);
        tenant.stagiaireBox.setText(stagiaire.getNom());
        tenant.stagiaireBox.setChecked(stagiaire.isSelectionne());
        tenant.stagiaireBox.setTag(stagiaire);
        return convertView;
    }
}
