package com.telenoetica.android.activity;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.CallOutVisit;

public class CalloutVisitActivity extends AbstractVisitActivity {
  private Button button1;
  private Button button2;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.callout_visit);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
    // showDateTime();
    addItemsOnSpinner(R.id.spinner_main_category_of_fault, AppValuesHolder.getFaults());
    addItemsOnSpinner(R.id.spinner_equipment_causing_fault, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_customer1, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer2, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer3, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer4, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_equip_comp_repaired, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_equip_comp_replaced, AppValuesHolder.getSpares());
  }

  /*
   * public void showDateTime() { Context context; // Dialog dialog = new
   * Dialog(context); dialog.setContentView(R.layout.callout_visit);
   * dialog.setTitle("Choose Time & Date"); TimePicker tp = (TimePicker)
   * dialog.findViewById(R.id.timePicker1); //
   * tp.setOnTimeChangedListener(TimePicker.OnTimeChangedListener()));
   * 
   * }
   */

  public void setOnTimeChangeListener() {

  }

  public void addListenerOnButtonSubmit() {

    final Context context = this;
    button1 = (Button) findViewById(R.id.btn_cv_submit);
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        // Intent intent = new Intent(context, MainMenu.class);
        // startActivity(intent);
        ViewGroup group = (ViewGroup) findViewById(R.id.ll4_cv);
        getTargetObject(group, valueMap);
        saveVisit(new CallOutVisit(), valueMap);

      }

    });

  }

  private void addListenerOnButtonReset() {
    final Context context = this;
    button2 = (Button) findViewById(R.id.btn_cv_reset);
    button2.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {
        Reset r = new Reset();
        ViewGroup group = (ViewGroup) findViewById(R.id.ll4_cv);
        r.clearForm(group);

      }

    });
  }

}