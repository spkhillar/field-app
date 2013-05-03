package com.telenoetica.scheduler.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.service.CallOutVisitService;

public class CallOutVisitJob extends QuartzJobBean {
  
  private static final Logger LOGGER = Logger.getLogger(CallOutVisitJob.class);
  
  private CallOutVisitService callOutVisitService;

  public CallOutVisitService getCallOutVisitService() {
    return callOutVisitService;
  }

  public void setCallOutVisitService(CallOutVisitService callOutVisitService) {
    this.callOutVisitService = callOutVisitService;
  }

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    LOGGER.debug("CallOutVisitJob. started..");

  }

}
