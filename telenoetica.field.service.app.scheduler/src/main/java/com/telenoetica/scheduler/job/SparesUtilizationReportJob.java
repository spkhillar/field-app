/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
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

/**
 * The Class SparesUtilizationReportJob.
 */
public class SparesUtilizationReportJob extends QuartzJobBean {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = Logger
      .getLogger(SparesUtilizationReportJob.class);

  /** The job history service. */
  private JobHistoryService jobHistoryService;

  /** The diesel detail report service. */
  private DieselDetailReportService dieselDetailReportService;

  /**
   * Sets the diesel detail report service.
   *
   * @param dieselDetailReportService the new diesel detail report service
   */
  public void setDieselDetailReportService(
      final DieselDetailReportService dieselDetailReportService) {
    this.dieselDetailReportService = dieselDetailReportService;
  }

  /**
   * Sets the job history service.
   *
   * @param jobHistoryService the new job history service
   */
  public void setJobHistoryService(final JobHistoryService jobHistoryService) {
    this.jobHistoryService = jobHistoryService;
  }

  /**
   * Execute internal.
   *
   * @param context the context
   * @throws JobExecutionException the job execution exception
   * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
   */
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
    } catch (Exception e) {
      LOGGER.debug("Error while creating Report");
      e.printStackTrace();
    }
    jobHistory.setPath(reportPath);
    jobHistory.setEndTime(new Date());
    jobHistory.setJobStatus(JobStatus.COMPLETED);
    updateJobStatus(jobHistory);

  }

  /**
   * Creates the job status.
   *
   * @param jobHistory the job history
   */
  public void createJobStatus(final JobHistory jobHistory) {
    jobHistoryService.saveOrUpdate(jobHistory);
  }

  /**
   * Update job status.
   *
   * @param jobHistory the job history
   */
  public void updateJobStatus(final JobHistory jobHistory) {
    jobHistoryService.saveOrUpdate(jobHistory);
  }
}
