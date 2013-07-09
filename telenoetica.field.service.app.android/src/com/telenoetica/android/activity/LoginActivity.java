package com.telenoetica.android.activity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.rest.AppValuesPopulator;
import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public class LoginActivity extends Activity {
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginActivity.class);
  private Button buttonLogin;
  private EditText userName;
  private EditText password;
  private SQLiteDbHandler sqLiteDbHandler;
  private boolean userExistsInLocal;
  private Context context;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.login);
    addListenerOnButtonLogin();
    addListenerOnButtonPass();
    sqLiteDbHandler = new SQLiteDbHandler(this);
    context = this;
  }

  public void addListenerOnButtonLogin() {
    buttonLogin = (Button) findViewById(R.id.btn1_main);
    buttonLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        userName = (EditText) findViewById(R.id.et1_main_uid);
        password = (EditText) findViewById(R.id.et2_main_password);
        String[] array = new String[] { userName.getText().toString(), password.getText().toString() };
        LOGGER.info("Logging to the system...");
        if(StringUtils.isBlank(array[0]) || StringUtils.isBlank(array[1]) ){
          RestResponse response = new RestResponse(500, "Username or password is blank.");
          doWithResponse(response);
        }else{
          userExistsInLocal = sqLiteDbHandler.validateUser(array[0], array[1]);
          if (userExistsInLocal) {
            LOGGER.info("User verified from local");
            sqLiteDbHandler.checkBaseDataInSystem();
            RestResponse response = new RestResponse(0, "Logged In");
            doWithResponse(response);
          } else {
            LoginAsyncTask task = new LoginAsyncTask();
            task.execute(array);
          }
        }
      }
    });
  }

  private void doWithResponse(final RestResponse result) {
    LOGGER.info("Logging to the system. done.." + result);
    int statusCode = result.getStatusCode();
    String uname = userName.getText().toString();
    String pwd = password.getText().toString();
    if (statusCode == 0) {
      if (!userExistsInLocal) {
        sqLiteDbHandler.insertOrUpdateUser(uname, pwd);
        sqLiteDbHandler.checkAndInsertBaseData();
      }
      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(this, MainMenu.class);
      startActivity(intent);
    } else {
      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }
    AppValuesHolder.setCurrentUser(uname);
    AppValuesHolder.setCurrentUserPassword(pwd);
  }

  public void addListenerOnButtonPass() {
    buttonLogin = (Button) findViewById(R.id.btn1_pass);
    buttonLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, MainMenu.class);
        startActivity(intent);
      }
    });
  }

  private class LoginAsyncTask extends AsyncTask<String, Void, RestResponse> {
    private ProgressDialog pd;
    @Override
    protected void onPreExecute() {
      pd = new ProgressDialog(context);
      pd.setTitle("Processing...");
      pd.setMessage("Please wait.");
      pd.setCancelable(false);
      pd.setIndeterminate(true);
      pd.show();
    }

    @Override
    protected RestResponse doInBackground(final String... params) {
      Date start = new Date();
      String url = AppValuesHolder.getHost() + "/rest/auth";
      RestResponse response = null;
      try {
        LOGGER.debug("invoking..." + url);
        response =
            RestClient.INSTANCE.executeRest(url, params[0], params[1], HttpMethod.GET, null, RestResponse.class, null);
        if (AppValuesHolder.getClients().size() == 1) {
          AppValuesPopulator.populateValues(params[0], params[1]);
        }
      } catch (Exception e) {
        LOGGER.error("Exception...", e);
        if(e.getCause() instanceof HttpClientErrorException){
          HttpStatus status = ((HttpClientErrorException)e.getCause()).getStatusCode();
          if(HttpStatus.UNAUTHORIZED.equals(status)){
            response = new RestResponse(500, "Invalid Credentials. Check username and password");
          }
        }
      }
      if (response == null) {
        response = new RestResponse(500, "System Exception...");
      }
      Date end = new Date();
      long total = end.getTime() - start.getTime();
      LOGGER.debug("...Total Time..." + total);
      return response;
    }

    @Override
    protected void onPostExecute(final RestResponse restResponse) {
      pd.dismiss();
      findViewById(R.id.btn1_main).setEnabled(true);
      doWithResponse(restResponse);
    }
  }
}
