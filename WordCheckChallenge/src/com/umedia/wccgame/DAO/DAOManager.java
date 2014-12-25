package com.umedia.wccgame.DAO;

import java.util.ArrayList;

import com.umedia.wccgame.sqlite.WCCAssetsDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOManager {
	
	Context context;
	WCCAssetsDB dbUtil;
	SQLiteDatabase readDB;
	EnglishWords engwords;
	
	public DAOManager(Context context)
	{
		this.context = context;
		dbUtil = new WCCAssetsDB(context);
		readDB = dbUtil.getReadableDatabase();
		
		
		
	}
	
	
	public ArrayList<EnglishWords> allEnglish()
	{
		
		ArrayList<EnglishWords> all = new ArrayList<EnglishWords>();
		Cursor cs = dbUtil.getAllEnglish();
		
		while(cs.moveToNext())
		//while(cs.move(10))
		{
			
			engwords = new EnglishWords(cs.getString(2), cs.getString(1), cs.getString(3), cs.getInt(4));
			all.add(engwords);
			
			
		}
		
		
		return all;
	}

}
