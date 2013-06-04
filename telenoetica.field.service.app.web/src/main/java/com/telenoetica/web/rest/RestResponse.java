package com.telenoetica.web.rest;

import java.io.Serializable;

public class RestResponse implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -1058263063993594276L;

  private int statusCode;
  
  private String message;
  
  

  public RestResponse() {
    super();
    // TODO Auto-generated constructor stub
  }

  public RestResponse(int statusCode, String message) {
    super();
    this.statusCode = statusCode;
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  
  

}
