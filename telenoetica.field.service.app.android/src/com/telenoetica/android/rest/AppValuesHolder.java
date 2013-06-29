package com.telenoetica.android.rest;

import java.util.List;

public class AppValuesHolder {

  private static List<String> sites;

  private static List<String> spares;

  private static List<String> faults;

  private static List<String> clients;

  private static List<String> maintenanceCategories;

  public static List<String> getSites() {
    return sites;
  }

  public static void setSites(final List<String> sites) {
    AppValuesHolder.sites = sites;
  }

  public static List<String> getSpares() {
    return spares;
  }

  public static void setSpares(final List<String> spares) {
    AppValuesHolder.spares = spares;
  }

  public static List<String> getFaults() {
    return faults;
  }

  public static void setFaults(final List<String> faults) {
    AppValuesHolder.faults = faults;
  }

  public static List<String> getClients() {
    return clients;
  }

  public static void setClients(final List<String> clients) {
    AppValuesHolder.clients = clients;
  }

  public static List<String> getMaintenanceCategories() {
    return maintenanceCategories;
  }

  public static void setMaintenanceCategories(final List<String> maintenanceCategories) {
    AppValuesHolder.maintenanceCategories = maintenanceCategories;
  }



}
