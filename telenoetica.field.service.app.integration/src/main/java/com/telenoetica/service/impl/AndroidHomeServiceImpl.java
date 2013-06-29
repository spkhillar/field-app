package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.service.AndroidHomeService;
import com.telenoetica.util.model.HomeAndroidObject;

@Service("androidHomeService")
public class AndroidHomeServiceImpl implements AndroidHomeService {

  @Autowired
  private GenericQueryExecutorDAO genericQueryExecutorDAO;

  @Override
  @Transactional(readOnly=true)
  public HomeAndroidObject getAndroidHomeObject() {
    List<String> sites = genericQueryExecutorDAO.executeQuery("select name from Site", String.class);
    List<String> faults = genericQueryExecutorDAO.executeQuery("select name from Fault", String.class);
    List<String> clients = genericQueryExecutorDAO.executeQuery("select name from Client", String.class);
    List<String> maintenanceCategories = genericQueryExecutorDAO.executeQuery("select name from MaintenanceVisitCategory", String.class);
    List<String> spares = genericQueryExecutorDAO.executeQuery("select name from Spare", String.class);
    HomeAndroidObject homeAndroidObject = new HomeAndroidObject(sites, spares, clients, faults, maintenanceCategories);
    return homeAndroidObject;
  }

}
