package com.telenoetica.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MaintainenceVisit extends Activity {
	Button button1;
	Button button2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintainence_visit);
		addListenerOnButtonSubmit();
		addListenerOnButtonReset();
	}

	public void addListenerOnButtonSubmit() {

		final Context context = this;
		Button button;

		button1 = (Button) findViewById(R.id.btn_mv_submit);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, CalloutVisit.class);
				startActivity(intent);

			}

		});
	}

	private void addListenerOnButtonReset() {
		final Context context = this;
		button2 = (Button) findViewById(R.id.btn_mv_reset);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Reset r = new Reset();
				ViewGroup group = (ViewGroup) findViewById(R.id.ll3_mv);
				r.clearForm(group);

			}

		});
	}

}
