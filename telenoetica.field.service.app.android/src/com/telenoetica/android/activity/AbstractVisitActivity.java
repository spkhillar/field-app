package com.telenoetica.android.activity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.telenoetica.android.rest.ActivityRestResponse;
import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.rest.JsonValidator;
import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestJsonUtils;
import com.telenoetica.android.rest.RestResponse;

public abstract class AbstractVisitActivity extends ApplicationBaseActivity {

  protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractVisitActivity.class);

  protected void setupAutoCompleteSite() {
    AutoCompleteTextView siteAutoCompleteView = (AutoCompleteTextView) findViewById(R.id.visitSiteId);
    AutoCompleteTextView dieselTransferAutoCompleteView = (AutoCompleteTextView) findViewById(R.id.etTransfer);
    ArrayAdapter<String> arrayAdapter =
        new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, AppValuesHolder.getSites());
    siteAutoCompleteView.setAdapter(arrayAdapter);
    if (dieselTransferAutoCompleteView != null) {
      dieselTransferAutoCompleteView.setAdapter(arrayAdapter);
    }
  }

  public void addItemsOnSpinner(final int spinnerId, final List<String> spinnerValues) {
    Spinner spinner;
    spinner = (Spinner) findViewById(spinnerId);
    ArrayAdapter<String> spinnerAdapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerValues);
    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(spinnerAdapter);
  }

  public void getTargetObject(final ViewGroup group, final Map<String, Object> valueMap, final List<String> errorList) {
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
      } else if (view instanceof TextView) {
        value = ((TextView) view).getText();
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
    // try sending visit information to the server.
    Object serverRequestParams[] = new Object[] { bean, clazz };
    SendToServerAsyncTask sendToServerAsyncTask = new SendToServerAsyncTask();
    sendToServerAsyncTask.execute(serverRequestParams);

  }

  private void showToast(final long insertedDB) {
    if (insertedDB != -1) {
      Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, "Sending failed. Try sending after sometime.", Toast.LENGTH_SHORT).show();
    }
    Intent intent = new Intent(this, MainMenu.class);
    startActivity(intent);

  }

  @SuppressWarnings("rawtypes")
  public Object getTypedValue(final Class clazz, final String value) {
    if (ClassUtils.isAssignable(clazz, Long.class)) {
      return Long.parseLong(value);
    } else if (ClassUtils.isAssignable(clazz, Boolean.class)) {
      if ("Yes".equals(value)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    } else if (ClassUtils.isAssignable(clazz, Integer.class)) {
      return Integer.parseInt(value);
    } else if (ClassUtils.isAssignable(clazz, Date.class)) {
      return null;
    } else if (ClassUtils.isAssignable(clazz, String.class)) {
      return value;
    } else if (ClassUtils.isAssignable(clazz, Float.class)) {
      return Float.valueOf(value);
    }
    throw new IllegalArgumentException(clazz.getName() + "Not in List");
  }

  public void renderConfirmationDialog() {

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
    alertDialog.setTitle("Save");
    alertDialog.setMessage("Are you sure?");

    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(final DialogInterface dialog, final int which) {
        saveCurrentActivity();
      }
    });

    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(final DialogInterface dialog, final int which) {
        // Write your code here to invoke NO event
        dialog.cancel();
      }
    });
    alertDialog.show();

  }

  private class SendToServerAsyncTask extends AsyncTask<Object, Void, ActivityRestResponse> {
    private ProgressDialog pd;

    @Override
    protected void onPreExecute() {
      pd = new ProgressDialog(context);
      pd.setTitle("Processing...");
      pd.setMessage("Please wait.");
      pd.setCancelable(false);
      pd.setIndeterminate(true);
      pd.show();
    }

    @Override
    protected ActivityRestResponse doInBackground(final Object... params) {
      ActivityRestResponse activityRestResponse = new ActivityRestResponse();
      RestResponse response = null;
      Class<?> currentClazz = null;
      Object postObject = null;
      Date start = new Date();
      try {
        currentClazz = (Class<?>) params[1];
        String url = AppValuesHolder.getHost() + determinePath(currentClazz);
        LOGGER.debug("Sending to server....invoking..." + url);
        postObject = params[0];
        response =
            RestClient.INSTANCE.executeRest(url, AppValuesHolder.getCurrentUser(),
              AppValuesHolder.getCurrentUserPassword(), HttpMethod.POST, postObject, RestResponse.class,
              MediaType.APPLICATION_JSON);
      } catch (Exception e) {
        LOGGER.error("Exception send to server...", e);
        if (e.getCause() instanceof HttpClientErrorException) {
          HttpStatus status = ((HttpClientErrorException) e.getCause()).getStatusCode();
          if (HttpStatus.UNAUTHORIZED.equals(status)) {
            response = new RestResponse(401, "Invalid Credentials. Check username and password");
          } else if (HttpStatus.FORBIDDEN.equals(status)) {
            response = new RestResponse(403, "Invalid Credentials. Check username and password");
          }
        } else {
          response = new RestResponse(500, "Error while sending information to server...");
        }
      }

      Date end = new Date();
      long total = end.getTime() - start.getTime();
      LOGGER.debug("...Total Time save and send..." + total);
      activityRestResponse.setStatusCode(response.getStatusCode());
      activityRestResponse.setMessage(response.getMessage());
      if (response.getStatusCode() != 0) {
        activityRestResponse.setVisitObject(postObject);
        activityRestResponse.setVisitClassType(currentClazz);
      }
      return activityRestResponse;

    }

    @Override
    protected void onPostExecute(final ActivityRestResponse restResponse) {
      pd.dismiss();
      doWithSendToServerResponse(restResponse);
    }
  }

  public void doWithSendToServerResponse(final ActivityRestResponse restResponse) {
    if (restResponse.getStatusCode() == 0) {
      showToast(1);
    } else {
      try {
        String jsonString = RestJsonUtils.toJSONString(restResponse.getVisitObject());
        sqLiteDbHandler.insertVisit(jsonString, restResponse.getVisitClassType().getCanonicalName());
      } catch (JsonGenerationException e) {
        LOGGER.error("Exception while saving", e);
      } catch (JsonMappingException e) {
        LOGGER.error("Exception while saving", e);
      } catch (IOException e) {
        LOGGER.error("Exception while saving", e);
      }
      showToast(-1);
    }
  }

  public abstract void saveCurrentActivity();

}
