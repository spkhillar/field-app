package com.telenoetica.android.activity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.DieselVisit;
public class DieselVisitActivity extends AbstractVisitActivity {
  private Button buttonSubit;
  private Button buttonReset;
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.diesel_visit);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
  }
  public void addListenerOnButtonSubmit() {
    buttonSubit = (Button) findViewById(R.id.btn_dv_submit);
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    buttonSubit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        // intent = new Intent(context, MaintainenceVisit.class);
        // startActivity(intent);
        ViewGroup group = (ViewGroup) findViewById(R.id.ll2_dv);
        List<String> errorList = new ArrayList<String>();
        getTargetObject(group, valueMap,errorList);
        DieselVisit dieselVisit = new DieselVisit();
        dieselVisit.setUserId(AppValuesHolder.getCurrentUser());
        saveVisit(dieselVisit, valueMap);
      }
    });
  }
  private void addListenerOnButtonReset() {
    buttonReset = (Button) findViewById(R.id.btn_dv_reset);
    buttonReset.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        Reset r = new Reset();
        ViewGroup group = (ViewGroup) findViewById(R.id.ll2_dv);
        r.clearForm(group);
      }
    });
  }
}
