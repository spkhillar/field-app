package com.telenoetica.android.activity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.telenoetica.android.rest.RestJsonUtils;
import com.telenoetica.android.sqllite.SQLiteDbHandler;

public class AbstractVisitActivity extends Activity {

  protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractVisitActivity.class);

  protected SQLiteDbHandler sqLiteDbHandler;

  public void addItemsOnSpinner(final int spinnerId, final List<String> spinnerValues) {
    Spinner spinner;
    spinner = (Spinner) findViewById(spinnerId);
    ArrayAdapter<String> spinnerAdapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerValues);
    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(spinnerAdapter);
  }

  public void getTargetObject(final ViewGroup group, final Map<String, Object> valueMap){
    LOGGER.debug("Mapping fields to object map started");
    Object value=null;
    String tagValue = null;
    Object tag = null;
    for (int i = 0, count = group.getChildCount(); i < count; ++i) {
      View view = group.getChildAt(i);
      tag = view.getTag();
      if(tag != null){
        tagValue= tag.toString();
      }
      if (view instanceof EditText) {
        Editable editable = ((EditText) view).getText();
        value = editable.toString();
      } else if (view instanceof Spinner) {
        value = ((Spinner) view).getSelectedItem();
        if("Browse & Select".equals(value)){
          value = null;
        }
      } else if (view instanceof RadioGroup) {
        RadioGroup rg = (RadioGroup) view;
        int valuebutton = rg.getCheckedRadioButtonId();
        if(valuebutton != -1){
          int id= rg.getCheckedRadioButtonId();
          View radioButton = rg.findViewById(id);
          int radioId = rg.indexOfChild(radioButton);
          RadioButton btn = (RadioButton) rg.getChildAt(radioId);
          value = btn.getText();
        }
      }
      if(tagValue != null){
        valueMap.put(tagValue, value);
      }
      if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)) {
        getTargetObject((ViewGroup) view,valueMap);
      }
    }
    LOGGER.debug("Mapping fields to object map ends");
  }

  @SuppressWarnings("rawtypes")
  public void saveVisit(final Object bean,final Map<String, Object> valueMap){
    LOGGER.debug("saveVisit starts");
    if(bean != null){
      Class clazz = bean.getClass();
      for (Map.Entry<String, Object> mapEntry : valueMap.entrySet()) {
        try {
          Field field = clazz.getDeclaredField(mapEntry.getKey());
          field.setAccessible(true);
          Class retType = field.getType();
          if(mapEntry.getValue() != null && StringUtils.isNotBlank(mapEntry.getValue().toString())){
            Object value = getTypedValue(retType, mapEntry.getValue().toString());
            field.set(bean, value);
          }
        } catch (NoSuchFieldException e) {
          LOGGER.error("NoSuchFieldException...", e);
        } catch (IllegalArgumentException e) {
          LOGGER.error("IllegalArgumentException...", e);
        } catch (IllegalAccessException e) {
          LOGGER.error("IllegalArgumentException...", e);
        }
      }
      saveJsonToDB(bean,clazz);
    }
    LOGGER.debug("saveVisit ends.."+bean);
  }

  private void saveJsonToDB(final Object bean, final Class clazz) {
    String clazzName = clazz.getCanonicalName();
    LOGGER.debug("Saving To DB.."+clazzName);
    try {
      String jsonString =  RestJsonUtils.toJSONString(bean);
      sqLiteDbHandler.insertVisit(jsonString, clazzName);
    } catch (JsonGenerationException e) {
      LOGGER.error("JsonGenerationException...", e);
    } catch (JsonMappingException e) {
      LOGGER.error("JsonMappingException...", e);
    } catch (IOException e) {
      LOGGER.error("IOException...", e);
    }


  }

  @SuppressWarnings("rawtypes")
  public Object getTypedValue(final Class clazz, final String value) {
    if (ClassUtils.isAssignable(clazz, Long.class)) {
      return Long.parseLong(value);
    } else if (ClassUtils.isAssignable(clazz, Boolean.class)) {
      return Boolean.valueOf(value);
    } else if (ClassUtils.isAssignable(clazz, Integer.class)) {
      return Integer.parseInt(value);
    } else if (ClassUtils.isAssignable(clazz, Date.class)) {
      return null;
    } else if (ClassUtils.isAssignable(clazz, String.class)) {
      return value;
    }
    throw new IllegalArgumentException(clazz.getName()+"Not in List");
  }
}
