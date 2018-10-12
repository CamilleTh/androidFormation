package com.societe.persistancebd.fournisseur;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.societe.persistancebd.bean.StagiaireDAO;

public class FournisseurNoms extends ContentProvider
{

	private static final String authorite = "societe.com.android.provider.noms";
	private static final String uriNom = "content://" + authorite + "/noms";
	public static final Uri CONTENT_URI = Uri.parse(uriNom);

	private StagiaireDAO stagiaireDAO;

	// les constantes pour les formes d'URI pour UriMatcher
	private static final int TOUS_LES_NOMS = 0;
	private static final int UN_NOM = 1;
	/*private static final int LIVE_FOLDER = 2;

	// Pour le live folder
	private static final HashMap<String,String> LIVE_FOLDER_PROJECTION_MAP;

	static
	{
		LIVE_FOLDER_PROJECTION_MAP = new HashMap<String,String>();
		LIVE_FOLDER_PROJECTION_MAP.put(LiveFolders._ID,StagiaireDAO.COLONNE_NOM
		    + " AS " + LiveFolders._ID);
		LIVE_FOLDER_PROJECTION_MAP.put(LiveFolders.NAME,StagiaireDAO.COLONNE_NOM
		    + " AS " + LiveFolders.NAME);
	}*/

	// la classe de gestion des URI
	private static UriMatcher uriMatcher;
	static
	{
		// configure l'uriMatcher pour que les uri se terminant par
		// noms correspondent � une requ�te pour tous les noms
		// et celles se terminant par un noms/id correspondent
		// � un seul nom (par son id)
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(authorite,"noms",TOUS_LES_NOMS);
		uriMatcher.addURI(authorite,"noms/#",UN_NOM);
		//uriMatcher.addURI(authorite,"livefolder",LIVE_FOLDER);
	}

	@Override
	public int delete(Uri uri,String selection,String[] selectionArgs)
	{
		Log.v("MESSAGE", uri.toString());
		switch(uriMatcher.match(uri))
		{
		case UN_NOM:
			long id = Long.parseLong(uri.getLastPathSegment());
			Log.v("MESSAGE", "destruction unitaire");
			return stagiaireDAO.deleteStagiaire(id);
		case TOUS_LES_NOMS:
			Log.v("MESSAGE", "Armagedon");
			return stagiaireDAO.deleteAllStagiaires();
		default:
			return 0;
		}
	}

	@Override
	public String getType(Uri uri)
	{
		switch(uriMatcher.match(uri))
		{
		case UN_NOM:
			return "vnd.android.cursor.item/noms";
		case TOUS_LES_NOMS:
		//case LIVE_FOLDER:
			return "vnd.android.cursor.collection/noms";
		default:
			throw new IllegalArgumentException("URI non support�e" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri,ContentValues values)
	{
		if(uriMatcher.match(uri) == UN_NOM)
		{
			long id = stagiaireDAO.rawInsertStagiaire(values);
			return Uri
			    .parse(uri.buildUpon().appendPath(Long.toString(id)).toString());
		}
		else
			return null;
	}

	@Override
	public boolean onCreate()
	{
		stagiaireDAO = new StagiaireDAO(getContext());
		stagiaireDAO.open();
		return true;
	}

	@Override
	public Cursor query(Uri uri,String[] projection,String selection,
	    String[] selectionArgs,String sortOrder)
	{
		Cursor curseur = null;
		switch(uriMatcher.match(uri))
		{
		case UN_NOM:
			int id = Integer.parseInt(uri.getPathSegments().get(1));
			curseur = stagiaireDAO.getStagiaireCurseur(id);
		break;
		case TOUS_LES_NOMS:
			curseur = stagiaireDAO.getAllStagiairesCurseur();
		break;
//		case LIVE_FOLDER:
//			SQLiteQueryBuilder sqlQb = new SQLiteQueryBuilder();
//			sqlQb.setTables(StagiaireDAO.getTable());
//			sqlQb.setProjectionMap(LIVE_FOLDER_PROJECTION_MAP);
//			curseur = sqlQb.query(stagiaireDAO.getBase(),projection,selection,
//			    selectionArgs,null,null,null);
//		break;
		}
		return curseur;
	}

	@Override
	public int update(Uri uri,ContentValues values,String selection,
	    String[] selectionArgs)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
