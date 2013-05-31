package com.telenoetica.service.excel.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.util.Assert;

import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ExcelRendererModel;

public abstract class AbstractExcelLayoutService implements ExcelLayoutService {

  @Override
  public void buildReport(ExcelRendererModel excelRendererModel) {

    HSSFSheet worksheet = excelRendererModel.getWorksheet();
    List<String> columns = excelRendererModel.getColumns();
    Assert.isTrue(CollectionUtils.isNotEmpty(columns), "Columns cannot be empty.");
 // Set column widths
    for (int i = 0; i < columns.size(); i++) {
      worksheet.setColumnWidth(i, excelRendererModel.getColumnWidth());
    }

    buildTitle(excelRendererModel);
    buildHeaders(excelRendererModel);
  }

  public abstract void buildHeaders(ExcelRendererModel excelRendererModel);


  public abstract void buildTitle(ExcelRendererModel excelRendererModel);

}
