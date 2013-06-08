package com.telenoetica.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.SiteService;

public class DieselDetailsReport extends QuartzJobBean {

	private DieselVisitService dieselVisitService;

	private SiteService siteService;


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {


	}

}
