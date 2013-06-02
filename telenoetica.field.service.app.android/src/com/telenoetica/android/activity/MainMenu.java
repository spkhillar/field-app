/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */

package com.telenoetica.android.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainMenu extends ListActivity  {

	private static final String[] MENU_ITEM = new String[] { "ROUTINE VISIT","DIESEL VISIT","MAINTENANCE VISIT","CALL-OUT VISIT","EXIT" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.main_menu,MENU_ITEM));
        ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener((OnItemClickListener) this);
	}
	
	void OnItemClick(AdapterView<?> a, View v, int position, long id) {
	         if(position==0){
	             Intent i = new Intent(this, RoutineVisit.class);
	             startActivity(i); 
	}
}
}