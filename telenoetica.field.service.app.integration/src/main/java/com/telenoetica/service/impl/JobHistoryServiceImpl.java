package com.telenoetica.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.repositories.JobHistoryDAO;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.util.ExcelWriter;

@Service("jobHistoryService")
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

	@Autowired
	private JobHistoryDAO jobHistoryDAO;

	@Override
	public JobHistory retrieve(final Long id) {
		return jobHistoryDAO.findOne(id);
	}

	@Override
	public JobHistory saveOrUpdate(final JobHistory jobHistory) {
		return jobHistoryDAO.saveAndFlush(jobHistory);
	}

	@Override
	public void delete(final JobHistory jobHistory) {
		jobHistoryDAO.delete(jobHistory);
	}

	@Override
	public List<JobHistory> findByStartTimeBetween() {
		Date startDate;
		Date endDate;
		Calendar currentDate = Calendar.getInstance(); // Get the current date
		endDate = currentDate.getTime();
		startDate = new DateTime(endDate).minusMonths(12).toDate();
		List<JobHistory> jobHistoryList = jobHistoryDAO.findByStartTimeBetween(
				startDate, endDate);
		return jobHistoryList;
	}

	@Override
	public String getPath(final long jobId) {
		JobHistory jobHistory = jobHistoryDAO.findById(jobId);
		String path = jobHistory.getPath();
		return path;
	}

	@Override
	public void exportReport(final String reportPath,
			final HttpServletResponse httpServletResponse) {

		String reportFileName = reportPath;
		ExcelWriter.write(httpServletResponse, reportFileName);
	}
}
