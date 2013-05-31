package com.telenoetica.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class ServiceUtil {

  private ServiceUtil(){
    
  }
  
  public static String checkAndReturnValue(String value){
    if(StringUtils.isBlank(value)){
      return "";
    }
    return value;
  }
  
  public static String checkAndReturnValue(Date date){
    if(date == null){
      return "";
    }
    return getDateInFormat(date, "MM/dd/yyyy HH:mm:ss");
  }
  
  public static String getDateInFormat(Date date, String format){
    
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
    
  }

  public static String checkAndReturnValue(Boolean enabled) {
    // TODO Auto-generated method stub
    return enabled.toString();
  }
  
}
