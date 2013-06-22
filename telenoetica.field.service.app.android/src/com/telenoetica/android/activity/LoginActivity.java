package com.telenoetica.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	
	Button button1;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	    addListenerOnButtonLogin();
}
	
public void addListenerOnButtonLogin() {
		
		final Context context = this;
		button1 = (Button) findViewById(R.id.btn1_main);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				
				LoginAsyncTask task = new LoginAsyncTask();
                task.execute();
			}

		});
	}

public void addListenerOnButtonPass() {

	final Context context = this;

	button1 = (Button) findViewById(R.id.btn1_pass);

	button1.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			Intent intent = new Intent(context, MainMenu.class);
			startActivity(intent);

		}

	});
}
	
}