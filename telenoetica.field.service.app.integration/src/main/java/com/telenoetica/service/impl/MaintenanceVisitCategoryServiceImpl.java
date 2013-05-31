package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.MaintenanceVisitCategory;
import com.telenoetica.jpa.repositories.MaintenanceVisitCategoryDAO;
import com.telenoetica.service.MaintenanceVisitCategoryService;

@Service("maintenanceVisitCategoryService")
public class MaintenanceVisitCategoryServiceImpl implements MaintenanceVisitCategoryService {

  @Autowired
  private MaintenanceVisitCategoryDAO maintenanceVisitCategoryDAO;
  
  @Override
  public MaintenanceVisitCategory retrieve(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MaintenanceVisitCategory saveOrUpdate(MaintenanceVisitCategory baseEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(MaintenanceVisitCategory baseEntity) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<MaintenanceVisitCategory> getCategories() {
    return maintenanceVisitCategoryDAO.findAll();
  }

}
