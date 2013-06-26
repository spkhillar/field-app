package com.telenoetica.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

  Button button1;

  private EditText userName;

  private EditText password;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);
    addListenerOnButtonLogin();
  }

  public void addListenerOnButtonLogin() {

    button1 = (Button) findViewById(R.id.btn1_main);
    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {
        userName = (EditText)findViewById(R.id.et1_main_uid);
        password = (EditText)findViewById(R.id.et2_main_password);
        String []array = new String[]{userName.getText().toString(),password.getText().toString()};
        LoginAsyncTask task = new LoginAsyncTask();
        task.execute(array);
      }

    });
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

}