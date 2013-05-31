package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.Spare;
import com.telenoetica.jpa.repositories.SpareDAO;
import com.telenoetica.service.SpareService;

@Service("spareService")
public class SpareServiceImpl implements SpareService {
 
  @Autowired
  private SpareDAO spareDAO;

  @Override
  public Spare retrieve(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Spare saveOrUpdate(Spare baseEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Spare baseEntity) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Spare> getSpares() {
    return spareDAO.findAll();
  }

}
