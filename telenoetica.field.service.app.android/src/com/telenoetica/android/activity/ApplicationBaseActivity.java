package com.telenoetica.android.activity;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public abstract class ApplicationBaseActivity extends Activity {

  protected SQLiteDbHandler sqLiteDbHandler;
  protected Context context;

  private LogoutReciever logoutReciever;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = this;
    sqLiteDbHandler = new SQLiteDbHandler(this);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("com.package.ACTION_LOGOUT");
    logoutReciever = new LogoutReciever();
    registerReceiver(logoutReciever, intentFilter);
    initializeActivity(savedInstanceState);
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(logoutReciever);
  }

  protected void checkForUserIdandPassword() {
    if (StringUtils.isBlank(AppValuesHolder.getCurrentUser())
        || StringUtils.isBlank(AppValuesHolder.getCurrentUserPassword())) {
      Intent intent = new Intent(context, LoginActivity.class);
      startActivity(intent);
    }
  }

  protected abstract void initializeActivity(Bundle savedInstanceState);

  private class LogoutReciever extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
      finish();
    }
  }
}
