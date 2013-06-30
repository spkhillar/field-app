package com.telenoetica.android.activity;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.telenoetica.android.rest.AppValuesPopulator;
import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public class LoginActivity extends Activity {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginActivity.class);
  Button button1;

  private EditText userName;

  private EditText password;

  private SQLiteDbHandler sqLiteDbHandler;

  private boolean userExistsInLocal;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    addListenerOnButtonLogin();
    addListenerOnButtonPass();
    sqLiteDbHandler = new SQLiteDbHandler(this);
  }

  public void addListenerOnButtonLogin() {
    button1 = (Button) findViewById(R.id.btn1_main);
    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        userName = (EditText) findViewById(R.id.et1_main_uid);
        password = (EditText) findViewById(R.id.et2_main_password);
        String[] array = new String[] { userName.getText().toString(), password.getText().toString() };

        LOGGER.info("Logging to the system...");

        userExistsInLocal = sqLiteDbHandler.validateUser(array[0], array[1]);

        if(userExistsInLocal){
          LOGGER.info("User verified from local");
          sqLiteDbHandler.checkBaseDataInSystem();
          RestResponse response = new RestResponse(0, "Logged In");
          doWithResponse(response);
        }else{
          LoginAsyncTask task = new LoginAsyncTask();
          task.execute(array);
        }
      }

    });
  }

  private void doWithResponse(final RestResponse result) {
    LOGGER.info("Logging to the system. done.."+result);
    int i = result.getStatusCode();
    if (i == 0) {

      if(!userExistsInLocal){
        sqLiteDbHandler.insertUser(userName.getText().toString(), password.getText().toString());
        sqLiteDbHandler.checkAndInsertBaseData();
      }

      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(this, MainMenu.class);
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
      Date start = new Date();
      String url = "http://192.168.1.103:8082/fieldapp/rest/auth";
      RestResponse response = null;
      try {
        LOGGER.debug("invoking..."+url);
        response =
            RestClient.INSTANCE.executeRest(url, params[0], params[1], HttpMethod.GET, null, RestResponse.class, null);
        AppValuesPopulator.populateValues( params[0], params[1]);
        /*
         * CallOutVisit postObject = getCalloutVisitEntity();
         * url="http://192.168.1.104:8080/fieldapp/callout/rest"; response =
         * RestClient.INSTANCE.executeRest(url, "sunny", "sunny",
         * HttpMethod.POST, postObject, RestResponse.class,
         * MediaType.APPLICATION_JSON); if(response != null){
         * System.err.println("..callout request..."+response.getMessage()); }
         */
      } catch (Exception e) {
        LOGGER.error("Exception...",e);
        response = new RestResponse(500, "System Exception.");
      }
      if(response == null){
        response = new RestResponse(500, "Rest invocation failed...");
      }
      Date end = new Date();
      long total = end.getTime() - start.getTime();

      System.out.println("...Total Time..."+total);
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
