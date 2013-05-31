package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.repositories.SiteDAO;
import com.telenoetica.service.SiteService;

@Service("siteService")
@Transactional
public class SiteServiceImpl implements SiteService {

  @Autowired
  private SiteDAO siteDAO;
  
  
  public Site retrieve(Long id) {
    return siteDAO.findOne(id);
  }

  public Site saveOrUpdate(Site baseEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  public void delete(Site baseEntity) {
    // TODO Auto-generated method stub

  }

  public Page<Site> getSites(Integer pageNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Site> getSites() {
    // TODO Auto-generated method stub
    return siteDAO.findAll();
  }

  @Override
  public Site findSite(String name) {
    return siteDAO.findByName(name);
  }

}
