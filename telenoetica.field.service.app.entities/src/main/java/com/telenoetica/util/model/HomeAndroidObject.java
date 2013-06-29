package com.telenoetica.util.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(JsonMethod.NONE)
public class HomeAndroidObject {

  /** The sites. */
  @JsonProperty
  private List<String> sites = new ArrayList<String>(1150);

  /** The spares. */
  @JsonProperty
  private List<String> spares = new ArrayList<String>(34);

  /** The clients. */
  @JsonProperty
  private List<String> clients = new ArrayList<String>(50);

  /** The faults. */
  @JsonProperty
  private List<String> faults = new ArrayList<String>(10);

  /** The maintenance categories. */
  @JsonProperty
  List<String> maintenanceCategories = new ArrayList<String>(10);



  public HomeAndroidObject() {
    super();
  }

  public HomeAndroidObject(final List<String> sites, final List<String> spares, final List<String> clients, final List<String> faults,
      final List<String> maintenanceCategories) {
    super();
    this.sites = sites;
    this.spares = spares;
    this.clients = clients;
    this.faults = faults;
    this.maintenanceCategories = maintenanceCategories;
  }



  public List<String> getSites() {
    return sites;
  }

  public void setSites(final List<String> sites) {
    this.sites = sites;
  }

  public List<String> getSpares() {
    return spares;
  }

  public void setSpares(final List<String> spares) {
    this.spares = spares;
  }

  public List<String> getClients() {
    return clients;
  }

  public void setClients(final List<String> clients) {
    this.clients = clients;
  }

  public List<String> getFaults() {
    return faults;
  }

  public void setFaults(final List<String> faults) {
    this.faults = faults;
  }

  public List<String> getMaintenanceCategories() {
    return maintenanceCategories;
  }

  public void setMaintenanceCategories(final List<String> maintenanceCategories) {
    this.maintenanceCategories = maintenanceCategories;
  }

  @Override
  public String toString() {
    return "HomeAndroidObject [sites=" + sites.size() + ", spares=" + spares.size() + ", clients=" + clients.size() + ", faults=" + faults.size()
        + ", maintenanceCategories=" + maintenanceCategories.size() + "]";
  }





}
