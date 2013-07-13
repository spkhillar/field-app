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

  private static List<String> maintenanceCategories = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));

  private static String currentUser;

  private static String currentUserPassword;

  private static String host="http://ec2-54-234-57-223.compute-1.amazonaws.com:8080/fieldapp";

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

  public static String getCurrentUser() {
    return currentUser;
  }

  public static void setCurrentUser(final String currentUser) {
    AppValuesHolder.currentUser = currentUser;
  }

  public static String getCurrentUserPassword() {
    return currentUserPassword;
  }

  public static void setCurrentUserPassword(final String currentUserPassword) {
    AppValuesHolder.currentUserPassword = currentUserPassword;
  }

  public static String getHost() {
    return host;
  }

  public static void setHost(final String host) {
    AppValuesHolder.host = host;
  }

  public static void resetConfigData(){
    AppValuesHolder.clients.clear();
    AppValuesHolder.clients.add(DEFAULT_ITEM[0]);
    AppValuesHolder.faults.clear();
    AppValuesHolder.faults.add(DEFAULT_ITEM[0]);
    AppValuesHolder.maintenanceCategories.clear();
    AppValuesHolder.maintenanceCategories.add(DEFAULT_ITEM[0]);
    AppValuesHolder.sites.clear();
    AppValuesHolder.sites.add(DEFAULT_ITEM[0]);
    AppValuesHolder.spares.clear();
    AppValuesHolder.spares.add(DEFAULT_ITEM[0]);
  }

}
