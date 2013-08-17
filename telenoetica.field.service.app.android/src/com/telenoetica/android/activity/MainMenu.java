/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.android.activity;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.telenoetica.android.rest.AndroidConstants;
import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestJsonUtils;
import com.telenoetica.android.rest.RestResponse;
import com.telenoetica.android.sqllite.AndroidVisitSqLiteModel;

public class MainMenu extends ApplicationBaseActivity {
  private Button btnRoutineVisit;
  private Button btnDieselVisit;
  private Button btnMaintenanceVisit;
  private Button btnCalloutVisit;
  private Button btnSendToServer;
  private Button btnConfigure;
  private Button btnExit;

  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // checkForUserIdandPassword();
    setContentView(R.layout.main_menu);
    addListenerOnButtonRV();
    addListenerOnButtonDV();
    addListenerOnButtonMV();
    addListenerOnButtonCV();
    // addListenerOnButtonConfigure();
    addListenerOnButtonSendToServer();
    addListenerOnButtonExit();
  }

  private void addListenerOnButtonSendToServer() {
    btnSendToServer = (Button) findViewById(R.id.button_send_to_server);
    List<AndroidVisitSqLiteModel> dataList = sqLiteDbHandler.getVisitsInSystem();
    if (!CollectionUtils.isEmpty(dataList)) {
      int sendToServerRecordCount = dataList.size();
      btnSendToServer.setText("Send To Server(" + sendToServerRecordCount + ")");
    }
    btnSendToServer.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        List<AndroidVisitSqLiteModel> dataList = sqLiteDbHandler.getVisitsInSystem();
        if (!CollectionUtils.isEmpty(dataList)) {
          AndroidVisitSqLiteModel array[] = new AndroidVisitSqLiteModel[dataList.size()];
          SendToServerAsyncTask task = new SendToServerAsyncTask();
          task.execute(dataList.toArray(array));
        } else {
          RestResponse response = new RestResponse(501, "No pending visit records available in system");
          doWithSendToServerResponse(response);

        }
      }
    });

  }

  public void addListenerOnButtonRV() {
    btnRoutineVisit = (Button) findViewById(R.id.button_rv);
    btnRoutineVisit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, RoutineVisitActivity.class);
        startActivity(intent);
      }
    });
  }

  public void addListenerOnButtonDV() {
    btnDieselVisit = (Button) findViewById(R.id.button_dv);
    btnDieselVisit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, DieselVisitActivity.class);
        startActivity(intent);
      }
    });
  }

  public void addListenerOnButtonMV() {
    btnMaintenanceVisit = (Button) findViewById(R.id.button_mv);
    btnMaintenanceVisit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, MaintainenceVisitActivity.class);
        startActivity(intent);
      }
    });
  }

  public void addListenerOnButtonCV() {
    btnCalloutVisit = (Button) findViewById(R.id.button_cv);
    btnCalloutVisit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, CalloutVisitActivity.class);
        startActivity(intent);
      }
    });
  }

  /*
   * public void addListenerOnButtonConfigure() { btnConfigure = (Button)
   * findViewById(R.id.button_configure); btnConfigure.setOnClickListener(new
   * OnClickListener() {
   * 
   * @Override public void onClick(final View arg0) { Intent intent = new
   * Intent(context, ConfigureActivity.class); startActivity(intent); } }); }
   */

  public void addListenerOnButtonExit() {
    btnExit = (Button) findViewById(R.id.button_exit);
    btnExit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        exitConfirmationDialog();
      }
    });
  }

  public void doWithSendToServerResponse(final RestResponse restResponse) {
    if (restResponse != null) {
      if (restResponse.getStatusCode() == 401) {
        Toast.makeText(this, restResponse.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
      } else if (restResponse.getStatusCode() != 0) {
        Toast.makeText(this, restResponse.getMessage(), Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "Send to server successfull.", Toast.LENGTH_SHORT).show();
      }

    }

  }

  private class SendToServerAsyncTask extends AsyncTask<AndroidVisitSqLiteModel, Void, RestResponse> {
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
    protected RestResponse doInBackground(final AndroidVisitSqLiteModel... params) {
      long errorCode = 0;
      Date start = new Date();
      RestResponse response = null;
      AppValuesHolder.clearSentRecordCount();
      for (AndroidVisitSqLiteModel androidVisitSqLiteModel : params) {
        try {
          Class<?> currentClazz = Class.forName(androidVisitSqLiteModel.getClazzName());
          AppValuesHolder.addSentRecord(currentClazz.getSimpleName());
          String url = AppValuesHolder.getHost() + determinePath(currentClazz);
          LOGGER.debug(androidVisitSqLiteModel + "....invoking..." + url);
          Object postObject = determinePostObject(currentClazz, androidVisitSqLiteModel.getJson());
          response =
              RestClient.INSTANCE.executeRest(url, AppValuesHolder.getCurrentUser(),
                AppValuesHolder.getCurrentUserPassword(), HttpMethod.POST, postObject, RestResponse.class,
                MediaType.APPLICATION_JSON);
          if (response != null && response.getStatusCode() != 0) {
            androidVisitSqLiteModel.setTries(androidVisitSqLiteModel.getTries() + 1);
            androidVisitSqLiteModel.setStatus(AndroidConstants.FAILED_STATUS);
            sqLiteDbHandler.updateVisit(androidVisitSqLiteModel);
          } else if (response != null && response.getStatusCode() == 0) {
            sqLiteDbHandler.deleteVisit(androidVisitSqLiteModel.getId());
          }
        } catch (Exception e) {
          LOGGER.error("Exception send to server...", e);
          if (e.getCause() instanceof HttpClientErrorException) {
            HttpStatus status = ((HttpClientErrorException) e.getCause()).getStatusCode();
            if (HttpStatus.UNAUTHORIZED.equals(status)) {
              response = new RestResponse(401, "Invalid Credentials. Check username and password");
            } else if (HttpStatus.FORBIDDEN.equals(status)) {
              response = new RestResponse(403, "Invalid Credentials. Check username and password");
            }
          } else {
            response = new RestResponse(500, "System Exception...");
          }
        }
        if (response != null && response.getStatusCode() != 0) {
          errorCode = 1;
        }
      }
      Date end = new Date();
      long total = end.getTime() - start.getTime();
      LOGGER.debug("...Total Time..." + total);
      if (errorCode == 0) {
        response = new RestResponse(0, "Sent successfully.");
        Intent intent = new Intent(context, ReportActivity.class);
        startActivity(intent);
      } else {
        response = new RestResponse(1, "Sending failed.");
      }
      return response;

    }

    private Object determinePostObject(final Class<?> currentClazz, final String json) throws JsonParseException,
        JsonMappingException, IOException {
      return RestJsonUtils.fromJSONString(json, currentClazz);
    }

    @Override
    protected void onPostExecute(final RestResponse restResponse) {
      pd.dismiss();
      doWithSendToServerResponse(restResponse);
    }
  }

  public void exitConfirmationDialog() {

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
    alertDialog.setTitle("Exit");
    alertDialog.setMessage("Are you sure?");

    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(final DialogInterface dialog, final int which) {
        finish();
        System.exit(0);
        // Intent intent = new Intent(context, LoginActivity.class);
        // startActivity(intent);
      }
    });

    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(final DialogInterface dialog, final int which) {
        // Write your code here to invoke NO event
        dialog.cancel();
      }
    });
    alertDialog.show();

  }

}