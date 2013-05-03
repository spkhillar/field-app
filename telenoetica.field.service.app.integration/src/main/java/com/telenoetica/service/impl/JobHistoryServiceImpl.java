package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.repositories.JobHistoryDAO;
import com.telenoetica.service.JobHistoryService;

@Service("jobHistoryService")
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

  @Autowired
  private JobHistoryDAO jobHistoryDAO;
  
  @Override
  public JobHistory retrieve(Long id) {
    return jobHistoryDAO.findOne(id);
  }

  @Override
  public JobHistory saveOrUpdate(JobHistory jobHistory) {
    return jobHistoryDAO.saveAndFlush(jobHistory);
  }

  @Override
  public void delete(JobHistory jobHistory) {
    jobHistoryDAO.delete(jobHistory);
  }

}
