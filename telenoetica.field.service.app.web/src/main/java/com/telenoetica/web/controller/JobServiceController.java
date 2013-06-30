package com.telenoetica.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.service.JobHistoryService;

@Controller
@RequestMapping(value = "/reportDownload")
@SessionAttributes("jobHistoryForm")
public class JobServiceController extends BaseController {

	@Autowired
	JobHistoryService jobHistoryService;

	/**
	 * Creates the.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/diesel")
	public String create() {
		return "reportDownload.diesel";
	}

	@RequestMapping(value = "/dieselReportList", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<JobHistory> findByStartTimeBetween() {
		List<JobHistory> listReport = jobHistoryService
				.findByStartTimeBetween();
		return listReport;
	}

	@RequestMapping(value = "/diesel/export/{jobId}")
	@ResponseBody
	public void exportDieselDetailsReport(@PathVariable final long jobId,
			final HttpServletResponse httpServletResponse) {
		String reportPath = jobHistoryService.getPath(jobId);
		jobHistoryService.exportReport(reportPath, httpServletResponse);
	}
}
