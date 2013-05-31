package com.telenoetica.service.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExcelRendererModel {
  
  private HSSFSheet worksheet;
  
  private int startRowIndex=0;
  
  private int startColIndex=0;
  
  private int columnWidth;
  
  private List<String> columns;
  
  private String reportTitle;
  

  public ExcelRendererModel(HSSFSheet worksheet, int columnWidth, List<String> columns,String reportTitle) {
    super();
    this.worksheet = worksheet;
    this.columnWidth = columnWidth;
    this.columns = columns;
    this.reportTitle = reportTitle;
  }

  public ExcelRendererModel(HSSFSheet worksheet, int startRowIndex, int startColIndex, int columnWidth,
      List<String> columns,String reportTitle) {
    super();
    this.worksheet = worksheet;
    this.startRowIndex = startRowIndex;
    this.startColIndex = startColIndex;
    this.columnWidth = columnWidth;
    this.columns = columns;
    this.reportTitle = reportTitle;
  }

  public HSSFSheet getWorksheet() {
    return worksheet;
  }

  public int getStartRowIndex() {
    return startRowIndex;
  }

  public int getStartColIndex() {
    return startColIndex;
  }

  public int getColumnWidth() {
    return columnWidth;
  }

  public List<String> getColumns() {
    return columns;
  }

  public String getReportTitle() {
    return reportTitle;
  }

}
