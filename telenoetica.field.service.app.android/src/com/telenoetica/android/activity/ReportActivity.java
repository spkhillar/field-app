package com.telenoetica.android.activity;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.RoutineVisit;

public class ReportActivity extends ApplicationBaseActivity {
  Button btnReport;

  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    setContentView(R.layout.report);
    addListenerOnButtonOk();
    populateReport();
  }

  private void populateReport() {
    TextView reportTextView = null;
    for (Map.Entry<String, Long> report : AppValuesHolder.getSentRecordCountMap().entrySet()) {

      if (RoutineVisit.class.getSimpleName().equals(report.getKey())) {
        reportTextView = (TextView) findViewById(R.id.reportRoutineVisit);
      } else if (CallOutVisit.class.getSimpleName().equals(report.getKey())) {
        reportTextView = (TextView) findViewById(R.id.reportCalloutVisit);
      } else if (MaintenanceVisit.class.getSimpleName().equals(report.getKey())) {
        reportTextView = (TextView) findViewById(R.id.reportMaintenanceVisit);
      } else if (DieselVisit.class.getSimpleName().equals(report.getKey())) {
        reportTextView = (TextView) findViewById(R.id.reportDieselVisit);
      } else {
        throw new IllegalArgumentException("unknow value populated in record count " + report.getKey());
      }

      reportTextView.setText(report.getValue().toString());
    }
    AppValuesHolder.clearSentRecordCount();
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
