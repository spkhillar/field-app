package com.telenoetica.scheduler.job;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.SiteService;

public class DieselDetailsReport extends QuartzJobBean {

	private static final Logger LOGGER = Logger
			.getLogger(DieselDetailsReport.class);

	private DieselVisitService dieselVisitService;

	private MessageSource messageSource;

	private SiteService siteService;

	private JobHistoryService jobHistoryService;

	public void setJobHistoryService(final JobHistoryService jobHistoryService) {
		this.jobHistoryService = jobHistoryService;
	}

	public void setDieselVisitService(
			final DieselVisitService dieselVisitService) {
		this.dieselVisitService = dieselVisitService;
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

	/**
	 * @param heldOrders
	 * @param pPath
	 * @return
	 */
	public String createExcelReport(final Vector heldOrders, final String pPath) {
		return null;
		/*
		 * 
		 * String filePath = null ; String name =
		 * "Field_App_Diesel_Details_Report_" ;
		 * 
		 * name = addTimeInFileName ( name ) ; File xFile = new File ( pPath +
		 * name ) ; / / File xFile = new File ( name ) ; / / create a wireline
		 * info object here now / / HeldOrderInfo hInfo = null ;
		 * 
		 * try { HSSFWorkbook book = new HSSFWorkbook ( ) ;
		 * 
		 * HSSFSheet hSheets [ ] = new HSSFSheet [ 1 ] ; hSheets [ 0 ] = book .
		 * createSheet ( "DieselDetailReport" ) ;
		 * 
		 * HSSFCell c = null ; HSSFRow r = null ;
		 * 
		 * / / create a default cell style for cell headers HSSFCellStyle
		 * headerCellStyle = book . createCellStyle ( ) ; HSSFFont f = book .
		 * createFont ( ) ; f . setBoldweight ( Font . BOLDWEIGHT_BOLD ) ; f .
		 * setFontHeightInPoints ( ( short ) 13 ) ; headerCellStyle .
		 * setWrapText ( false ) ; headerCellStyle . setFont ( f ) ;
		 * headerCellStyle . setAlignment ( CellStyle . ALIGN_CENTER ) ;
		 * headerCellStyle . setBorderBottom ( CellStyle . BORDER_MEDIUM ) ;
		 * headerCellStyle . setBorderLeft ( CellStyle . BORDER_MEDIUM ) ;
		 * headerCellStyle . setBorderRight ( CellStyle . BORDER_MEDIUM ) ;
		 * headerCellStyle . setBorderTop ( CellStyle . BORDER_MEDIUM ) ;
		 * 
		 * / / set up some arrays here to hold the header strings String headers
		 * [ ] = new String [ 8 ] ; headers [ 0 ] = "SITEID" ; headers [ 1 ] =
		 * "DISEL LEVEL" ; headers [ 2 ] = "Timestamp" ; headers [ 3 ] =
		 * "OrderMax Version #" ;
		 * 
		 * r = hSheets [ 0 ] . createRow ( ( short ) 0 ) ; hSheets [ 0 ] .
		 * setDefaultColumnWidth ( ( short ) 25 ) ; / / now lets populate the
		 * headers and format the width of / / each cell c = r . createCell ( (
		 * short ) 1 ) ; c . setCellType ( Cell . CELL_TYPE_STRING ) ; c .
		 * setCellStyle ( headerCellStyle ) ; c . setCellValue ( "SITEID" ) ; c
		 * = r . createCell ( ( short ) 1 ) ; c . setCellType ( Cell .
		 * CELL_TYPE_STRING ) ; c . setCellStyle ( headerCellStyle ) ; c .
		 * setCellValue ( "DISEL LEVEL" ) ; / / hSheets [ 0 ] . addMergedRegion
		 * ( CellRangeAddress . valueOf ( "B1:C1" ) ) ;
		 * 
		 * / / now lets populate the data for ( int j = 0 ; j < hSheets . length
		 * ; j ++ ) { / / tbd }
		 * 
		 * FileOutputStream out = new FileOutputStream ( xFile ) ; filePath =
		 * xFile . getAbsolutePath ( ) ; System . out . println (
		 * "RETURNED FILE PATH: " + filePath ) ; book . write ( out ) ; out .
		 * flush ( ) ; out . close ( ) ; } catch ( IOException e ) { } System .
		 * out . println ( "Returned STRING: " + filePath ) ; return filePath ;
		 */
	}

	private String addTimeInFileName(String name) {
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);

		name += month + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_"
				+ cal.get(Calendar.YEAR) + "_" + hour + "_" + minute + "_"
				+ seconds + ".xls";
		System.out.println("Creating new excel doc named: " + name);
		return name;
	}
}
