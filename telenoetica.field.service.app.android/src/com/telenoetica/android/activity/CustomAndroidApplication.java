package com.telenoetica.android.activity;

import android.app.Application;

public class CustomAndroidApplication extends Application {
  private String httpUrl;

  @Override
  public void onCreate() {
    // TODO Auto-generated method stub
    super.onCreate();
  }

  public String getHttpUrl() {
    return httpUrl;
  }

  public void setHttpUrl(final String httpUrl) {
    this.httpUrl = httpUrl;
  }

}
