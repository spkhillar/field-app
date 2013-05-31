package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.repositories.DieselVisitDAO;
import com.telenoetica.service.DieselVisitService;
@Service("dieselVisitService")
public class DieselVisitServiceImpl implements DieselVisitService {

  @Autowired
  private DieselVisitDAO dieselVisitDAO;
  
  @Override
  public DieselVisit retrieve(Long id) {
    return dieselVisitDAO.findOne(id);
  }

  @Override
  public DieselVisit saveOrUpdate(DieselVisit baseEntity) {
    return dieselVisitDAO.save(baseEntity);
  }

  @Override
  public void delete(DieselVisit baseEntity) {
    dieselVisitDAO.delete(baseEntity);
  }

  @Override
  public List<DieselVisit> getVisits() {
    return dieselVisitDAO.findAll();
  }

}
