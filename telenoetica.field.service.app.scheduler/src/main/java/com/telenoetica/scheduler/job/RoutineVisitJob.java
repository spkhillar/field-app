package com.telenoetica.scheduler.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.RoutineVisitService;

public class RoutineVisitJob extends QuartzJobBean {
  
  private static final Logger LOGGER = Logger.getLogger(RoutineVisitJob.class);

  private RoutineVisitService routineVisitService;
  
  public JobHistoryService jobHistoryService;
  
  public JobHistoryService getJobHistoryService() {
    return jobHistoryService;
  }

  public void setJobHistoryService(JobHistoryService jobHistoryService) {
    this.jobHistoryService = jobHistoryService;
  }
  
  public RoutineVisitService getRoutineVisitService() {
    return routineVisitService;
  }

  public void setRoutineVisitService(RoutineVisitService routineVisitService) {
    this.routineVisitService = routineVisitService;
  }

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    LOGGER.debug("Job Started");
    JobHistory jobHistory = new JobHistory("RoutineVisitJob","RoutineVisitJob",new Date(),null,JobStatus.RUNNING);
    this.createJobStatus(jobHistory);
    //Do your work
    jobHistory.setEndTime(new Date());
    jobHistory.setJobStatus(JobStatus.COMPLETED);
    this.updateJobStatus(jobHistory);
  }

  public void createJobStatus(JobHistory jobHistory){
    jobHistoryService.saveOrUpdate(jobHistory);
  }
 
  public void updateJobStatus(JobHistory jobHistory){
    jobHistoryService.saveOrUpdate(jobHistory);
  }
  

 

}
