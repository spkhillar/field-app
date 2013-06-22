package com.telenoetica.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RoutineVisit extends Activity {


	Button button1;
	Button button2;
	
	
//	et.setFilters(new InputFilter[]{new InputFilterMinMax("1","200")});
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routine_visit);
		addListenerOnButtonSubmit();
		addListenerOnButtonReset();

		
	}

	public void addListenerOnButtonSubmit() {
		
		final Context context = this;
		button1 = (Button) findViewById(R.id.btn_rv_submit);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				
				Intent intent = new Intent(context, DieselVisit.class);
				startActivity(intent);

			}

		});
	}

	private void addListenerOnButtonReset() {
		final Context context = this;
		button2 = (Button) findViewById(R.id.btn_rv_reset);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
                Reset r=new Reset();
				ViewGroup group = (ViewGroup) findViewById(R.id.ll1_rv);
				r.clearForm(group);

			}

		});
	}

}
