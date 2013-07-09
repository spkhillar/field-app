package com.telenoetica.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.List;

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
import com.telenoetica.service.util.ServiceUtil;

@Service("dieselDetailReportService")
public class DieselDetailReportServiceImpl implements DieselDetailReportService {

  @Autowired
  private DieselVisitService dieselVisitService;

  private HSSFWorkbook workbook = null;

  public static final DateFormat dateFormat = DateFormat.getDateInstance();

  @Autowired
  MessageSource messageSource;

  @Override
  public void createNewReport(final List<Site> siteList) throws Exception {

    String configuredFileName = messageSource.getMessage(
      "fieldapp.report.diselReport.template.path", null, null);
    InputStream is = this.getClass()
        .getResourceAsStream(configuredFileName);
    // create a POIFSFileSystem object to read the data
    POIFSFileSystem fs = new POIFSFileSystem(is);

    workbook = new HSSFWorkbook(fs);
    setSheetData(workbook.getSheetAt(0), siteList);
    closeReport();
  }

  @SuppressWarnings("deprecation")
  private void setSheetData(final HSSFSheet sheet, final List siteList) {
    HSSFRow row;
    HSSFCell cell;
    int rNum = 2;
    for (int i = 0; i < siteList.size(); i++) {
      Site siteName = (Site) siteList.get(i);
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

  public void closeReport() throws Exception {
    /*
     * for (short i = 0; i < HEADERS.length; i++) { sheet.autoSizeColumn(i);
     * }
     */

    /*
     * File file = new File(messageSource.getMessage(
     * "fieldapp.report.diselReport.file.save.path", null, null) +
     * messageSource.getMessage( "fieldapp.report.diselReport.File.name",
     * null, null));
     */
    File file = new File(messageSource.getMessage(
      "fieldapp.report.diselReport.File.name", null, null));
    // write the new changes to a new file
    FileOutputStream fos = new FileOutputStream(file);

    System.out.println("RETURNED FILE PATH: " + file.getAbsolutePath());
    workbook.write(fos);
    fos.flush();
    fos.close();
  }

}
