package com.telenoetica.scheduler.job;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.impl.DieselDetailReportServiceImpl;

public class DieselDetailsReport extends QuartzJobBean {

	private static final Logger LOGGER = Logger
			.getLogger(DieselDetailsReport.class);

	private DieselDetailReportServiceImpl detailReportServiceImpl;

	private MessageSource messageSource;

	private SiteService siteService;

	private JobHistoryService jobHistoryService;

	public void setJobHistoryService(final JobHistoryService jobHistoryService) {
		this.jobHistoryService = jobHistoryService;
	}

	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context)
			throws JobExecutionException {
		LOGGER.debug("Job Started");
		JobHistory jobHistory = new JobHistory("DieselDetailReportJob",
				"DieselDetailReportJob", new Date(), null, JobStatus.RUNNING);
		createJobStatus(jobHistory);
		// setup the
		// Do your work
		List<Site> siteList = siteService.getSites();
		try {
			detailReportServiceImpl.createNewReport(siteList);
		} catch (Exception e) {
			LOGGER.debug("Error while creating Report");
			e.printStackTrace();
		}
		jobHistory.setEndTime(new Date());
		jobHistory.setJobStatus(JobStatus.COMPLETED);
		updateJobStatus(jobHistory);

	}

	public void createJobStatus(final JobHistory jobHistory) {
		jobHistoryService.saveOrUpdate(jobHistory);
	}

	public void updateJobStatus(final JobHistory jobHistory) {
		jobHistoryService.saveOrUpdate(jobHistory);
	}
}
