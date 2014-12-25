package com.umedia.wccgame.activities;



import java.util.ArrayList;

import com.umedia.wccgame.DAO.DAOManager;
import com.umedia.wccgame.DAO.EnglishWords;
import com.umedia.wccgame.sqlite.WCCAssetsDB;

import android.R;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;


public class AllEnglishWords extends ListActivity {
	
	
	private WCCAssetsDB dbb;
	private Cursor words;
	private DAOManager manager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
//		dbb = new WCCAssetsDB(AllEnglishWords.this);
//		words = dbb.getAllEnglish();
//		
//		ListAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,
//				words,new String[] {"word"},new int[] {android.R.id.text1});
//		
//	getListView().setAdapter(adapter);
		
		manager = new DAOManager(AllEnglishWords.this);
		
		ArrayList<EnglishWords> eng = manager.allEnglish();
		
		getListView().setAdapter(new ArrayAdapter<>(AllEnglishWords.this, R.layout.simple_list_item_1, eng));
		
		
	}
	
	

}
