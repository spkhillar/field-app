package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.repositories.MaintenanceVisitDAO;
import com.telenoetica.service.MaintenanceVisitService;

@Service("maintenanceVisitService")
public class MaintenanceVisitServiceImpl implements MaintenanceVisitService {

  @Autowired
  private MaintenanceVisitDAO maintenanceVisitDAO;
  
  
  @Override
  public MaintenanceVisit retrieve(Long id) {
    // TODO Auto-generated method stub
    return maintenanceVisitDAO.findOne(id);
  }

  @Override
  public MaintenanceVisit saveOrUpdate(MaintenanceVisit baseEntity) {
    // TODO Auto-generated method stub
    return maintenanceVisitDAO.save(baseEntity);
  }

  @Override
  public void delete(MaintenanceVisit baseEntity) {
    maintenanceVisitDAO.delete(baseEntity);
  }

  @Override
  public List<MaintenanceVisit> getVisits() {
    return maintenanceVisitDAO.findAll();
  }

}
