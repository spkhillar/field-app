package com.telenoetica.android.activity;

import java.util.LinkedHashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.jpa.entities.RoutineVisit;

public class RoutineVisitActivity extends AbstractVisitActivity {
  private Button button1;
  private Button button2;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.routine_visit);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
  }

  public void addListenerOnButtonSubmit() {
    button1 = (Button) findViewById(R.id.btn_rv_submit);
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    button1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        //Intent intent = new Intent(context, MainMenu.class);
        //startActivity(intent);
        ViewGroup group = (ViewGroup) findViewById(R.id.ll1_rv);
        getTargetObject(group,valueMap);
        saveVisit(new RoutineVisit(), valueMap);

      }
    });
  }

  private void addListenerOnButtonReset() {
    button2 = (Button) findViewById(R.id.btn_rv_reset);
    button2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Reset r=new Reset();
        ViewGroup group = (ViewGroup) findViewById(R.id.ll1_rv);
        r.clearForm(group);
      }
    });
  }
}
