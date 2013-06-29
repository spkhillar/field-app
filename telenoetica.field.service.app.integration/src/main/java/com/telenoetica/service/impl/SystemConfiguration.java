package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("systemConfig")
public class SystemConfiguration {

  @Value("${diesel.details.report.directory}")
  private String dieselDetailsReportDirectory;

  @Value("${spares.utilization.report.directory}")
  private String sparesUtilizationReportDirectory;


  @Value("${diesel.details.report.file.name}")
  private String dieselDetailsReportFileName;

  @Value("${spares.utilization.report.file.name}")
  private String sparesUtilizationReportFileName;

  public String getDieselDetailsReportDirectory() {
    return dieselDetailsReportDirectory;
  }

  public String getSparesUtilizationReportDirectory() {
    return sparesUtilizationReportDirectory;
  }

  public String getDieselDetailsReportFileName() {
    return dieselDetailsReportFileName;
  }

  public String getSparesUtilizationReportFileName() {
    return sparesUtilizationReportFileName;
  }



}
