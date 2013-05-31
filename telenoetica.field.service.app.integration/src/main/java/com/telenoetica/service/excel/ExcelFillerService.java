package com.telenoetica.service.excel;

import java.util.List;

import com.telenoetica.service.util.ExcelRendererModel;

public interface ExcelFillerService {
   void fillReport(ExcelRendererModel excelRendererModel,List<List<Object>> targetValues );
  
  
}
