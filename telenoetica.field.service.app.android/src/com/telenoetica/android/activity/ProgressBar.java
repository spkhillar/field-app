package com.telenoetica.android.activity;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressBar {

  protected static final String TAG = AbstractAsyncActivity.class.getSimpleName();

  private ProgressDialog progressDialog;

  private boolean destroyed = false;

  // ***************************************
  // Activity methods
  // ***************************************
  @Override
  protected void onDestroy() {
    super.onDestroy();
    destroyed = true;
  }

  // ***************************************
  // Public methods
  // ***************************************
  public void showLoadingProgressDialog() {
    showProgressDialog("Loading. Please wait...");
  }

  public void showProgressDialog(final CharSequence message) {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(this);
      progressDialog.setIndeterminate(true);
    }

    progressDialog.setMessage(message);
    progressDialog.show();
  }

  public void dismissProgressDialog() {
    if (progressDialog != null && !destroyed) {
      progressDialog.dismiss();
    }
  }

}
