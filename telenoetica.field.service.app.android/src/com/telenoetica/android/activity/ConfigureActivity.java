package com.telenoetica.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class ConfigureActivity extends Activity {

  Button button1;
  Button button2;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.configure);
    // addListenerOnButtonSubmit();
    // addListenerOnButtonReset();

  }
}