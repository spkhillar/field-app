package com.telenoetica.jpa.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.jpa.repositories.JobHistoryDAO;

public class JobHistoryDAOTest extends BaseTest{

  @Autowired
  private JobHistoryDAO jobHistoryDAO;
  
  
  @Test
  public void testJobHistory(){
    
    JobHistory jobHistory = new JobHistory("TestJob", "JOB Description", new Date(), new Date(), JobStatus.RUNNING);
    
    JobHistory jobHistory1 = jobHistoryDAO.save(jobHistory);
    
    JobHistory jobHistory2 = jobHistoryDAO.findOne(jobHistory1.getId());
    
    System.err.println("..jobHistory2..."+jobHistory2);
    
    jobHistory2.setJobStatus(JobStatus.COMPLETED);
    
    jobHistory1 = jobHistoryDAO.saveAndFlush(jobHistory2);
    System.err.println("..jobHistory1 after update..."+jobHistory2);
    
  }
}
