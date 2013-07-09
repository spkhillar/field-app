package com.telenoetica.scheduler.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.service.DieselDetailReportService;
import com.telenoetica.service.JobHistoryService;

public class DieselDetailsReportJob extends QuartzJobBean {

  private static final Logger LOGGER = Logger
      .getLogger(DieselDetailsReportJob.class);

  private JobHistoryService jobHistoryService;

  private DieselDetailReportService dieselDetailReportService;

  public void setDieselDetailReportService(
      final DieselDetailReportService dieselDetailReportService) {
    this.dieselDetailReportService = dieselDetailReportService;
  }

  public void setJobHistoryService(final JobHistoryService jobHistoryService) {
    this.jobHistoryService = jobHistoryService;
  }

  @Override
  protected void executeInternal(final JobExecutionContext context)
      throws JobExecutionException {
    LOGGER.debug("Job Started");
    String reportPath = "";
    JobHistory jobHistory = new JobHistory("DieselDetailReportJob",
      "DieselDetailReport", new Date(), null, JobStatus.RUNNING);
    createJobStatus(jobHistory);
    // setup the
    // Do your work
    try {
      reportPath = dieselDetailReportService.createNewReport();
      jobHistory.setPath(reportPath);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.COMPLETED);
    } catch (Exception e) {
      LOGGER.error("Error while creating Report",e);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.FAILED);
    }
    updateJobStatus(jobHistory);

  }

  public void createJobStatus(final JobHistory jobHistory) {
    jobHistoryService.saveOrUpdate(jobHistory);
  }

  public void updateJobStatus(final JobHistory jobHistory) {
    jobHistoryService.saveOrUpdate(jobHistory);
  }
}