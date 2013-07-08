package com.telenoetica.android.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.telenoetica.util.model.CustomObjectMapper;

public abstract class RestJsonUtils {

  public static <T> T fromJSONString(final String jsonString, final Class<T> objectType) throws JsonParseException,
  JsonMappingException, IOException {
    CustomObjectMapper om = new CustomObjectMapper();
    T valueObject = om.readValue(jsonString, objectType);
    return valueObject;
  }

  public static String toJSONString(final Object object) throws JsonGenerationException, JsonMappingException, IOException {
    StringWriter sw = new StringWriter();
    CustomObjectMapper om = new CustomObjectMapper();
    PrintWriter pw = new PrintWriter(sw);
    om.writeValue(pw, object);
    return sw.toString();
  }
}
