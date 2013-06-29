package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Site;

public interface DieselDetailReportService {

	public void createNewReport(final List<Site> siteList) throws Exception;

}
