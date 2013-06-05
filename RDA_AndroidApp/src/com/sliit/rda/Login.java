package com.sliit.rda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends Activity {

	private Button butClose;
	private Button btLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);

		butClose = (Button) findViewById(R.id.login_view_bt_cancel);
		butClose.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onBackPressed();
			}
		});

		btLogin = (Button) findViewById(R.id.login_view_bt_login);
		btLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(Login.this,
						AndroidclientActivity.class);
				Login.this.startActivity(intent);
				Toast.makeText(getApplicationContext(),
						"Welcome to RDA Mobile!", Toast.LENGTH_LONG)
						.show();
			}
		});

	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(this, R.style.CustomTheme));
		builder.setTitle(R.string.app_name);
		builder.setMessage("Are you sure want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Login.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
}
