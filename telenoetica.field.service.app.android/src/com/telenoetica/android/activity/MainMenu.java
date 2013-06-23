/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */

package com.telenoetica.android.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		addListenerOnButtonRV();
		addListenerOnButtonDV();
		addListenerOnButtonMV();
		addListenerOnButtonCV();
		addListenerOnButtonExit();
	}

	public void addListenerOnButtonRV() {

		final Context context = this;

		button1 = (Button) findViewById(R.id.button_rv);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, RoutineVisit.class);
				startActivity(intent);

			}

		});
	}
	
	public void addListenerOnButtonDV() {

		final Context context = this;

		button2 = (Button) findViewById(R.id.button_dv);

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, DieselVisit.class);
				startActivity(intent);

			}

		});
	}
	public void addListenerOnButtonMV() {

		final Context context = this;

		button3 = (Button) findViewById(R.id.button_mv);

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, MaintainenceVisit.class);
				startActivity(intent);

			}

		});
}
	
	public void addListenerOnButtonCV() {

		final Context context = this;

		button5 = (Button) findViewById(R.id.button_cv);

		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, CalloutVisit.class);
				startActivity(intent);

			}

		});
}
	
	
	public void addListenerOnButtonExit() {

		final Context context = this;

		button4 = (Button) findViewById(R.id.button_exit);

		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, LoginActivity.class);
				startActivity(intent);

			}

		});
}
	}