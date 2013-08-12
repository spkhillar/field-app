package com.telenoetica.android.rest;

public class ActivityRestResponse extends RestResponse {

  private Object visitObject;

  private Class<?> visitClassType;

  /**
   * @return the visitObject
   */
  public Object getVisitObject() {
    return visitObject;
  }

  /**
   * @param visitObject the visitObject to set
   */
  public void setVisitObject(final Object visitObject) {
    this.visitObject = visitObject;
  }

  /**
   * @return the visitClassType
   */
  public Class<?> getVisitClassType() {
    return visitClassType;
  }

  /**
   * @param visitClassType the visitClassType to set
   */
  public void setVisitClassType(final Class<?> visitClassType) {
    this.visitClassType = visitClassType;
  }

}
