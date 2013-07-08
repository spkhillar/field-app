package com.telenoetica.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public class ConfigureActivity extends Activity {
  private Button btnSubmit;

  //Shall clear all the tables from the local handheld device
  private CheckBox resetSystemChkbox;

  //Shall clear data from the spinner tables. does not include visit and configuration table
  private CheckBox resetConfigurationChkBox;

  private SQLiteDbHandler sqLiteDbHandler;

  private EditText hostConfiguredIpAddressEditText;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.configure);
    sqLiteDbHandler = new SQLiteDbHandler(this);
    addBtnSubmitAction();
  }


  private void addBtnSubmitAction() {
    btnSubmit = (Button) findViewById(R.id.btn_config_submit);
    resetSystemChkbox = (CheckBox) findViewById(R.id.chk_reset_system);
    resetConfigurationChkBox = (CheckBox) findViewById(R.id.chk_reset_config);
    hostConfiguredIpAddressEditText = (EditText) findViewById(R.id.et_config_address);
    final boolean resetSys = resetSystemChkbox.isChecked();
    final boolean resetConfig = resetConfigurationChkBox.isChecked();
    final String hostConfiguredIpAddress = hostConfiguredIpAddressEditText.getText().toString();


    btnSubmit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        if(resetSys){
          sqLiteDbHandler.resetConfiguration(true);
        }else if(resetConfig){
          sqLiteDbHandler.resetConfiguration(false);
        }

        if(hostConfiguredIpAddress != null && !AppValuesHolder.getHost().equals(hostConfiguredIpAddress)){
          AppValuesHolder.setHost(hostConfiguredIpAddress);
        }
      }
    });
  }
}