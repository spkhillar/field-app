package com.telenoetica.android.activity;

import java.util.Date;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import android.os.AsyncTask;

import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;
import com.telenoetica.jpa.entities.CallOutVisit;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {

  @Override
  protected String doInBackground(final Void... params) {

    System.err.println("...spring app- rest123--");
    String url = "http://192.168.1.2:8080/fieldapp/rest/auth";

    RestResponse response = RestClient.INSTANCE.executeRest(url, "sunny", "sunny", HttpMethod.GET, null, RestResponse.class, null);
    if(response != null){
      System.err.println("..auth request..."+response.getMessage());
    }
    CallOutVisit postObject = getCalloutVisitEntity();
    url="http://192.168.1.2:8080/fieldapp/callout/rest";
    response = RestClient.INSTANCE.executeRest(url, "sunny", "sunny", HttpMethod.POST, postObject, RestResponse.class, MediaType.APPLICATION_JSON);
    if(response != null){
      System.err.println("..callout request..."+response.getMessage());
    }

    return null;
  }

  private CallOutVisit getCalloutVisitEntity(){
    CallOutVisit callOutVisit = new CallOutVisit();
    callOutVisit.setAccessCode("AAA");

    callOutVisit.setUserId("root");
    callOutVisit.setSiteId("HT/SW/OY/007");
    callOutVisit.setTimeComplainReceived(new Date());
    callOutVisit.settimeFaultResolved(new Date());
    callOutVisit.setTimeReachedToSite(new Date());
    return callOutVisit;
  }

}
