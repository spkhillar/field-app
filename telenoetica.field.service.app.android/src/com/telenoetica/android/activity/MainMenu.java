/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */

package com.telenoetica.android.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.telenoetica.android.sqllite.AndroidVisitSqLiteModel;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public class MainMenu extends Activity {
  private Button btnRoutineVisit;
  private Button btnDieselVisit;
  private Button btnMaintenanceVisit;
  private Button btnCalloutVisit;
  private Button btnSendToServer;
  private Button btnConfigure;
  private Button btnExit;
  private Context context;
  private SQLiteDbHandler sqLiteDbHandler;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = this;
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.main_menu);
    sqLiteDbHandler = new SQLiteDbHandler(this);
    addListenerOnButtonRV();
    addListenerOnButtonDV();
    addListenerOnButtonMV();
    addListenerOnButtonCV();
    addListenerOnButtonConfigure();
    addListenerOnButtonSendToServer();
    addListenerOnButtonExit();
  }

  private void addListenerOnButtonSendToServer() {
    btnSendToServer = (Button) findViewById(R.id.button_send_to_server);
    btnSendToServer.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        List<AndroidVisitSqLiteModel> dataList = sqLiteDbHandler.getVisitsInSystem();
        for (AndroidVisitSqLiteModel androidVisitSqLiteModel : dataList) {
          /* androidVisitSqLiteModel.setTries(1);
          androidVisitSqLiteModel.setStatus(AndroidConstants.FAILED_STATUS);
          sqLiteDbHandler.updateVisit(androidVisitSqLiteModel);*/
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

  public void addListenerOnButtonConfigure() {
    btnConfigure = (Button) findViewById(R.id.button_configure);
    btnConfigure.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, ConfigureActivity.class);
        startActivity(intent);
      }
    });
  }

  public void addListenerOnButtonExit() {
    btnExit = (Button) findViewById(R.id.button_exit);
    btnExit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
      }
    });
  }
}