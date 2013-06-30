package com.telenoetica.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.telenoetica.jpa.entities.JobHistory;

public interface JobHistoryService extends BaseService<JobHistory> {

	List<JobHistory> findByStartTimeBetween();

	void exportReport(String reportPath, HttpServletResponse httpServletResponse);

	String getPath(long jobId);

}
