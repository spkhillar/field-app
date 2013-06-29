package com.telenoetica.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.android.rest.AppValuesHolder;

public class CalloutVisit extends AbstractVisitActivity {
  private Button button1;
  private Button button2;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.callout_visit);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
    addItemsOnSpinner(R.id.spinner_main_category_of_fault, AppValuesHolder.getFaults());
    addItemsOnSpinner(R.id.spinner_customer1, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer2, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer3, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer4, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_equip_comp_repaired, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_equip_comp_replaced, AppValuesHolder.getSpares());
  }

  public void addListenerOnButtonSubmit() {

    final Context context = this;
    Button button;

    button1 = (Button) findViewById(R.id.btn_cv_submit);

    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        Intent intent = new Intent(context, MainMenu.class);
        startActivity(intent);

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