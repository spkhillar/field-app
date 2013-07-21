package com.telenoetica.android.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.MaintenanceVisit;

public class MaintainenceVisitActivity extends AbstractVisitActivity {
  private Button buttonSubmit;

  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // checkForUserIdandPassword();
    setContentView(R.layout.maintainence_visit);
    addListenerOnButtonSubmit();
    addItemsOnSpinner(R.id.spinner_category_of_maintainence, AppValuesHolder.getMaintenanceCategories());
    addItemsOnSpinner(R.id.spinner_consumable1, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable2, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable3, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable4, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable5, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_consumable6, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares1, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares2, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares3, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares4, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares5, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_spares6, AppValuesHolder.getSpares());

  }

  public void addListenerOnButtonSubmit() {

    final Context context = this;
    buttonSubmit = (Button) findViewById(R.id.btn_mv_submit);
    buttonSubmit.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        // Intent intent = new Intent(context, CalloutVisit.class);
        // startActivity(intent);
        renderConfirmationDialog();
      }
    });
  }

  @Override
  public void saveCurrentActivity() {
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    ViewGroup group = (ViewGroup) findViewById(R.id.ll3_mv);
    List<String> errorList = new ArrayList<String>();
    getTargetObject(group, valueMap, errorList);
    if (CollectionUtils.isEmpty(errorList)) {
      MaintenanceVisit maintenanceVisit = new MaintenanceVisit();
      maintenanceVisit.setUserId(AppValuesHolder.getCurrentUser());
      saveVisit(maintenanceVisit, valueMap);
    } else {
      LOGGER.error("Validation failed");
    }
  }

}
