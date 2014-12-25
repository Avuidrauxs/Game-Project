package com.umedia.wccgame.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.wordcheckchallenge.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AltWelcomeActivity extends Activity  {
	
	
	ImageView wordCheck;
	Spinner difficulty;
	String difficulty_selected;
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
	        if(days > 15) { // More than 10 days?
	             // Expired !!!
	       
	        	//wordCheck.setVisibility(View.INVISIBLE);
	        	
	        	Dialog dial = new Dialog(AltWelcomeActivity.this);
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
//				         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//				        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//				        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//				        | View.SYSTEM_UI_FLAG_FULLSCREEN
//				        | View.SYSTEM_UI_FLAG_IMMERSIVE);
//				
				//this will display the splash activity in fullscreen without the title bar
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
		
				setContentView(R.layout.alt_welcome);
				
				
				wordCheck = (ImageView) findViewById(R.id.play_now);
		
		difficulty = (Spinner) findViewById(R.id.difficulty);
		
		List<String> levels = new ArrayList<String>();
		
		levels.add("EASY");
		levels.add("NORMAL");
		levels.add("DIFFICULT");
		//levels.add("INSANE");
		
		//difficulty.setAdapter(new ArrayAdapter<>(AltWelcomeActivity.this, android.R.layout.simple_list_item_1, levels));
		difficulty.setAdapter(new ArrayAdapter<>(AltWelcomeActivity.this, R.layout.custom_spinner, levels));
		
		
		difficulty_selected = (String) difficulty.getSelectedItem();
		
		
		wordCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Checks which difficulty has been selected
				
				difficulty_selected = (String) difficulty.getSelectedItem();
				
				
				switch(difficulty_selected){
				
				case "EASY":
					
				startActivity(new Intent(AltWelcomeActivity.this, WordCheckActivity.class));
				//Toast.makeText(AltWelcomeActivity.this,difficulty_selected , 40000).show();
				break;
				
				case "NORMAL":
				
					startActivity(new Intent(AltWelcomeActivity.this, NormalWordCheckActivity.class));
					//Toast.makeText(AltWelcomeActivity.this,difficulty_selected , 40000).show();
					
					break;
					
				case "DIFFICULT":
					
					//Toast.makeText(AltWelcomeActivity.this,difficulty_selected , 40000).show();
					startActivity(new Intent(AltWelcomeActivity.this, HardWordCheckActivity.class));
					break;
				
//				case "INSANE":
//					
//					
//					//Toast.makeText(AltWelcomeActivity.this,difficulty_selected , 40000).show();
//					
//					break;
					
					
					default:
						
						Toast.makeText(AltWelcomeActivity.this,"Not working" , 40000).show();
						
						break;
				}
				
			}
		});
		
	}

	
	
	@SuppressLint("NewApi")
	@Override
	protected void onStart() {

		
		//Immersive mode
//				getWindow().getDecorView().setSystemUiVisibility(
//				          View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//				        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//				        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//				        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//				        | View.SYSTEM_UI_FLAG_FULLSCREEN
//				        | View.SYSTEM_UI_FLAG_IMMERSIVE);
				
				//this will display the splash activity in fullscreen without the title bar
				//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				
		super.onStart();
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
			
			Dialog about = new Dialog(AltWelcomeActivity.this);
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
			
			Dialog help = new Dialog(AltWelcomeActivity.this);
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
		
		Toast.makeText(AltWelcomeActivity.this, "GOODBYE, COME BACK AGAIN!", 4000).show();
		
		super.onDestroy();
	}
	
	
	
	
	

}
