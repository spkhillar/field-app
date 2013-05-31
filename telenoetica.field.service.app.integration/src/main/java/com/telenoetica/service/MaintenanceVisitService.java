package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.MaintenanceVisit;

public interface MaintenanceVisitService extends BaseService<MaintenanceVisit> {

  List<MaintenanceVisit> getVisits();
}
