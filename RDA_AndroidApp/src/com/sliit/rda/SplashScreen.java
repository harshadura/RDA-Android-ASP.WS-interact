package com.sliit.rda;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
//import android.support.v4.app.NavUtils;

public class SplashScreen extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash_screen);

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this,
						Login.class);
				SplashScreen.this.startActivity(intent);
				SplashScreen.this.finish();

			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 2000);

	}

}
