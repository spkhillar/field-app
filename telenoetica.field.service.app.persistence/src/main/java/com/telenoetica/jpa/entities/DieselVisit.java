/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * DieselVisit.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "diesel_visit")
public class DieselVisit implements java.io.Serializable,BaseEntity {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -68293699494074823L;
  
  /** The id. */
  private Long id;
  
  /** The version. */
  private Integer version;
  
  /** The user. */
  private User user;
  
  /** The site. */
  private Site site;
  
  /** The access code. */
  private String accessCode;
  
  /** The drn number. */
  private String drnNumber;
  
  /** The diesel transfer or bulk supply. */
  private String dieselTransferOrBulkSupply;
  
  /** The transfer from which site. */
  private Long transferFromWhichSite;
  
  /** The bulk name of vendor. */
  private String bulkNameOfVendor;
  
  /** The diesel level t1 before visit. */
  private Long dieselLevelT1BeforeVisit;
  
  /** The diesel level t2 before visit. */
  private Long dieselLevelT2BeforeVisit;
  
  /** The diesel received ltrs. */
  private Long dieselReceivedLtrs;
  
  /** The run hour gen1. */
  private Long runHourGen1;
  
  /** The run hour gen2. */
  private Long runHourGen2;
  
  /** The drn book at site. */
  private Boolean drnBookAtSite;
  
  /** The diesel log book maintained. */
  private Boolean dieselLogBookMaintained;
  
  /** The phcn installed. */
  private Boolean phcnInstalled;
  
  /** The phcn hrs per day. */
  private Long phcnHrsPerDay;
  
  /** The hybrid or piu installed. */
  private Boolean hybridOrPiuInstalled;
  
  /** The hybrid or piu hrs per day. */
  private Long hybridOrPiuHrsPerDay;

  /** The created at. */
  private Date createdAt;

  /**
   * Instantiates a new diesel visit.
   */
  public DieselVisit() {}

  /**
   * Instantiates a new diesel visit.
   *
   * @param createdAt the created at
   */
  public DieselVisit(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Instantiates a new diesel visit.
   *
   * @param user the user
   * @param site the site
   * @param accessCode the access code
   * @param drnNumber the drn number
   * @param dieselTransferOrBulkSupply the diesel transfer or bulk supply
   * @param transferFromWhichSite the transfer from which site
   * @param bulkNameOfVendor the bulk name of vendor
   * @param dieselLevelT1BeforeVisit the diesel level t1 before visit
   * @param dieselLevelT2BeforeVisit the diesel level t2 before visit
   * @param dieselReceivedLtrs the diesel received ltrs
   * @param runHourGen1 the run hour gen1
   * @param runHourGen2 the run hour gen2
   * @param drnBookAtSite the drn book at site
   * @param dieselLogBookMaintained the diesel log book maintained
   * @param createdAt the created at
   */
  public DieselVisit(User user, Site site, String accessCode, String drnNumber, String dieselTransferOrBulkSupply,
      Long transferFromWhichSite, String bulkNameOfVendor, Long dieselLevelT1BeforeVisit,
      Long dieselLevelT2BeforeVisit, Long dieselReceivedLtrs, Long runHourGen1, Long runHourGen2,
      Boolean drnBookAtSite, Boolean dieselLogBookMaintained, Date createdAt) {
    this.user = user;
    this.site = site;
    this.accessCode = accessCode;
    this.drnNumber = drnNumber;
    this.dieselTransferOrBulkSupply = dieselTransferOrBulkSupply;
    this.transferFromWhichSite = transferFromWhichSite;
    this.bulkNameOfVendor = bulkNameOfVendor;
    this.dieselLevelT1BeforeVisit = dieselLevelT1BeforeVisit;
    this.dieselLevelT2BeforeVisit = dieselLevelT2BeforeVisit;
    this.dieselReceivedLtrs = dieselReceivedLtrs;
    this.runHourGen1 = runHourGen1;
    this.runHourGen2 = runHourGen2;
    this.drnBookAtSite = drnBookAtSite;
    this.dieselLogBookMaintained = dieselLogBookMaintained;
    this.createdAt = createdAt;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  public Long getId() {
    return this.id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the version.
   *
   * @return the version
   */
  @Version
  @Column(name = "version")
  public Integer getVersion() {
    return this.version;
  }

  /**
   * Sets the version.
   *
   * @param version the new version
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Gets the user.
   *
   * @return the user
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public User getUser() {
    return this.user;
  }

  /**
   * Sets the user.
   *
   * @param user the new user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets the site.
   *
   * @return the site
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "site_id")
  public Site getSite() {
    return this.site;
  }

  /**
   * Sets the site.
   *
   * @param site the new site
   */
  public void setSite(Site site) {
    this.site = site;
  }

  /**
   * Gets the access code.
   *
   * @return the access code
   */
  @Column(name = "access_code", length = 250)
  public String getAccessCode() {
    return this.accessCode;
  }

  /**
   * Sets the access code.
   *
   * @param accessCode the new access code
   */
  public void setAccessCode(String accessCode) {
    this.accessCode = accessCode;
  }

  /**
   * Gets the drn number.
   *
   * @return the drn number
   */
  @Column(name = "drn_number", length = 250)
  public String getDrnNumber() {
    return this.drnNumber;
  }

  /**
   * Sets the drn number.
   *
   * @param drnNumber the new drn number
   */
  public void setDrnNumber(String drnNumber) {
    this.drnNumber = drnNumber;
  }

  /**
   * Gets the diesel transfer or bulk supply.
   *
   * @return the diesel transfer or bulk supply
   */
  @Column(name = "diesel_transfer_or_bulk_supply", length = 250)
  public String getDieselTransferOrBulkSupply() {
    return this.dieselTransferOrBulkSupply;
  }

  /**
   * Sets the diesel transfer or bulk supply.
   *
   * @param dieselTransferOrBulkSupply the new diesel transfer or bulk supply
   */
  public void setDieselTransferOrBulkSupply(String dieselTransferOrBulkSupply) {
    this.dieselTransferOrBulkSupply = dieselTransferOrBulkSupply;
  }

  /**
   * Gets the transfer from which site.
   *
   * @return the transfer from which site
   */
  @Column(name = "transfer_from_which_site")
  public Long getTransferFromWhichSite() {
    return this.transferFromWhichSite;
  }

  /**
   * Sets the transfer from which site.
   *
   * @param transferFromWhichSite the new transfer from which site
   */
  public void setTransferFromWhichSite(Long transferFromWhichSite) {
    this.transferFromWhichSite = transferFromWhichSite;
  }

  /**
   * Gets the bulk name of vendor.
   *
   * @return the bulk name of vendor
   */
  @Column(name = "bulk_name_of_vendor", length = 250)
  public String getBulkNameOfVendor() {
    return this.bulkNameOfVendor;
  }

  /**
   * Sets the bulk name of vendor.
   *
   * @param bulkNameOfVendor the new bulk name of vendor
   */
  public void setBulkNameOfVendor(String bulkNameOfVendor) {
    this.bulkNameOfVendor = bulkNameOfVendor;
  }

  /**
   * Gets the diesel level t1 before visit.
   *
   * @return the diesel level t1 before visit
   */
  @Column(name = "diesel_level_t1_before_visit")
  public Long getDieselLevelT1BeforeVisit() {
    return this.dieselLevelT1BeforeVisit;
  }

  /**
   * Sets the diesel level t1 before visit.
   *
   * @param dieselLevelT1BeforeVisit the new diesel level t1 before visit
   */
  public void setDieselLevelT1BeforeVisit(Long dieselLevelT1BeforeVisit) {
    this.dieselLevelT1BeforeVisit = dieselLevelT1BeforeVisit;
  }

  /**
   * Gets the diesel level t2 before visit.
   *
   * @return the diesel level t2 before visit
   */
  @Column(name = "diesel_level_t2_before_visit")
  public Long getDieselLevelT2BeforeVisit() {
    return this.dieselLevelT2BeforeVisit;
  }

  /**
   * Sets the diesel level t2 before visit.
   *
   * @param dieselLevelT2BeforeVisit the new diesel level t2 before visit
   */
  public void setDieselLevelT2BeforeVisit(Long dieselLevelT2BeforeVisit) {
    this.dieselLevelT2BeforeVisit = dieselLevelT2BeforeVisit;
  }

  /**
   * Gets the diesel received ltrs.
   *
   * @return the diesel received ltrs
   */
  @Column(name = "diesel_received_ltrs")
  public Long getDieselReceivedLtrs() {
    return this.dieselReceivedLtrs;
  }

  /**
   * Sets the diesel received ltrs.
   *
   * @param dieselReceivedLtrs the new diesel received ltrs
   */
  public void setDieselReceivedLtrs(Long dieselReceivedLtrs) {
    this.dieselReceivedLtrs = dieselReceivedLtrs;
  }

  /**
   * Gets the run hour gen1.
   *
   * @return the run hour gen1
   */
  @Column(name = "run_hour_gen1")
  public Long getRunHourGen1() {
    return this.runHourGen1;
  }

  /**
   * Sets the run hour gen1.
   *
   * @param runHourGen1 the new run hour gen1
   */
  public void setRunHourGen1(Long runHourGen1) {
    this.runHourGen1 = runHourGen1;
  }

  /**
   * Gets the run hour gen2.
   *
   * @return the run hour gen2
   */
  @Column(name = "run_hour_gen2")
  public Long getRunHourGen2() {
    return this.runHourGen2;
  }

  /**
   * Sets the run hour gen2.
   *
   * @param runHourGen2 the new run hour gen2
   */
  public void setRunHourGen2(Long runHourGen2) {
    this.runHourGen2 = runHourGen2;
  }

  /**
   * Gets the drn book at site.
   *
   * @return the drn book at site
   */
  @Column(name = "drn_book_at_site")
  public Boolean getDrnBookAtSite() {
    return this.drnBookAtSite;
  }

  /**
   * Sets the drn book at site.
   *
   * @param drnBookAtSite the new drn book at site
   */
  public void setDrnBookAtSite(Boolean drnBookAtSite) {
    this.drnBookAtSite = drnBookAtSite;
  }

  /**
   * Gets the diesel log book maintained.
   *
   * @return the diesel log book maintained
   */
  @Column(name = "diesel_log_book_maintained")
  public Boolean getDieselLogBookMaintained() {
    return this.dieselLogBookMaintained;
  }

  /**
   * Sets the diesel log book maintained.
   *
   * @param dieselLogBookMaintained the new diesel log book maintained
   */
  public void setDieselLogBookMaintained(Boolean dieselLogBookMaintained) {
    this.dieselLogBookMaintained = dieselLogBookMaintained;
  }

  /**
   * Gets the phcn installed.
   *
   * @return the phcn installed
   */
  @Column(name = "phcn_installed")
  public Boolean getPhcnInstalled() {
    return phcnInstalled;
  }

  /**
   * Sets the phcn installed.
   *
   * @param phcnInstalled the new phcn installed
   */
  public void setPhcnInstalled(Boolean phcnInstalled) {
    this.phcnInstalled = phcnInstalled;
  }

  /**
   * Gets the phcn hrs per day.
   *
   * @return the phcn hrs per day
   */
  @Column(name = "phcn_hr_per_day")
  public Long getPhcnHrsPerDay() {
    return phcnHrsPerDay;
  }

  /**
   * Sets the phcn hrs per day.
   *
   * @param phcnHrsPerDay the new phcn hrs per day
   */
  public void setPhcnHrsPerDay(Long phcnHrsPerDay) {
    this.phcnHrsPerDay = phcnHrsPerDay;
  }

  /**
   * Gets the hybrid or piu installed.
   *
   * @return the hybrid or piu installed
   */
  @Column(name = "hybrid_or_piu_installed")
  public Boolean getHybridOrPiuInstalled() {
    return hybridOrPiuInstalled;
  }

  /**
   * Sets the hybrid or piu installed.
   *
   * @param hybridOrPiuInstalled the new hybrid or piu installed
   */
  public void setHybridOrPiuInstalled(Boolean hybridOrPiuInstalled) {
    this.hybridOrPiuInstalled = hybridOrPiuInstalled;
  }

  /**
   * Gets the hybrid or piu hrs per day.
   *
   * @return the hybrid or piu hrs per day
   */
  @Column(name = "hybrid_or_piu_hr_per_day")
  public Long getHybridOrPiuHrsPerDay() {
    return hybridOrPiuHrsPerDay;
  }

  /**
   * Sets the hybrid or piu hrs per day.
   *
   * @param hybridOrPiuHrsPerDay the new hybrid or piu hrs per day
   */
  public void setHybridOrPiuHrsPerDay(Long hybridOrPiuHrsPerDay) {
    this.hybridOrPiuHrsPerDay = hybridOrPiuHrsPerDay;
  }

  /**
   * Gets the created at.
   *
   * @return the created at
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, length = 19)
  public Date getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Sets the created at.
   *
   * @param createdAt the new created at
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

}
