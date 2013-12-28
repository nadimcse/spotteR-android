package com.butterfly.spotter.android.activities;


import java.util.Timer;
import java.util.TimerTask;

import com.butterfly.spotter.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/** 
*
* @author Nadim
* @since  Dec 18, 2013
*
*/

public class IncomingCallScreenActivity extends Activity {
	Button button;
	MediaPlayer player;
	Timer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callscreen);
		
		player = MediaPlayer.create(this, R.raw.test_song);
		player.start();
		
		TimerTask task = new TimerTask() {
		    @Override
		    public void run() {
		        player.stop();
		    }
		};
		timer = new Timer();
		timer.schedule(task, 10000);
		
		addListenerOnButton();

	}	
	
	public void addListenerOnButton() {
		 
		final Context context = this;
 
		button = (Button) findViewById(R.id.button1);
 
		button.setOnClickListener(new OnClickListener() {
			///add fragment
			@Override
			public void onClick(View arg0) {
				timer.cancel();
				player.stop();
			    Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);  
                finish();            
			}
 
		});
 
	}
}
