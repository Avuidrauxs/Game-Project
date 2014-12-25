package com.umedia.wccgame.activities;


import com.example.wordcheckchallenge.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomeChoiceActivity extends Activity implements OnClickListener {


	ImageView wordCount,wordCheck,wordChallenge;
	
	Dialog diallo;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		//Immersive mode
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE);

		setContentView(R.layout.welcome_screen);


		wordCount = (ImageView) findViewById(R.id.wordCounts);
		wordCheck = (ImageView) findViewById(R.id.wordCheckers);
		wordChallenge = (ImageView) findViewById(R.id.wordChallenge);
		
		wordCount.setOnClickListener(this);
		wordCheck.setOnClickListener(this);
		wordChallenge.setOnClickListener(this);



	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		switch (v.getId())
		{

		case R.id.wordCounts:

			Toast.makeText(WelcomeChoiceActivity.this, "Welcome to Word Count", Toast.LENGTH_LONG).show();
			startActivity(new Intent(WelcomeChoiceActivity.this, WordCheckActivity.class));
			break;


		case R.id.wordCheckers:
			
			
			diallo = new Dialog(WelcomeChoiceActivity.this);
			diallo.setContentView(R.layout.dialog_comingsoon);
			diallo.setTitle("WCC");
			diallo.setCanceledOnTouchOutside(true);
			diallo.show();
			
			break;
			
			
		case R.id.wordChallenge:
			
			
			diallo = new Dialog(WelcomeChoiceActivity.this);
			diallo.setContentView(R.layout.dialog_comingsoon);
			diallo.setTitle("WCC");
			diallo.setCanceledOnTouchOutside(true);
			diallo.show();
			
			break;
			
		default:

			break;


		}

	}
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
		//Immersive mode
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE);
	}

}
