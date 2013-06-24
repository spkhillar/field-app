package com.telenoetica.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.RoutineVisit;
@JsonAutoDetect(JsonMethod.NONE)
public class WebHomeData {

  @JsonProperty
  private List<Integer> chartData = new ArrayList<Integer>();
  @JsonProperty
  private List<MaintenanceVisit> maintenanceVisits = new ArrayList<MaintenanceVisit>();
  @JsonProperty
  private List<CallOutVisit> callOutVisits = new ArrayList<CallOutVisit>();
  @JsonProperty
  private List<RoutineVisit> routineVisits = new ArrayList<RoutineVisit>();
  @JsonProperty
  private List<DieselVisit> dieselVisits = new ArrayList<DieselVisit>();



  public WebHomeData() {
    super();
  }

  public WebHomeData(final List<Integer> chartData, final List<MaintenanceVisit> maintenanceVisits,
      final List<CallOutVisit> callOutVisits, final List<RoutineVisit> routineVisits, final List<DieselVisit> dieselVisits) {
    super();
    this.chartData = chartData;
    this.maintenanceVisits = maintenanceVisits;
    this.callOutVisits = callOutVisits;
    this.routineVisits = routineVisits;
    this.dieselVisits = dieselVisits;
  }

  public List<Integer> getChartData() {
    return chartData;
  }

  public void setChartData(final List<Integer> chartData) {
    this.chartData = chartData;
  }

  public List<MaintenanceVisit> getMaintenanceVisits() {
    return maintenanceVisits;
  }

  public void setMaintenanceVisits(final List<MaintenanceVisit> maintenanceVisits) {
    this.maintenanceVisits = maintenanceVisits;
  }

  public List<CallOutVisit> getCallOutVisits() {
    return callOutVisits;
  }

  public void setCallOutVisits(final List<CallOutVisit> callOutVisits) {
    this.callOutVisits = callOutVisits;
  }

  public List<RoutineVisit> getRoutineVisits() {
    return routineVisits;
  }

  public void setRoutineVisits(final List<RoutineVisit> routineVisits) {
    this.routineVisits = routineVisits;
  }

  public List<DieselVisit> getDieselVisits() {
    return dieselVisits;
  }

  public void setDieselVisits(final List<DieselVisit> dieselVisits) {
    this.dieselVisits = dieselVisits;
  }



}
