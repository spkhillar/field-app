package com.telenoetica.web.rest;

import com.telenoetica.jpa.entities.Client;
import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.entities.MaintenanceVisitCategory;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.Spare;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(JsonMethod.NONE)
public class HomeDataObject {

  @JsonProperty
  private List<Site> sites = new ArrayList<Site>(1150);

  @JsonProperty
  private List<Spare> spares = new ArrayList<Spare>(34);

  @JsonProperty
  private List<Client> clients = new ArrayList<Client>(50);

  @JsonProperty
  private List<Fault> faults = new ArrayList<Fault>(10);

  @JsonProperty
  List<MaintenanceVisitCategory> maintenanceCategories = new ArrayList<MaintenanceVisitCategory>(10);
  
  public HomeDataObject() {}

  public HomeDataObject(List<Site> sites, List<Spare> spares, List<Client> clients, List<Fault> faults,List<MaintenanceVisitCategory> maintenanceCategories) {
    super();
    this.sites = sites;
    this.spares = spares;
    this.clients = clients;
    this.faults = faults;
    this.maintenanceCategories = maintenanceCategories;
  }

  public List<Site> getSites() {
    return sites;
  }

  public void setSites(List<Site> sites) {
    this.sites = sites;
  }

  public List<Spare> getSpares() {
    return spares;
  }

  public void setSpares(List<Spare> spares) {
    this.spares = spares;
  }

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }

  public List<Fault> getFaults() {
    return faults;
  }

  public void setFaults(List<Fault> faults) {
    this.faults = faults;
  }

  public List<MaintenanceVisitCategory> getMaintenanceCategories() {
    return maintenanceCategories;
  }

  public void setMaintenanceCategories(List<MaintenanceVisitCategory> maintenanceCategories) {
    this.maintenanceCategories = maintenanceCategories;
  }
  
  

}
