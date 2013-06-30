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
    StringBuilder builder = new StringBuilder();
    builder.append("RestResponse [statusCode=");
    builder.append(statusCode);
    builder.append(", ");
    if (message != null) {
      builder.append("message=");
      builder.append(message);
    }
    builder.append("]");
    return builder.toString();
  }



}
