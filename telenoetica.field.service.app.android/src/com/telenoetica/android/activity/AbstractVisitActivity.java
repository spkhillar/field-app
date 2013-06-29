package com.telenoetica.android.activity;

import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AbstractVisitActivity extends Activity {

  public void addItemsOnSpinner(final int spinnerId, final List<String> spinnerValues) {

    Spinner spinner;
    spinner = (Spinner) findViewById(spinnerId);
    ArrayAdapter<String> spinnerAdapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerValues);
    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(spinnerAdapter);

  }

}
