package com.telenoetica.android.activity;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import android.os.AsyncTask;

import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;
import com.telenoetica.jpa.entities.CallOutVisit;

public class CalloutAsyncTask extends AsyncTask<Object, Void, String> {

  @Override
  protected String doInBackground(final Object... params) {

    System.err.println("...spring app- rest123--");
    String url = "http://54.225.71.93:8080/fieldapp";
    String userName = params[0].toString();
    String password = params[1].toString();
    CallOutVisit visit = (CallOutVisit) params[2];

    url = "http://54.225.71.93:8080/fieldapp";
    RestResponse response =
        RestClient.INSTANCE.executeRest(url, userName, password, HttpMethod.POST, visit, RestResponse.class,
          MediaType.APPLICATION_JSON);
    if (response != null) {
      System.err.println("..callout request..." + response.getMessage());
    }

    return null;
  }

}
