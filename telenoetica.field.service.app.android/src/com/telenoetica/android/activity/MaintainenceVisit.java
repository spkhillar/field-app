package com.telenoetica.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.android.rest.AppValuesHolder;

public class MaintainenceVisit extends AbstractVisitActivity {
  Button button1;
  Button button2;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.maintainence_visit);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
    addItemsOnSpinner(R.id.spinner_category_of_maintainence, AppValuesHolder.getMaintenanceCategories());
    addItemsOnSpinner(R.id.spinner_consumable1, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable2, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable3, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable4, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable5, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares1, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares2, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares3, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares4, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares5, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares6, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares7, AppValuesHolder.getSpares());
  }

  public void addListenerOnButtonSubmit() {

    final Context context = this;
    Button button;

    button1 = (Button) findViewById(R.id.btn_mv_submit);

    button1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        Intent intent = new Intent(context, CalloutVisit.class);
        startActivity(intent);

      }

    });
  }

  private void addListenerOnButtonReset() {
    final Context context = this;
    button2 = (Button) findViewById(R.id.btn_mv_reset);
    button2.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {
        Reset r = new Reset();
        ViewGroup group = (ViewGroup) findViewById(R.id.ll3_mv);
        r.clearForm(group);

      }

    });
  }

}
