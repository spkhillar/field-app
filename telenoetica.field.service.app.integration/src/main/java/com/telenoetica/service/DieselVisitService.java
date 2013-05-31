package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.DieselVisit;

public interface DieselVisitService extends BaseService<DieselVisit> {

  List<DieselVisit> getVisits();
}
