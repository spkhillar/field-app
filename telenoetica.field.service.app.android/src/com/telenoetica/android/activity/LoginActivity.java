package com.telenoetica.android.activity;

import org.springframework.http.HttpMethod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;

public class LoginActivity extends Activity {

  Button button1;

  private EditText userName;

  private EditText password;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    addListenerOnButtonLogin();
    addListenerOnButtonPass();
  }

  public void addListenerOnButtonLogin() {
    final Context context = this;
    button1 = (Button) findViewById(R.id.btn1_main);
    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        userName = (EditText) findViewById(R.id.et1_main_uid);
        password = (EditText) findViewById(R.id.et2_main_password);
        String[] array = new String[] { userName.getText().toString(), password.getText().toString() };
        LoginAsyncTask task = new LoginAsyncTask();
        task.execute(array);

      }

    });
  }

  private void doWithResponse(final RestResponse result) {

    int i = result.getStatusCode();
    if (i == 0) {
      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
      final Context context = this;
      Intent intent = new Intent(context, MainMenu.class);
      startActivity(intent);

    } else {
      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }

  public void addListenerOnButtonPass() {

    final Context context = this;

    button1 = (Button) findViewById(R.id.btn1_pass);

    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        Intent intent = new Intent(context, MainMenu.class);
        startActivity(intent);

      }

    });
  }

  private class LoginAsyncTask extends AsyncTask<String, Void, RestResponse> {

    @Override
    protected RestResponse doInBackground(final String... params) {

      System.err.println("...spring app- rest123--");
      String url = "http://192.168.1.104:8082/fieldapp/rest/auth";
      RestResponse response = null;
      try {

        response =
            RestClient.INSTANCE.executeRest(url, params[0], params[1], HttpMethod.GET, null, RestResponse.class, null);
        /*
         * CallOutVisit postObject = getCalloutVisitEntity();
         * url="http://192.168.1.104:8080/fieldapp/callout/rest"; response =
         * RestClient.INSTANCE.executeRest(url, "sunny", "sunny",
         * HttpMethod.POST, postObject, RestResponse.class,
         * MediaType.APPLICATION_JSON); if(response != null){
         * System.err.println("..callout request..."+response.getMessage()); }
         */
      } catch (Exception e) {
        System.out.println("..Failed, in rest client..." + e.getMessage());
        response = new RestResponse(500, "System Exception.");
      }

      return response;
    }

    @Override
    protected void onPostExecute(final RestResponse restResponse) {
      doWithResponse(restResponse);
    }

    /*
     * private CallOutVisit getCalloutVisitEntity() { CallOutVisit callOutVisit
     * = new CallOutVisit(); callOutVisit.setAccessCode("AAA");
     * 
     * callOutVisit.setUserId("root"); callOutVisit.setSiteId("HT/SW/OY/007");
     * callOutVisit.setTimeComplainReceived(new Date());
     * callOutVisit.settimeFaultResolved(new Date());
     * callOutVisit.setTimeReachedToSite(new Date()); return callOutVisit; }
     */

  }
}
