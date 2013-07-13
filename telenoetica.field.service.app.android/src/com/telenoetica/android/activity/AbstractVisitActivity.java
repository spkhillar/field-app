package com.telenoetica.android.activity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.telenoetica.android.rest.JsonValidator;
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

  public void getTargetObject(final ViewGroup group, final Map<String, Object> valueMap, final List<String> errorList) {
    LOGGER.debug("Mapping fields to object map started");
    Object value = null;
    String tagValue = null;
    Object tag = null;
    for (int i = 0, count = group.getChildCount(); i < count; ++i) {
      View view = group.getChildAt(i);
      tag = view.getTag();
      if (tag != null) {
        tagValue = tag.toString();
      }
      if (view instanceof EditText) {
        Editable editable = ((EditText) view).getText();
        value = editable.toString();
      } else if (view instanceof Spinner) {
        value = ((Spinner) view).getSelectedItem();
        if ("Browse & Select".equals(value)) {
          value = null;
        }
      } else if (view instanceof RadioGroup) {
        RadioGroup rg = (RadioGroup) view;
        int valuebutton = rg.getCheckedRadioButtonId();
        if (valuebutton != -1) {
          int id = rg.getCheckedRadioButtonId();
          View radioButton = rg.findViewById(id);
          int radioId = rg.indexOfChild(radioButton);
          RadioButton btn = (RadioButton) rg.getChildAt(radioId);
          value = btn.getText();
        }
      }
      if (tagValue != null && value != null) {
        addValuesInMap(view, valueMap, value, tagValue, errorList);
      }
      if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)) {
        getTargetObject((ViewGroup) view, valueMap, errorList);
      }
    }
    LOGGER.debug("Mapping fields to object map ends");
  }

  private void addValuesInMap(final View view, final Map<String, Object> valueMap, final Object value,
      final String tagValue, final List<String> errorList) {
    String tagAndValidation[] = StringUtils.split(tagValue, "=");
    boolean valid = false;
    if (tagAndValidation.length == 1) {
      valueMap.put(tagValue, value);
    } else {
      try {
        JsonValidator jsonValidator = RestJsonUtils.fromJSONString(tagAndValidation[1], JsonValidator.class);
        valid = jsonValidator.validate(value.toString());
        if (!valid) {
          if (view instanceof EditText) {
            EditText editable = (EditText) view;
            editable.setError(jsonValidator.getMessage());
          }
          errorList.add("Error");
        } else {
          valueMap.put(tagAndValidation[0], value);
        }
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @SuppressWarnings("rawtypes")
  public void saveVisit(final Object bean, final Map<String, Object> valueMap) {
    LOGGER.debug("saveVisit starts");
    if (bean != null) {
      Class clazz = bean.getClass();
      for (Map.Entry<String, Object> mapEntry : valueMap.entrySet()) {
        try {
          Field field = clazz.getDeclaredField(mapEntry.getKey());
          field.setAccessible(true);
          Class retType = field.getType();
          if (mapEntry.getValue() != null && StringUtils.isNotBlank(mapEntry.getValue().toString())) {
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
      saveJsonToDB(bean, clazz);
    }
    LOGGER.debug("saveVisit ends.." + bean);
  }

  private void saveJsonToDB(final Object bean, final Class<?> clazz) {
    String clazzName = clazz.getCanonicalName();
    LOGGER.debug("Saving To DB.." + clazzName);
    try {
      String jsonString = RestJsonUtils.toJSONString(bean);
      long insertedDB = sqLiteDbHandler.insertVisit(jsonString, clazzName);
      showToast(insertedDB);
    } catch (JsonGenerationException e) {
      LOGGER.error("JsonGenerationException...", e);
    } catch (JsonMappingException e) {
      LOGGER.error("JsonMappingException...", e);
    } catch (IOException e) {
      LOGGER.error("IOException...", e);
    }

  }

  private void showToast(final long insertedDB) {
    // TODO Auto-generated method stub
    if (insertedDB != -1) {
      Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "Save Unsuccessfull", Toast.LENGTH_SHORT).show();
    }
    Intent intent = new Intent(this, MainMenu.class);
    startActivity(intent);

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
    throw new IllegalArgumentException(clazz.getName() + "Not in List");
  }
}
