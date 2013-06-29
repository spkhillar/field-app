package com.telenoetica.android.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppValuesHolder {

  private static final String[] DEFAULT_ITEM = new String[] { "Browse & Select" };
  private static List<String> sites = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));

  private static List<String> spares = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  private static List<String> faults = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  private static List<String> clients = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  private static List<String> maintenanceCategories = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  public static List<String> getSites() {
    return sites;
  }

  public static void setSites(final List<String> sites) {
    AppValuesHolder.sites.addAll(sites);
  }

  public static List<String> getSpares() {
    return spares;
  }

  public static void setSpares(final List<String> spares) {
    AppValuesHolder.spares.addAll(spares);
  }

  public static List<String> getFaults() {
    return faults;
  }

  public static void setFaults(final List<String> faults) {
    AppValuesHolder.faults.addAll(faults);
  }

  public static List<String> getClients() {
    return clients;
  }

  public static void setClients(final List<String> clients) {
    AppValuesHolder.clients.addAll(clients);
  }

  public static List<String> getMaintenanceCategories() {
    return maintenanceCategories;
  }

  public static void setMaintenanceCategories(final List<String> maintenanceCategories) {
    AppValuesHolder.maintenanceCategories.addAll(maintenanceCategories);
  }

}
