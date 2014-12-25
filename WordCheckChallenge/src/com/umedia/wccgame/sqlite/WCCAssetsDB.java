package com.umedia.wccgame.sqlite;

import java.util.Random;

import android.app.DownloadManager.Query;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class WCCAssetsDB extends SQLiteAssetHelper {
	
	
	private static final String DATABASE_NAME = "xmhghana_wcc_heavy.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private SQLiteDatabase db;

	public WCCAssetsDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	
		db = getReadableDatabase();
		
		// you can use an alternate constructor to specify a database location
		// (such as a folder on the sd card)
		// you must ensure that this folder is available and you have permission
		// to write to it
		//super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
		
	}
	
	
	public Cursor getAllEnglish()
	{
		
		
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		//Projection
		String [] sqlSelect = {"*"};
		//Database table to be selected
		String sqlTables = "en_three";
		
		qb.setTables(sqlTables);
		
		Cursor c = qb.query(db, sqlSelect, null, null,null, null, null);
		
		c.moveToFirst();
		
		
		return c;
	}
	
	
	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		db.close();
		super.close();
	}
	
	
	public boolean threeLetter(String words,String codes)
	{
		
		//Cursor cs = readDB.query("stock", new String[]{"item","price"}, "price=?", new String[]{plice+""}, null, null, null);
//		
//		return cs;
		
		//Cursor cc = db.query("en_words", new String[]{"*"}, "word=?", new String[]{words}, null, null, null);
		
		Cursor cc = db.rawQuery("SELECT * FROM en_three WHERE word = ? AND code = ?",new String[]{words,codes} );
		
		if(cc.getCount() > 0)
		{
			
			return true;
		}
		
		return false;
	}
	
	
	
	public boolean fourLetter(String words,String codes)
	{
		
		//Cursor cs = readDB.query("stock", new String[]{"item","price"}, "price=?", new String[]{plice+""}, null, null, null);
//		
//		return cs;
		
		//Cursor cc = db.query("en_words", new String[]{"*"}, "word=?", new String[]{words}, null, null, null);
		
		Cursor cc = db.rawQuery("SELECT * FROM en_words WHERE word = ? AND code = ?",new String[]{words,codes} );
		
		if(cc.getCount() > 0)
		{
			
			return true;
		}
		
		return false;
	}
	
	
	
	public boolean hardLetters(String words,String codes)
	{
		
		//Cursor cs = readDB.query("stock", new String[]{"item","price"}, "price=?", new String[]{plice+""}, null, null, null);
//		
//		return cs;
		
		//Cursor cc = db.query("en_words", new String[]{"*"}, "word=?", new String[]{words}, null, null, null);
		
		Cursor cc = db.rawQuery("SELECT * FROM en_words WHERE word = ? AND advance_code = ?",new String[]{words,codes} );
		
		if(cc.getCount() > 0)
		{
			
			return true;
		}
		
		return false;
	}
	
	
//	public String randomWord(){
//		
//		String ranString = "NONE";
//		Random rd = new Random();
//		
//		Cursor cc = db.rawQuery("SELECT * FROM en_normal WHERE _id = ? ", new String[]{rd.nextInt(12860)+""});
//		
//		if(cc.moveToFirst())
//		{
//			
//			ranString = cc.getString(0);
//			
//		}
//		
//		
//		return ranString;
//	}
	
	
	
		public String randomThree(){
		
		String ranString = "NONE";
		Random rd = new Random();
		
		Cursor cc = db.rawQuery("SELECT * FROM en_three WHERE _id = ? ", new String[]{rd.nextInt(1015)+""});
		
		if(cc.moveToFirst())
		{
			
			ranString = cc.getString(0);
			
		}
		
		
		return ranString;
	}
	
	
		public String randomHard(){
		
		String ranString = "NONE";
		Random rd = new Random();
		
		//Cursor cc = db.rawQuery("SELECT * FROM en_words WHERE _id = ? ", new String[]{rd.nextInt(198519)+""});
		Cursor cc = db.rawQuery("SELECT * FROM en_words WHERE _id = ? ", new String[]{rd.nextInt(27727)+""});
		
		
		if(cc.moveToFirst())
		{
			
			ranString = cc.getString(3);
			
		}
		
		
		return ranString;
	}
	
	
	

}
