package com.telenoetica.android.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.DieselVisit;

public class DieselVisitActivity extends AbstractVisitActivity {
  private Button buttonSubmit;

  public void addListenerOnButtonSubmit() {
    buttonSubmit = (Button) findViewById(R.id.btn_dv_submit);
    buttonSubmit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        // intent = new Intent(context, MaintainenceVisit.class);
        // startActivity(intent);
        renderConfirmationDialog();
      }
    });
  }

  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // checkForUserIdandPassword();
    setContentView(R.layout.diesel_visit);
    addListenerOnButtonSubmit();
    registerListenerForDieselTransferOrBulk();
    registerListenerForPhcn();
    registerListenerForHybridPiu();
    addItemsOnSpinner(R.id.etBulk, AppValuesHolder.getDieselVendors());
    setupAutoCompleteSite();
  }

  @Override
  public void saveCurrentActivity() {
    // TODO Auto-generated method stub
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    ViewGroup group = (ViewGroup) findViewById(R.id.ll2_dv);
    List<String> errorList = new ArrayList<String>();
    getTargetObject(group, valueMap, errorList);
    if (CollectionUtils.isEmpty(errorList)) {
      DieselVisit dieselVisit = new DieselVisit();
      dieselVisit.setUserId(AppValuesHolder.getCurrentUser());
      saveVisit(dieselVisit, valueMap);
    } else {
      LOGGER.error("Validation failed");
    }
  }

  public void registerListenerForDieselTransferOrBulk() {
    final RadioGroup rg = (RadioGroup) findViewById(R.id.radioTransferBulk);
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(final RadioGroup group, final int checkedId) {
        // checkedId is the RadioButton selected
        if (R.id.radioBulk == checkedId) {

          Spinner spinnerDieselVendor = (Spinner) findViewById(R.id.etBulk);
          spinnerDieselVendor.setEnabled(true);
          spinnerDieselVendor.setFocusableInTouchMode(true);
          spinnerDieselVendor.requestFocus();
          AutoCompleteTextView autoTransfer = (AutoCompleteTextView) findViewById(R.id.etTransfer);
          autoTransfer.setEnabled(false);
          autoTransfer.setFocusableInTouchMode(false);
          autoTransfer.clearFocus();
          autoTransfer.setText("");

        }
        if (R.id.radioTransfer == checkedId) {
          AutoCompleteTextView autoTransfer = (AutoCompleteTextView) findViewById(R.id.etTransfer);
          autoTransfer.setEnabled(true);
          autoTransfer.setFocusableInTouchMode(true);
          autoTransfer.requestFocus();
          Spinner spinnerDieselVendor = (Spinner) findViewById(R.id.etBulk);
          spinnerDieselVendor.setEnabled(false);
          spinnerDieselVendor.setFocusableInTouchMode(false);
          spinnerDieselVendor.clearFocus();
          spinnerDieselVendor.setSelection(0);
        }

      }
    });
  }

  public void registerListenerForPhcn() {

    final RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioPhcnInstalled);
    rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(final RadioGroup group, final int checkedId) {
        // checkedId is the RadioButton selected
        if (R.id.radioPhcnInstalledYes == checkedId) {
          EditText edt5 = (EditText) findViewById(R.id.etPHCNHoursPerDay);
          edt5.setEnabled(true);
          edt5.setInputType(InputType.TYPE_CLASS_NUMBER);
          edt5.setFocusableInTouchMode(true);
          edt5.requestFocus();
        } else if (R.id.radioPhcnInstalledNo == checkedId) {

          EditText edt = (EditText) findViewById(R.id.etPHCNHoursPerDay);
          edt.setEnabled(false);
          edt.setInputType(InputType.TYPE_NULL);
          edt.setFocusableInTouchMode(false);
          edt.clearFocus();

        }
      }
    });
  }

  public void registerListenerForHybridPiu() {

    final RadioGroup rg = (RadioGroup) findViewById(R.id.radioHybridPiuInstalled);
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(final RadioGroup group, final int checkedId) {
        if (R.id.radioHybridPiuInstalledYes == checkedId) {
          EditText edt = (EditText) findViewById(R.id.etHybridPiuHoursPerDay);
          edt.setEnabled(true);
          edt.setInputType(InputType.TYPE_CLASS_NUMBER);
          edt.setFocusableInTouchMode(true);
          edt.requestFocus();
        } else if (R.id.radioHybridPiuInstalledNo == checkedId) {
          EditText edt = (EditText) findViewById(R.id.etHybridPiuHoursPerDay);
          edt.setEnabled(false);
          edt.setInputType(InputType.TYPE_NULL);
          edt.setFocusableInTouchMode(false);
          edt.clearFocus();
        }

      }
    });

  }

}
