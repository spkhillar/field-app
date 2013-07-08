package com.telenoetica.android.sqllite;

public class AndroidVisitSqLiteModel {

  private Long id;

  private String json;

  private String status;

  private String clazzName;

  private Integer tries;



  public AndroidVisitSqLiteModel() {
    super();
  }

  public AndroidVisitSqLiteModel(final Long id,final String json, final String status, final String clazzName, final Integer tries) {
    super();
    this.id=id;
    this.json = json;
    this.status = status;
    this.clazzName = clazzName;
    this.tries = tries;
  }



  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getJson() {
    return json;
  }

  public void setJson(final String json) {
    this.json = json;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }

  public String getClazzName() {
    return clazzName;
  }

  public void setClazzName(final String clazzName) {
    this.clazzName = clazzName;
  }

  public Integer getTries() {
    return tries;
  }

  public void setTries(final Integer tries) {
    this.tries = tries;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AndroidVisitSqLiteModel [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
    if (json != null) {
      builder.append("json=");
      builder.append(json);
      builder.append(", ");
    }
    if (status != null) {
      builder.append("status=");
      builder.append(status);
      builder.append(", ");
    }
    if (clazzName != null) {
      builder.append("clazzName=");
      builder.append(clazzName);
      builder.append(", ");
    }
    if (tries != null) {
      builder.append("tries=");
      builder.append(tries);
    }
    builder.append("]");
    return builder.toString();
  }
}
