package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.repositories.CallOutVisitDAO;
import com.telenoetica.service.CallOutVisitService;

@Service("callOutVisitService")
public class CallOutVisitServiceImpl implements CallOutVisitService {

  @Autowired
  private CallOutVisitDAO callOutVisitDAO;
  
  private static final int PAGE_SIZE = 50;

  @Transactional
  public CallOutVisit retrieve(Long id) {
    return this.callOutVisitDAO.findOne(id);
  }

  @Transactional
  public CallOutVisit saveOrUpdate(CallOutVisit callOutVisit) {
    return this.callOutVisitDAO.save(callOutVisit);
  }

  @Transactional
  public void delete(CallOutVisit callOutVisit) {
    this.callOutVisitDAO.delete(callOutVisit);
  }
  
  @Transactional
  public Page<CallOutVisit> getCallOutVisits(Integer pageNumber) {
      PageRequest request =
          new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
      return callOutVisitDAO.findAll(request);
  }

}
