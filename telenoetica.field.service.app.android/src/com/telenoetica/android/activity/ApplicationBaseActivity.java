package com.telenoetica.android.activity;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public abstract class ApplicationBaseActivity extends Activity {

  protected SQLiteDbHandler sqLiteDbHandler;
  protected Context context;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = this;
    sqLiteDbHandler = new SQLiteDbHandler(this);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    initializeActivity(savedInstanceState);
  }

  protected void checkForUserIdandPassword() {
    if (StringUtils.isBlank(AppValuesHolder.getCurrentUser())
        || StringUtils.isBlank(AppValuesHolder.getCurrentUserPassword())) {
      Intent intent = new Intent(context, LoginActivity.class);
      startActivity(intent);
    }
  }

  protected abstract void initializeActivity(Bundle savedInstanceState);

}
