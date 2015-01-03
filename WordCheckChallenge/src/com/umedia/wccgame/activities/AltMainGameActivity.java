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
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AltMainGameActivity extends Activity implements OnClickListener  {
	
	
	ProgressBar webBar;
	
	ProgressHandlerBar hanbar;
	String difficulty;
	ImageView playNow,howTo,aboutBtn,chooseBtn;
	TextView about_text,help_text;
	
	

	//Checking for trials using Shared preference
		private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		private final long ONE_DAY = 24 * 60 * 60 * 1000;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//Checking Shared preference data to resolve cheating
		
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
	    String installDate = preferences.getString("InstallDate", null);
	    if(installDate == null) {
	        // First run, so save the current date
	        SharedPreferences.Editor editor = preferences.edit();
	        Date now = new Date();
	        String dateString = formatter.format(now);
	        editor.putString("InstallDate", dateString);
	        // Commit the edits!
	        editor.commit();
	    }
	    else {
	        // This is not the 1st run, check install date
	        Date before = null;
			try {
				
				before = (Date)formatter.parse(installDate);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Date now = new Date();
	        
	        long diff = now.getTime() - before.getTime();
	        long days = diff / ONE_DAY;
	        if(days > 5) { // More than 10 days?
	             // Expired !!!
	       
	        	//wordCheck.setVisibility(View.INVISIBLE);
	        	
	        	Dialog dial = new Dialog(AltMainGameActivity.this);
	        	dial.setContentView(R.layout.dialog_comingsoon);
	        	dial.setTitle("UNPAID");
	        	dial.setCancelable(false);
	        	dial.show();
	        	
	        }
	        
	        else {
	        	
	        	Toast.makeText(getBaseContext(), "Welcome!!", Toast.LENGTH_LONG).show();
	        }
	    }
	    
	    //End of code for Shared preferences
		
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
				setContentView(R.layout.main_layout2);
				
				
				webBar = (ProgressBar) findViewById(R.id.progressBar1);
				hanbar = new ProgressHandlerBar();
				difficulty = "none";
				playNow = (ImageView) findViewById(R.id.main_play_btn);
				howTo = (ImageView) findViewById(R.id.how_to_btn);
				aboutBtn = (ImageView) findViewById(R.id.about_btn);
				chooseBtn = (ImageView) findViewById(R.id.choose_btn);
				
				
				
				//These are set invisible initially
//				playNow.setVisibility(View.INVISIBLE);
//				howTo.setVisibility(View.INVISIBLE);
//				aboutBtn.setVisibility(View.INVISIBLE);
//				chooseBtn.setVisibility(View.INVISIBLE);
				webBar.setVisibility(View.INVISIBLE);
				playNow.setOnClickListener(this);
				howTo.setOnClickListener(this);
				aboutBtn.setOnClickListener(this);
				chooseBtn.setOnClickListener(this);
				
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
			                    //startActivity(new Intent(MainGameActivity.this, AltWelcomeActivity.class));
			                    
			                    //showDifficultyDialog();
			                    
			                  // showAll();
			    				
			                    
			                }
			                 
			                catch (InterruptedException e) {
			                    // TODO Auto-generated catch block
			                    e.printStackTrace();
			                }
			                 
			                finally{
			                   // finish();
			                }
						
					}
						
					};
					
					
					
					//logoTimer.start();
					
					
				
				
				
				
				
		
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
	
	public void showAll()
	{
		
		
		 //set visible after progress bar is full
        playNow.setVisibility(View.VISIBLE);
		howTo.setVisibility(View.VISIBLE);
		aboutBtn.setVisibility(View.VISIBLE);
		chooseBtn.setVisibility(View.VISIBLE);
		
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
		
    		//The OK ImageView listener
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			
				
						
			}
		});
    	
    	
    	//The listener for the radio buttonss
    	builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				

				if("EASY".equals(items[which]))
				{
				difficulty = "EASY";
				Toast.makeText(AltMainGameActivity.this, "Difficulty seleted: "+difficulty, Toast.LENGTH_LONG).show();
				}
				else if("NORMAL".equals(items[which]))
				{
					difficulty = "NORMAL";
					Toast.makeText(AltMainGameActivity.this, "Difficulty seleted: "+difficulty, Toast.LENGTH_LONG).show();
					}
				else if("DIFFICULT".equals(items[which]))
				{
					difficulty = "HARD";
					Toast.makeText(AltMainGameActivity.this, "Difficulty seleted: "+difficulty, Toast.LENGTH_LONG).show();
					}
				
			}
		});
		
		builder.show();
		
	}


	//OnclicListener implemented by Main class
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		switch(v.getId())
		{
		
		case R.id.main_play_btn:
			
			
			//inner switch case to select difficulty chosen
			switch (difficulty) {
			case "EASY":
				
				
				startActivity(new Intent(AltMainGameActivity.this, WordCheckActivity.class));
				
				break;
				
				
			case "NORMAL":
				
				startActivity(new Intent(AltMainGameActivity.this, NormalWordCheckActivity.class));
				
				break;
				
				
			case "HARD":
				
				startActivity(new Intent(AltMainGameActivity.this, HardWordCheckActivity.class));
				
				break;

			default:
				
				Toast.makeText(AltMainGameActivity.this, "Please Choose a difficulty", Toast.LENGTH_LONG).show();
				
				break;
			}//Inner switch case to check difficulty selected ends here
			
			break;
			
			
		case R.id.about_btn:
			
			
			Toast.makeText(AltMainGameActivity.this, "About App", Toast.LENGTH_LONG).show();
			
			break;
			
			
		case R.id.how_to_btn:
			
			Toast.makeText(AltMainGameActivity.this, "How To", Toast.LENGTH_LONG).show();
			
			break;
			
			
		case R.id.choose_btn:
			
			showDifficultyDialog();
			
			break;
		
		
		default:
			break;
		}
		
		
		
		
		
	}
	

	private Toast toast;
	private long lastBackPressTime = 0;

	
	/*
	 * 	I'd prefer to exit with double tap on the back button than with an exit Dialog.
		In this solution, it show a toast when go back for the first time, 
		warning that another back press will close the App. In this example less than 4 seconds.
	*/
	@Override
	public void onBackPressed() {
	  if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
	    toast = Toast.makeText(this, "Press back again to close this app", 4000);
	    toast.show();
	    this.lastBackPressTime = System.currentTimeMillis();
	  } else {
	    if (toast != null) {
	    toast.cancel();
	  }
	  super.onBackPressed();
	 }
	}
	
	

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
			
			Dialog about = new Dialog(AltMainGameActivity.this);
			about.setContentView(R.layout.about_wcc);
			about.setTitle("ABOUT US");
			about.setCanceledOnTouchOutside(true);
			
			about_text = (TextView) about.findViewById(R.id.abouts);
			about_text.setMovementMethod(LinkMovementMethod.getInstance());
			about_text.setText(Html.fromHtml(" "
					+ " The Word Check Challenge Version 1.0 (English) Copyright © Hesse Herman Heinrich. All rights reserved.	"
					+ " <br>No part of this software structure or games may be reproduced in any language "
					+ "<br>or form: photocopying or electronic retrieval system or transmitted in any form or by any means "
					+ "<br>without the price permission of the copyright owner."
					+ "<br>The Word Check Challenge operating system and its user interface are protected"
					+ " <br>by trademark and other pending or existing intellectual property rights in Ghana and "
					+ "<br>other countries. The Word Check Challenge educational software was developed for Herman Heinrich Hesse"
					+ " <br>for Anthe Android platform by SPAGX IT Solutions - GH"
					+ ""
					+ " "));
			
			about.show();
			
			break;
			
		case R.id.helper:
			
			
			Toast.makeText(getBaseContext(), "HELPER", 400000).show();
			
			Dialog help = new Dialog(AltMainGameActivity.this);
			help.setContentView(R.layout.help_wcc);
			help.setTitle("HELP");
			help.setCanceledOnTouchOutside(true);
			
			help_text = (TextView) help.findViewById(R.id.helps);
			help_text.setText(Html.fromHtml("  "
					+ "<h3>The WORD CHECK CHALLENGE SOFTWARE</h3><center>"
					+ "The WORD CHECK CHALLENGE SOFTWARE is an exciting, puzzling word challenge that will require pupils between "
					+ "<br> the ages of 8 to be quizzed through the educative mental spelling games. "
					+ "<br> The WORD CHECK CHALLENGE SOFTWARE was created by Herman Heinrich Hesse and has been specifically "
					+ "<br> designed to test the speed, attention flexibility and problem solving tendency of every student.  "
					+ "<br> The ‘WORD CHECK CHALLENGE SOFTWARE’ breaks away from the traditional way of spelling and prompts students to "
					+ "<br> read more educational materials by adding new vocabulary to their old ones every day."
					+ "<br> The ‘WORD CHECK CHALLENGE SOFTWARE’ has been designed in the form of an educational game to make its usage fun and exciting. The Word Check challenge software comprises of a system "
					+ "<br> based game. "
					+ " <br><br> <h4>WORD COUNT GAME</h4> WORD COUNT GAME will require player to provide codes for words that will be generated by the computer within "
					+ "<br> thirty seconds (30secs) per question.Egs: Quiet = Q5, Apricot = A7, Balance = B7, Rabbit = R6"
					+ " </center>"));
			
			
			help.show();
			
			break;
			
			
		case R.id.exiter:
			
			
			Toast.makeText(getBaseContext(), "EXITING", 400000).show();
			finish();
			
			break;
		
		default:
			
			Toast.makeText(getBaseContext(), "Nothing to see here", 400000).show();
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	@Override
	protected void onDestroy() {
		
		Toast.makeText(AltMainGameActivity.this, "GOODBYE, COME BACK AGAIN!", 4000).show();
		
		super.onDestroy();
	}
	
	
	
}

	
	
	

