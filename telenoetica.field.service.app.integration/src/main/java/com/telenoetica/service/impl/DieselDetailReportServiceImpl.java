package com.telenoetica.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.service.DieselDetailReportService;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.util.ServiceUtil;

@Service("dieselDetailReportService")
public class DieselDetailReportServiceImpl implements DieselDetailReportService {

  private static final Logger LOGGER = Logger
      .getLogger(DieselDetailReportService.class);

  @Autowired
  private DieselVisitService dieselVisitService;

  @Autowired
  private SiteService siteService;

  private HSSFWorkbook workbook = null;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private SystemConfiguration systemConfiguration;

  @Override
  public String createNewReport() throws Exception {
    LOGGER.debug("Service DieselDetailReportService Started");
    List<Site> siteList = siteService.getSites();
    String configuredFileName = messageSource.getMessage(
      "fieldapp.report.diselReport.template.path", null, null);
    LOGGER.debug("DieselDetailReport template is : " + configuredFileName);
    InputStream is = this.getClass()
        .getResourceAsStream(configuredFileName);
    // create a POIFSFileSystem object to read the data

    POIFSFileSystem fs = new POIFSFileSystem(is);

    workbook = new HSSFWorkbook(fs);
    setSheetData(workbook.getSheetAt(0), siteList);
    String reportName = closeReport();
    return reportName;
  }

  private void setSheetData(final HSSFSheet sheet, final List<Site> siteList) {
    HSSFRow row;
    HSSFCell cell;
    int rNum = 2;
    for (int i = 0; i < siteList.size(); i++) {
      Site siteName = siteList.get(i);
      List<DieselVisit> dieselVisitL = dieselVisitService
          .findBySiteAndCreatedAtBetween(siteName);
      int rNumPrev = rNum;
      for (int j = 0; j < dieselVisitL.size(); j++) {
        row = sheet.createRow(rNum++);
        DieselVisit dieselVisit = dieselVisitL.get(j);
        cell = row.createCell(0);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getSiteId()));
        cell = row.createCell(1);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getDieselLevelT1BeforeVisit()));
        cell = row.createCell(2);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getDieselLevelT2BeforeVisit()));
        cell = row.createCell(3);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getRunHourGen1()));
        cell = row.createCell(4);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getRunHourGen2()));
        cell = row.createCell(5);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getCreatedAt()));
        cell = row.createCell(6);
        if (ServiceUtil.checkAndReturnValue(
          dieselVisit.getDieselTransferOrBulkSupply())
          .equalsIgnoreCase("bulk")) {
          cell.setCellValue(dieselVisit.getDrnNumber());
        }
        cell = row.createCell(7);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getTransferredSiteId()));
        cell = row.createCell(8);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getUserId()));
        cell = row.createCell(9);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getDieselReceivedLtrs()));
        cell = row.createCell(10);
        if (ServiceUtil.checkAndReturnValue(
          dieselVisit.getDieselTransferOrBulkSupply())
          .equalsIgnoreCase("site")) {
          cell.setCellValue(dieselVisit.getDrnNumber());
        }
        cell = row.createCell(11);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getAccessCode()));
        cell = row.createCell(12);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getPhcnHrsPerDay()));
        cell = row.createCell(13);
        cell.setCellValue(ServiceUtil.checkAndReturnValue(dieselVisit
          .getHybridOrPiuHrsPerDay()));
      }
      if ((rNumPrev != rNum) && ((rNum - rNumPrev) > 1)) {
        sheet.groupRow(rNumPrev + 1, rNum);
        sheet.setRowGroupCollapsed(rNumPrev + 1, true);
      }

    }
  }

  public String closeReport() throws Exception {
    String reportName = addTimeInFileName(systemConfiguration
      .getDieselDetailsReportFileName());

    String reportFilePath = systemConfiguration
        .getDieselDetailsReportDirectory() + "\\" + reportName;
    File file = new File(reportFilePath);
    // write the new changes to a new file
    FileOutputStream fos = new FileOutputStream(file);

    LOGGER.debug("RETURNED FILE PATH: " + file.getAbsolutePath());
    workbook.write(fos);
    fos.flush();
    fos.close();
    return reportFilePath;
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
    LOGGER.debug("Creating new excel doc named: " + name);
    return name;
  }

}
