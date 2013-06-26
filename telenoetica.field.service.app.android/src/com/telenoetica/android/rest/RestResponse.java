package com.telenoetica.android.rest;

public class RestResponse {

  private int statusCode;

  private String message;

  public RestResponse() {
    super();
    // TODO Auto-generated constructor stub
  }

  public RestResponse(final int statusCode, final String message) {
    super();
    this.statusCode = statusCode;
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(final int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "RestResponse [statusCode=" + statusCode + ", message=" + message + "]";
  }

}
