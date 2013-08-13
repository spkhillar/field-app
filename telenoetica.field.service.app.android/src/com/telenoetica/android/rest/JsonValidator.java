package com.telenoetica.android.rest;

import java.text.MessageFormat;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(JsonMethod.NONE)
public class JsonValidator {

  @JsonProperty
  private boolean required;

  @JsonProperty
  private boolean siteIdCheck;

  @JsonProperty
  private int min;

  @JsonProperty
  private int max;

  @JsonProperty
  private String message;

  public boolean isRequired() {
    return required;
  }

  public void setRequired(final boolean required) {
    this.required = required;
  }

  public int getMin() {
    return min;
  }

  public void setMin(final int min) {
    this.min = min;
  }

  public boolean isSiteIdCheck() {
    return siteIdCheck;
  }

  public void setSiteIdCheck(final boolean siteIdCheck) {
    this.siteIdCheck = siteIdCheck;
  }

  public int getMax() {
    return max;
  }

  public void setMax(final int max) {
    this.max = max;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public boolean validate(final String value) {
    boolean valid = true;
    if (required) {
      valid = StringUtils.isNotBlank(value);
      if (!valid) {
        message = AndroidConstants.REQUIRED_MESSAGE;
      }
    }
    if (valid && siteIdCheck) {
      valid = AppValuesHolder.getSites().contains(value);
      if (!valid) {
        message = "Site ID does not exist in the system";
      }
      return valid;
    }

    if (valid && min >= 0 && max > 0) {
      if(StringUtils.isNotBlank(value)){
        int intValue = Integer.parseInt(value);
        valid = (intValue >= min && intValue <= max);
        if (!valid) {
          message = getStringInFormat(AndroidConstants.RANGE_MESSAGE, new Object[] { value, min, max });
        }
      }
    }
    return valid;
  }

  private String getStringInFormat(final String formatString, final Object arguments[]) {
    if (ArrayUtils.isEmpty(arguments)) {
      return formatString;
    }
    return MessageFormat.format(formatString, arguments);
  }

}
