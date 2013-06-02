package com.telenoetica.android.activity;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RoutineVisit extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routinevisit);
		System.out.println("Came here");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
