package com.telenoetica.service.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWriter {

  private static Logger logger = Logger.getLogger(ExcelWriter.class);

  /**
   * Writes the report to the http servlet response output stream
   */
  public static void write(HttpServletResponse response, HSSFWorkbook workbook,String attachmentFileName) {

    logger.debug("Writing report to the stream");
    try {
      response.setContentType("application/vnd.ms-excel");
      response.setHeader("Content-Disposition", "attachment; filename="+attachmentFileName);
      // Retrieve the output stream
      ServletOutputStream outputStream = response.getOutputStream();
      // Write to the output stream
      workbook.write(outputStream);
      // Flush the stream
      outputStream.flush();

    } catch (Exception e) {
      logger.error("Unable to write report to the output stream");
    }
  }

  /**
   * Writes the report to the http servlet response output stream
   */
  public static void write(String excelFileName, HSSFWorkbook workbook) {

    logger.debug("Writing report to the filename:"+excelFileName);
    try {
      File file = new File (excelFileName);
      logger.debug("Writing report to the actual file path :"+file.getAbsolutePath());
      FileOutputStream fileOut = new FileOutputStream(excelFileName);
      workbook.write(fileOut);
      fileOut.close();
    } catch (Exception e) {
      logger.error("Unable to write report to the output stream");
    }
  }

}
