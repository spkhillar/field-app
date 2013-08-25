/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import java.util.Map;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.RoutineVisit;

/**
 * The Class ReportActivity.
 */
public class ReportActivity extends ApplicationBaseActivity {

  /** The btn report. */
  Button btnReport;

  /**
   * Initialize activity.
   *
   * @param savedInstanceState the saved instance state
   * @see com.telenoetica.android.activity.ApplicationBaseActivity#initializeActivity(android.os.Bundle)
   */
  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    setContentView(R.layout.report);
    populateReport();
  }

  /**
   * Populate report.
   */
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
}
