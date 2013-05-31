package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.repositories.FaultDAO;
import com.telenoetica.service.FaultService;

@Service("faultService")
public class FaultServiceImpl implements FaultService {

  @Autowired
  private FaultDAO faultDAO;
  
  @Override
  public Fault retrieve(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Fault saveOrUpdate(Fault baseEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Fault baseEntity) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Fault> getFaults() {
    // TODO Auto-generated method stub
    return faultDAO.findAll();
  }

}
