package com.societe.menuactionmodemul;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class StagiaireTenant{
    TextView nom;
}

class StagiaireAdapter extends ArrayAdapter<Stagiaire> {

    public StagiaireAdapter(Context context, int resource, ArrayList<Stagiaire> lesStagiaires) {
        super(context, resource, lesStagiaires);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StagiaireTenant st = null;

        if (convertView == null){
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.stagiaire_item, null);
            st = new StagiaireTenant();
            st.nom = (TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(st);
        } else {
            st = (StagiaireTenant)convertView.getTag();
        }
        st.nom.setText(getItem(position).getNom());
        return convertView;
    }

    public String getNomStagiaire (View view){
        String result = null;

        if (view != null){
            StagiaireTenant st = (StagiaireTenant)view.getTag();
            result = st.nom.getText().toString();
        }
        return result;
    }
}
