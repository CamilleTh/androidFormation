package com.societe.materialdesign1.adaptateurs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.societe.materialdesign1.R;
import com.societe.materialdesign1.modeles.EntreeNavigation;

import java.util.Collections;
import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private List<EntreeNavigation> entrees = Collections.emptyList();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView texte;

        public ViewHolder(View itemView) {
            super(itemView);
            texte = (TextView)itemView.findViewById(R.id.titre);
        }
    }

    public NavigationAdapter(List<EntreeNavigation> entrees) {
        this.entrees = entrees;
    }

    @Override
    public int getItemCount() {
        return entrees.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entree_navigation, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EntreeNavigation courant = entrees.get(position);
        holder.texte.setText(courant.getTitre());
    }
}
