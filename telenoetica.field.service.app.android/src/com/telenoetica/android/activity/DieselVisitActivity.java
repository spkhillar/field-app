package com.telenoetica.android.activity;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.jpa.entities.DieselVisit;

public class DieselVisitActivity extends AbstractVisitActivity {
  Button button1;
  Button button2;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.diesel_visit);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
  }

  public void addListenerOnButtonSubmit() {

    final Context context = this;
    button1 = (Button) findViewById(R.id.btn_dv_submit);
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        // intent = new Intent(context, MaintainenceVisit.class);
        // startActivity(intent);
        ViewGroup group = (ViewGroup) findViewById(R.id.ll2_dv);
        getTargetObject(group, valueMap);
        saveVisit(new DieselVisit(), valueMap);

      }

    });

  }

  private void addListenerOnButtonReset() {
    final Context context = this;
    button2 = (Button) findViewById(R.id.btn_dv_reset);
    button2.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {
        Reset r = new Reset();
        ViewGroup group = (ViewGroup) findViewById(R.id.ll2_dv);
        r.clearForm(group);

      }

    });
  }

}
