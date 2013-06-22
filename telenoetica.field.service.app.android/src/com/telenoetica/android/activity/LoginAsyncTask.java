package com.telenoetica.android.activity;

import android.os.AsyncTask;

import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {

	@Override
	protected String doInBackground(Void... params) {
		
		System.err.println("...spring app- rest--");
		
		RestResponse restResponse = RestClient.INSTANCE.executePost(
				"http://192.168.1.2:8080/fieldapp/rest/auth", null, "sunny","sunny");
		
		if(restResponse != null){
			System.err.println("....Response ..."+restResponse);
		}
		
		return restResponse.getMessage();
	}

}
