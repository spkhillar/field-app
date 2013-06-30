package com.telenoetica.android.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.telenoetica.util.model.HomeAndroidObject;

public final class AppValuesPopulator {


  private static final Logger LOGGER = LoggerFactory.getLogger(AppValuesPopulator.class);

  private AppValuesPopulator() {

  }

  public static void populateValues(final String userName, final String password) throws JSONException {
    HomeAndroidObject homeAndroidObject =
        RestClient.INSTANCE.executeRest("http://192.168.1.103:8082/fieldapp/rest/home", userName, password, HttpMethod.GET, null,
          HomeAndroidObject.class, MediaType.APPLICATION_JSON);
    LOGGER.debug("...Home Object..."+homeAndroidObject);
    if(homeAndroidObject != null){
      AppValuesHolder.setClients(homeAndroidObject.getClients());
      AppValuesHolder.setFaults(homeAndroidObject.getFaults());
      AppValuesHolder.setMaintenanceCategories(homeAndroidObject.getMaintenanceCategories());
      AppValuesHolder.setSites(homeAndroidObject.getSites());
      AppValuesHolder.setSpares(homeAndroidObject.getSpares());
    }

  }
}
