package com.umedia.wccgame.activities;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.wordcheckchallenge.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainGameActivity extends Activity {
	
	
	ProgressBar webBar;
	ProgressBarThread thd;
	ProgressHandlerBar hanbar;
	String difficulty;
	
	
	
	

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//Immersive mode
//				getWindow().getDecorView().setSystemUiVisibility(
//				          View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//				        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//				        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//				        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//				        | View.SYSTEM_UI_FLAG_FULLSCREEN
//				        | View.SYSTEM_UI_FLAG_IMMERSIVE);
				
				//this will display the splash activity in fullscreen without the title bar
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
				//Code below sets the XML for the content view
				setContentView(R.layout.main_layout);
				
				
				webBar = (ProgressBar) findViewById(R.id.progressBar1);
				hanbar = new ProgressHandlerBar();
				thd = new ProgressBarThread();
				
				
				//Context cont = MainGameActivity.this;
				
				//Toast.makeText(cont, cont.getFilesDir().getPath()+"/database/", Toast.LENGTH_LONG).show();
			
				
				//Using an anonymous inner thread to start a concurrent timer for my splash screen
				Thread logoTimer = new Thread(){
					
					public void run() {
						
						 try{
			                    int logoTimer = 0;
			                    while(logoTimer < 7000){
			                   
			                    	sleep(100);
			                        logoTimer = logoTimer +100;
			                    };
			                    //startActivity(new Intent(MainGameActivity.this, WelcomeChoiceActivity.class));
			                    startActivity(new Intent(MainGameActivity.this, AltMainGameActivity.class));
			                    
			                    
			                    
			                   
			                    
			                }
			                 
			                catch (InterruptedException e) {
			                    // TODO Auto-generated catch block
			                    e.printStackTrace();
			                }
			                 
			                finally{
			                   finish();
			                }
						
					}
						
					};
					
					
					thd.start();
					logoTimer.start();
					
					
				
				
				
				
				
		
	}
	
	
	
	
	

	class ProgressHandlerBar extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			Bundle bn = msg.getData();
			int i = bn.getInt("level");
			webBar.setProgress(i);
			
			super.handleMessage(msg);
			
			
			
		}
		
	}
	

	class ProgressBarThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int counter = 0;counter <= 100;counter++){
				
				
				Message msg = new Message();	//Message to be sent to the handler
				Bundle bund = new Bundle();		//Bundle to contain our message
				bund.putInt("level", counter);
				msg.setData(bund);				//Message object collects the bundle
				hanbar.sendMessage(msg);
				try {
					sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			super.run();
		}
		
	}
	
	
	//Creating an alert dialog to enable user to select difficulty 
	public void showDifficultyDialog()
	{
		final CharSequence[] items = {"EASY","NORMAL","DIFFICULT"};
		
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("Pick a Difficulty");
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		
    		//The OK button listener
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			
				
						
			}
		});
    	
    	
    	//The listener for the radio buttons
    	builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				

				if("EASY".equals(items[which]))
				{
				difficulty = "EASY";
				}
				else if("NORMAL".equals(items[which]))
				{
					difficulty = "NORMAL";
					}
				else if("DIFFICULT".equals(items[which]))
				{
					difficulty = "HARD";
					}
				
			}
		});
		
		builder.show();
		
	}
	
	/*

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater manu = getMenuInflater();
		manu.inflate(R.menu.wcc_menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId())
		{
		case R.id.abouter:
			
			Toast.makeText(getBaseContext(), "ABOUT WCC", 400000).show();
			
			break;
			
		case R.id.helper:
			
			
			Toast.makeText(getBaseContext(), "HELPER", 400000).show();
			
			break;
			
		case R.id.exiter:
			
			Toast.makeText(getBaseContext(), "EXITING", 400000).show();
			
			break;
		
		
		
		default:
			
			Toast.makeText(getBaseContext(), "Nothing to see here", 400000).show();
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}*/
	
	
	
	
}

	
	
	

