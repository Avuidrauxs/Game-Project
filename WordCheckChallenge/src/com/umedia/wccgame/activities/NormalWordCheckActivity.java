package com.umedia.wccgame.activities;

import java.util.ArrayList;
import java.util.Random;

import com.example.wordcheckchallenge.R;
import com.umedia.wccgame.sqlite.WCCAssetsDB;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NormalWordCheckActivity extends Activity {

	CountDownTimer cunt;
	TextView timer, cluetext, scoreLabel, winner_text, loser_text, about_text,
			help_text;
	EditText answerText;
	Button winner_play, winner_exit;
	Button loser_play, loser_exit;
	WCCAssetsDB dbb;
	ImageView submitButton, ad_slider;
	Dialog diallo, loser_dialog;
	Random image_rand;
	private int scores, no_ques;
	private String ans, clue_code;
	private int[] slider;
	private ArrayList<String> entered_word;
	Animation animate_result;
	int choice, level;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// //Immersive mode
		// getWindow().getDecorView().setSystemUiVisibility(
		// View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		// | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		// | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		// | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		// | View.SYSTEM_UI_FLAG_FULLSCREEN
		// | View.SYSTEM_UI_FLAG_IMMERSIVE);

		// this will display the splash activity in fullscreen without the title
		// bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.sampli);

		// Randomize between characters TOTALLY WORKS!!!!
		Random rd = new Random();
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
		char letter = abc.charAt(rd.nextInt(25));
		choice = 4;

		// Toast.makeText(WordCheckActivity.this, letter+" Bayagombe bayagobe",
		// Toast.LENGTH_LONG).show();

		dbb = new WCCAssetsDB(NormalWordCheckActivity.this);
		timer = (TextView) findViewById(R.id.timer);
		cluetext = (TextView) findViewById(R.id.word_clue);
		answerText = (EditText) findViewById(R.id.ansText);

		scoreLabel = (TextView) findViewById(R.id.points_txt);
		submitButton = (ImageView) findViewById(R.id.submit_button);
		ad_slider = (ImageView) findViewById(R.id.ad_slider);

		slider = new int[] { R.drawable.advert_wcc, R.drawable.cream_small,
				R.drawable.indomie_small, R.drawable.zepto_small,
				R.drawable.registration_tag };

		entered_word = new ArrayList<String>();

		scores = 0;
		no_ques = 1;
		level = 1;

		Toast.makeText(NormalWordCheckActivity.this, "LEVEL: " + level,
				Toast.LENGTH_LONG).show();

		String let = letter + "";
		cluetext.setText(let + choice);

		answerText.setText("");

		scoreLabel.setText(scores + "");

		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Randomize ads
				image_rand = new Random();
				// ad_slider.setImageResource(slider[image_rand.nextInt(4)]);

				scores = Integer.parseInt(scoreLabel.getText().toString());
				if (no_ques < 10 && level < 15) {

					// inner IF
					ans = answerText.getText().toString().trim();
					clue_code = cluetext.getText().toString().trim();
					if (dbb.fourLetter(ans, clue_code)
							&& !entered_word.contains(ans)) {

						Random rd = new Random();
						String abc = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
						char letter = abc.charAt(rd.nextInt(25));
						entered_word.add(ans); // Adds words to prevent
												// duplicate
						String let = letter + "";
						cluetext.setText(let + choice);
						answerText.setText("");
						scores += 1;
						no_ques += 1;
						Toast.makeText(NormalWordCheckActivity.this,
								no_ques + "/10", 40000).show();
						scoreLabel.setText(scores + "");
						cunt.start();
					}// inner IF ends here

					// inner ELSE
					else {

						answerText.setText("");
						Toast.makeText(NormalWordCheckActivity.this, "INVALID",
								40000).show();

					}// inner ELSE ends here

				}// main IF statement ends here

				else {

					no_ques = 1;
					if (scores <= 4) {

						scores = 0;
						scoreLabel.setText(scores + "");
						// if(level >= 9)
						// {
						level = 1;
						choice = 4;

						// }
						// loser_dialog.show();
						cunt.cancel();
						// submitButton.setVisibility(View.INVISIBLE);

						// Intent in = getIntent();
						// finish();
						// startActivity(in);

						loser_dialog = new Dialog(NormalWordCheckActivity.this);
						loser_dialog.setContentView(R.layout.loser_text);
						loser_dialog.setTitle("GAME OVER");

						// Animation block starts here
						loser_text = (TextView) loser_dialog
								.findViewById(R.id.loser_text);
						animate_result = AnimationUtils.loadAnimation(
								getBaseContext(), R.anim.myanimation);
						animate_result.setRepeatCount(Animation.INFINITE);

						loser_text.startAnimation(animate_result);
						// Animation block ends here

						// loser_dialog.setCanceledOnTouchOutside(true);

						// Dialog buttons
						loser_play = (Button) loser_dialog
								.findViewById(R.id.lose_again);

						loser_play.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								loser_dialog.dismiss();

								cunt.start();
								// Intent in = getIntent();
								// finish();
								// startActivity(in);

							}
						});

						loser_exit = (Button) loser_dialog
								.findViewById(R.id.lose_exit);

						loser_exit.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

								loser_dialog.dismiss();
								finish();

							}
						});
						//

					} else {
						// diallo.show();
						// cunt.cancel();
						// submitButton.setVisibility(View.INVISIBLE);

						scores = 0;
						scoreLabel.setText(scores + "");
						level += 1;
						choice += 1;
						cunt.cancel();

						// LEVEL ender IF statemnet ends here
						if (level == 15) {

							diallo = new Dialog(NormalWordCheckActivity.this);
							diallo.setContentView(R.layout.winner_text);
							diallo.setTitle("LEVEL UP!!");

							// Animation block starts here
							winner_text = (TextView) diallo
									.findViewById(R.id.winner_text);
							animate_result = AnimationUtils.loadAnimation(
									getBaseContext(), R.anim.myanimation);
							animate_result.setRepeatCount(Animation.INFINITE);
							winner_text.startAnimation(animate_result);
							// Animation block ends here

							// Dialog buttons
							winner_play = (Button) diallo
									.findViewById(R.id.win_again);
							// Winner play again
							winner_play
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {

											cunt.cancel();
											Intent in = getIntent();
											startActivity(in);

										}
									});

							winner_exit = (Button) diallo
									.findViewById(R.id.win_exit);

							winner_exit
									.setOnClickListener(new OnClickListener() {

										@Override
										public void onClick(View v) {
											diallo.dismiss();
											finish();

										}
									});

							diallo.show();

						}// Level ender IF statement ends here

						diallo = new Dialog(NormalWordCheckActivity.this);
						diallo.setContentView(R.layout.winner_text);
						diallo.setTitle("LEVEL UP!!");

						// Animation block starts here
						winner_text = (TextView) diallo
								.findViewById(R.id.winner_text);
						animate_result = AnimationUtils.loadAnimation(
								getBaseContext(), R.anim.myanimation);
						animate_result.setRepeatCount(Animation.INFINITE);
						winner_text.startAnimation(animate_result);
						// Animation block ends here

						diallo.show();

						// Dialog buttons
						winner_play = (Button) diallo
								.findViewById(R.id.win_again);
						// Winner play again
						winner_play.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {

								cunt.start();

								Random rd = new Random();
								String abc = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
								char letter = abc.charAt(rd.nextInt(25));
								entered_word.add(ans); // Adds words to prevent
														// duplicate
								String let = letter + "";
								cluetext.setText(let + choice);
								answerText.setText("");
								diallo.dismiss();
								Toast.makeText(NormalWordCheckActivity.this,
										"LEVEL: " + level, 40000).show();

							}
						});

						winner_exit = (Button) diallo
								.findViewById(R.id.win_exit);

						winner_exit.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								diallo.dismiss();
								finish();

							}
						});

					}

				}// Else statement ends here

			}
		});

		// How to enter a customized font from assests folder
		// Typeface face = Typeface.createFromAsset(getAssets(),
		// "fonts/arial.ttf");
		//
		// timer.setTypeface(face);

	}

	@SuppressLint("NewApi")
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		// Immersive mode
		// getWindow().getDecorView().setSystemUiVisibility(
		// View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		// | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		// | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		// | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		// | View.SYSTEM_UI_FLAG_FULLSCREEN
		// | View.SYSTEM_UI_FLAG_IMMERSIVE);

		// this will display the splash activity in fullscreen without the title
		// bar
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Countdown timer
		cunt = new CountDownTimer(30000, 1000) {

			public void onTick(long millisUntilFinished) {

				timer.setText(String.valueOf(millisUntilFinished / 1000));

			}

			// Put any code you want to run after timer ends
			public void onFinish() {

				// Randomize ads
				image_rand = new Random();
				// ad_slider.setImageResource(slider[image_rand.nextInt(4)]);

				scores = Integer.parseInt(scoreLabel.getText().toString()
						.trim());
				if (no_ques < 10 && level < 15) {

					// inner IF
					ans = answerText.getText().toString().trim();
					clue_code = cluetext.getText().toString().trim();
					if (dbb.fourLetter(ans, clue_code)
							&& !entered_word.contains(ans)) {

						Random rd = new Random();
						String abc = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
						char letter = abc.charAt(rd.nextInt(25));
						entered_word.add(ans); // Adds words to prevent
												// duplicate
						String let = letter + "";
						cluetext.setText(let + choice);
						answerText.setText("");
						scores += 1;
						no_ques += 1;
						Toast.makeText(NormalWordCheckActivity.this,
								no_ques + "/10", 40000).show();
						scoreLabel.setText(scores + "");
						cunt.start();
					}// inner IF ends here

					// inner ELSE
					else {

						Random rd = new Random();
						String abc = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
						char letter = abc.charAt(rd.nextInt(25));
						String let = letter + "";
						cluetext.setText(let + choice);
						answerText.setText("");
						no_ques += 1;
						Toast.makeText(NormalWordCheckActivity.this,
								no_ques + "/10", 40000).show();
						Toast.makeText(NormalWordCheckActivity.this,
								"FAIL ONE", 40000).show();
						cunt.start();

					}// inner ELSE ends here

				}// main IF statement ends here

				else {

					no_ques = 1;
					if (scores <= 4) {

						scores = 0;
						scoreLabel.setText(scores + "");
						level = 1;
						choice = 4;

						// loser_dialog.show();
						cunt.cancel();
						// submitButton.setVisibility(View.INVISIBLE);

						// Intent in = getIntent();
						// finish();
						// startActivity(in);

						loser_dialog = new Dialog(NormalWordCheckActivity.this);
						loser_dialog.setContentView(R.layout.loser_text);
						loser_dialog.setTitle("GAME OVER");

						// Animation block starts here
						loser_text = (TextView) loser_dialog
								.findViewById(R.id.loser_text);
						animate_result = AnimationUtils.loadAnimation(
								getBaseContext(), R.anim.myanimation);
						animate_result.setRepeatCount(Animation.INFINITE);
						loser_text.startAnimation(animate_result);
						// Animation block ends here

						loser_dialog.setCanceledOnTouchOutside(false);

						// Dialog buttons
						loser_play = (Button) loser_dialog
								.findViewById(R.id.lose_again);

						loser_play.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								loser_dialog.dismiss();

								cunt.start();
								// Intent in = getIntent();
								// finish();
								// startActivity(in);

							}
						});

						loser_exit = (Button) loser_dialog
								.findViewById(R.id.lose_exit);

						loser_exit.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

								loser_dialog.dismiss();
								finish();

							}
						});
						//
						loser_dialog.show();

					} else {
						// diallo.show();
						// cunt.cancel();
						// submitButton.setVisibility(View.INVISIBLE);

						scores = 0;
						scoreLabel.setText(scores + "");
						level += 1;
						choice += 1;
						cunt.cancel();

						diallo = new Dialog(NormalWordCheckActivity.this);
						diallo.setContentView(R.layout.winner_text);
						diallo.setTitle("GAME OVER");
						diallo.setCanceledOnTouchOutside(false);

						// Animation block starts here
						winner_text = (TextView) diallo
								.findViewById(R.id.winner_text);
						animate_result = AnimationUtils.loadAnimation(
								getBaseContext(), R.anim.myanimation);
						animate_result.setRepeatCount(Animation.INFINITE);
						winner_text.startAnimation(animate_result);
						// Animation block ends here

						diallo.show();

						// Dialog buttons
						winner_play = (Button) diallo
								.findViewById(R.id.win_again);
						// Winner play again
						winner_play.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {

								cunt.start();

								Random rd = new Random();
								String abc = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
								char letter = abc.charAt(rd.nextInt(25));
								entered_word.add(ans); // Adds words to prevent
														// duplicate
								String let = letter + "";
								cluetext.setText(let + choice);
								answerText.setText("");
								diallo.dismiss();
								Toast.makeText(NormalWordCheckActivity.this,
										"LEVEL: " + level, 40000).show();

							}
						});

						winner_exit = (Button) diallo
								.findViewById(R.id.win_exit);

						winner_exit.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								diallo.dismiss();
								finish();

							}
						});

					}

				}// Else statement ends here

				// finish();
				// cunt.start();

			}
		}.start();

		super.onStart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		cunt.cancel();
		dbb.close(); // close the database
		super.onDestroy();
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

		switch (item.getItemId()) {
		case R.id.abouter:

			Toast.makeText(getBaseContext(), "ABOUT WCC", 400000).show();

			Dialog about = new Dialog(NormalWordCheckActivity.this);
			about.setContentView(R.layout.about_wcc);
			about.setTitle("ABOUT US");
			about.setCanceledOnTouchOutside(true);

			about_text = (TextView) about.findViewById(R.id.abouts);
			about_text.setMovementMethod(LinkMovementMethod.getInstance());
			about_text
					.setText(Html
							.fromHtml(" "
									+ " The Word Check Challenge Version 1.0 (English) Copyright © Hesse Herman Heinrich. All rights reserved.	"
									+ " <br>No part of this software structure or games may be reproduced in any language "
									+ "<br>or form: photocopying or electronic retrieval system or transmitted in any form or by any means "
									+ "<br>without the price permission of the copyright owner."
									+ "<br>The Word Check Challenge operating system and its user interface are protected"
									+ " <br>by trademark and other pending or existing intellectual property rights in Ghana and "
									+ "<br>other countries. The Word Check Challenge educational software was developed for Herman Heinrich Hesse"
									+ " <br>for Anthe Android platform by SPAGX IT Solutions - GH"
									+ "" + " "));

			about.show();

			break;

		case R.id.helper:

			Toast.makeText(getBaseContext(), "HELPER", 400000).show();

			Dialog help = new Dialog(NormalWordCheckActivity.this);
			help.setContentView(R.layout.help_wcc);
			help.setTitle("HELP");
			help.setCanceledOnTouchOutside(true);

			help_text = (TextView) help.findViewById(R.id.helps);
			help_text
					.setText(Html
							.fromHtml("  "
									+ "<h3>The WORD CHECK CHALLENGE SOFTWARE</h3><center>"
									+ "The WORD CHECK CHALLENGE SOFTWARE is an exciting, puzzling word challenge that will require pupils between "
									+ "<br> the ages of 8 to be quizzed through the educative mental spelling games. "
									+ "<br> The WORD CHECK CHALLENGE SOFTWARE was created by Herman Heinrich Hesse and has been specifically "
									+ "<br> designed to test the speed, attention flexibility and problem solving tendency of every student.  "
									+ "<br> The ‘WORD CHECK CHALLENGE SOFTWARE’ breaks away from the traditional way of spelling and prompts students to "
									+ "<br> read more educational materials by adding new vocabulary to their old ones every day."
									+ "<br> The ‘WORD CHECK CHALLENGE SOFTWARE’ has been designed in the form of an educational game to make its usage fun and exciting. The Word Check challenge software comprises of a system "
									+ "<br> based game.<br> "

									+ "<h4>WORD CHECK GAME</h4> – combines alphabets and numbers to generate codes  (A5, Q5, Z5, J8, N9, L20, S25...etc) that  <br> "
									+ "will require player to provide words. To match the codes correctly by count of alphabets in the word within  <br> "
									+ "Thirty seconds(30secs) per question. Egs: A5 = Apple, Awake...etc. B6 = Banker, Bucket...etc.C7 = Candles,  <br> "
									+ "Cynical…etc  <br> <br> <br>"
									+ " </center>"));

			help.show();

			break;

		case R.id.exiter:

			Toast.makeText(getBaseContext(), "EXITING", 400000).show();
			finish();

			break;

		default:

			Toast.makeText(getBaseContext(), "Nothing to see here", 400000)
					.show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		cunt.cancel();
		dbb.close(); // close the database
		super.onBackPressed();
	}

}
