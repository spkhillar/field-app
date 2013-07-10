/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The Class SystemConfiguration.
 * Place holder to fetch properties defined in system-config.properties.
 */
@Service("systemConfig")
public class SystemConfiguration {

  /** The diesel details report directory. */
  @Value("${diesel.details.report.directory}")
  private String dieselDetailsReportDirectory;

  /** The spares utilization report directory. */
  @Value("${spares.utilization.report.directory}")
  private String sparesUtilizationReportDirectory;

  /** The diesel details report file name. */
  @Value("${diesel.details.report.file.name}")
  private String dieselDetailsReportFileName;

  /** The spares utilization report file name. */
  @Value("${spares.utilization.report.file.name}")
  private String sparesUtilizationReportFileName;

  /** The android app file path. */
  @Value("${android.apk.source.file.name}")
  private String androidAppFilePath;

  /**
   * Gets the diesel details report directory.
   *
   * @return the diesel details report directory
   */
  public String getDieselDetailsReportDirectory() {
    return dieselDetailsReportDirectory;
  }

  /**
   * Gets the spares utilization report directory.
   *
   * @return the spares utilization report directory
   */
  public String getSparesUtilizationReportDirectory() {
    return sparesUtilizationReportDirectory;
  }

  /**
   * Gets the diesel details report file name.
   *
   * @return the diesel details report file name
   */
  public String getDieselDetailsReportFileName() {
    return dieselDetailsReportFileName;
  }

  /**
   * Gets the spares utilization report file name.
   *
   * @return the spares utilization report file name
   */
  public String getSparesUtilizationReportFileName() {
    return sparesUtilizationReportFileName;
  }

  /**
   * Gets the android app file path.
   *
   * @return the android app file path
   */
  public String getAndroidAppFilePath() {
    return androidAppFilePath;
  }

}
