package com.telenoetica.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReportActivity extends ApplicationBaseActivity {
  Button btnReport;

  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    setContentView(R.layout.report);
    addListenerOnButtonOk();
  }

  public void addListenerOnButtonOk() {
    btnReport = (Button) findViewById(R.id.btnReportOk);
    btnReport.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Intent intent = new Intent(context, MainMenu.class);
        startActivity(intent);
      }
    });
  }
}
