package com.societe.persistancebd.bean;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StagiaireDAO {
	private static final String BASE = "stagiaires.db";
	private static final int VERSION = 1;
	private static final String TABLE_STAGIAIRES = "stagiaires";
	public static final String COLONNE_ID = "id";
	private static final int COLONNE_ID_INDEX = 0;
	public static final String COLONNE_NOM = "nom";
	private static final int COLONNE_NOM_INDEX = 1;
	public static final String COLONNE_PRENOM = "prenom";
	private static final int COLONNE_PRENOM_INDEX = 2;
	private SQLiteDatabase stagiaires;
	private StagiaireOpenHelper stagiaireHelper;

	private static final String REQUETE_CREATION_BD = "create table "
		+ TABLE_STAGIAIRES + " (" + COLONNE_ID
		+ " integer primary key autoincrement, " + COLONNE_NOM
		+ " text not null, " + COLONNE_PRENOM + " text not null);";

	public StagiaireDAO(Context ctx){
		stagiaireHelper = new StagiaireOpenHelper(ctx, BASE, null, VERSION);
	}

	public SQLiteDatabase open(){
		stagiaires = stagiaireHelper.getWritableDatabase();
		// stagiaires.execSQL("drop table if exists " + TABLE_STAGIAIRES + ";");
		return stagiaires;
	}

	public void close(){
		stagiaires.close();
	}

	public Stagiaire getStagiaire(String nom){
		Cursor c = stagiaires.query(TABLE_STAGIAIRES, 
				new String[]{COLONNE_ID,COLONNE_NOM,COLONNE_PRENOM}, null, null, null, 
				COLONNE_ID + " LIKE " + nom, null);
		return cursorToStagiaire(c);
	}

	// Pour le fournisseur de contenu
	public Cursor getStagiaireCurseur(int id){
		Cursor c = stagiaires.query(TABLE_STAGIAIRES, 
				new String[]{COLONNE_ID,COLONNE_NOM,COLONNE_PRENOM}, null, null, null, 
				COLONNE_ID + " = " + id, null);
		return c;
	}
	
	public Stagiaire getStagiaire(int id){
		Cursor c = stagiaires.query(TABLE_STAGIAIRES, 
				new String[]{COLONNE_ID,COLONNE_NOM,COLONNE_PRENOM},COLONNE_ID + " = " + id, null, null, 
				null, null);
		return cursorToStagiaire(c);
	}

	// pour le fournisseur de contenu
	public Cursor getAllStagiairesCurseur(){
		Cursor c = stagiaires.query(TABLE_STAGIAIRES, new String[] {
				COLONNE_ID, COLONNE_NOM, COLONNE_PRENOM }, null, null, null,
				null, null);
		return c;
	}
	
	public ArrayList<Stagiaire> getAllStagiaires() {
		Cursor c = stagiaires.query(TABLE_STAGIAIRES, new String[] {
				COLONNE_ID, COLONNE_NOM, COLONNE_PRENOM }, null, null, null,
				null, null);
		return cursorToStagiaires(c);
	}

	private Stagiaire cursorToStagiaire(Cursor c) {
		// Si la requ�te ne renvoie pas de r�sultat
		if (c.getCount() == 0)
			return null;

		c.moveToFirst();
		Stagiaire stagiaire = new Stagiaire(c.getInt(COLONNE_ID_INDEX),
				c.getString(COLONNE_NOM_INDEX),
				c.getString(COLONNE_PRENOM_INDEX));
		c.close();
		return stagiaire;
	}

	private ArrayList<Stagiaire> cursorToStagiaires(Cursor c) {
		// Si la requ�te ne renvoie pas de r�sultat
		if (c.getCount() == 0)
			return new ArrayList<Stagiaire>();

		ArrayList<Stagiaire> stagaiaires = new ArrayList<Stagiaire>(c.getCount());
		c.moveToFirst();
		do {
			Stagiaire stagiaire = new Stagiaire(c.getInt(COLONNE_ID_INDEX),
					c.getString(COLONNE_NOM_INDEX),
					c.getString(COLONNE_PRENOM_INDEX));
			stagaiaires.add(stagiaire);
		} while (c.moveToNext());
		// Ferme le curseur pour lib�rer les ressources
		c.close();
		return stagaiaires;
	}

    public SQLiteDatabase getBase(){
    	return stagiaires;
    }
    
    public static final String getTable(){
    	return TABLE_STAGIAIRES;
    }
    
	public long insertStagiaire(Stagiaire stagiaire) {
		ContentValues valeurs = new ContentValues();
		valeurs.put(COLONNE_NOM, stagiaire.getNom());
		valeurs.put(COLONNE_PRENOM, stagiaire.getPrenom());
		return stagiaires.insert(TABLE_STAGIAIRES, null, valeurs);
	}
	
	public long rawInsertStagiaire(ContentValues valeurs) {
		return stagiaires.insert(TABLE_STAGIAIRES, null, valeurs);
	}

	public int updateStagiaire(int id, Stagiaire stagiaire) {
		ContentValues valeurs = new ContentValues();
		valeurs.put(COLONNE_NOM, stagiaire.getNom());
		valeurs.put(COLONNE_PRENOM, stagiaire.getPrenom());
		return stagiaires.update(TABLE_STAGIAIRES, valeurs, COLONNE_ID + " = "
				+ id, null);
	}
	
	public int deleteStagiaire(long id) {
		return stagiaires.delete(TABLE_STAGIAIRES,COLONNE_ID+" = ?",new String[]{Long.toString(id)});
	}
	
	public int deleteAllStagiaires() {
		return stagiaires.delete(TABLE_STAGIAIRES,null,null);
	}
	
	private class StagiaireOpenHelper extends SQLiteOpenHelper {

		public StagiaireOpenHelper(Context context, String nom,
				CursorFactory cursorfactory, int version) {
			super(context, nom, cursorfactory, version);
		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			Log.v("onOpen","OK");
			// db.execSQL("drop table if exists " + TABLE_STAGIAIRES + ";");
			// db.execSQL(REQUETE_CREATION_BD);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(REQUETE_CREATION_BD);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Dans notre cas, nous supprimons la base et les donn�es pour en
			// cr�er une nouvelle ensuite. Vous pouvez cr�er une logique de mise
			// � jour propre � votre base permettant de garder les donn�es � la
			// place.
			db.execSQL("drop table " + TABLE_STAGIAIRES + ";");
			// Cr�ation de la nouvelle structure.
			onCreate(db);
		}
	}
}
