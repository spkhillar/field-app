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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 */
@Entity
@Table(name = "call_out_visit")
@JsonAutoDetect(JsonMethod.NONE)
public class CallOutVisit implements BaseEntity, java.io.Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -1043305759290140709L;
  
  @JsonProperty
  private Long id;
  private Integer version;
  
  @JsonProperty
  private User user;
  
  @JsonProperty
  private Site site;
  
  @JsonProperty
  private String accessCode;
  
  @JsonProperty
  private String callOutCsrOrTtNumber;
  
  @JsonProperty
  private Date timeComplainReceived;
  
  @JsonProperty
  private Date timeReachedToSite;
  
  @JsonProperty
  private Date timeFaultReserved;
  
  @JsonProperty
  private String customer1Impacted;
  
  @JsonProperty
  private String customer2Impacted;
  
  @JsonProperty
  private String customer3Impacted;
  
  @JsonProperty
  private String customer4Impacted;
  
  @JsonProperty
  private String mainCategoryOfFault;
  
  @JsonProperty
  private String equipmentComponentCausedFault;
  
  @JsonProperty
  private String equipmentComponentRepaired;
  
  @JsonProperty
  private String equipmentComponentReplaced;
  
  @JsonProperty
  private String fixResolutionTemporaryOrPermanent;
  
  @JsonProperty
  private String actionsRequiredForPermanentResolution;
  
  @JsonProperty
  private Date createdAt;

  public CallOutVisit() {}

  public CallOutVisit(Date timeComplainReceived, Date timeReachedToSite, Date timeFaultReserved, Date createdAt) {
    this.timeComplainReceived = timeComplainReceived;
    this.timeReachedToSite = timeReachedToSite;
    this.timeFaultReserved = timeFaultReserved;
    this.createdAt = createdAt;
  }

  public CallOutVisit(User user, Site site, String accessCode, String callOutCsrOrTtNumber, Date timeComplainReceived,
      Date timeReachedToSite, Date timeFaultReserved, String customer1Impacted, String customer2Impacted,
      String customer3Impacted, String customer4Impacted, String mainCategoryOfFault,
      String equipmentComponentCausedFault, String equipmentComponentRepaired, String equipmentComponentReplaced,
      String fixResolutionTemporaryOrPermanent, String actionsRequiredForPermanentResolution, Date createdAt) {
    this.user = user;
    this.site = site;
    this.accessCode = accessCode;
    this.callOutCsrOrTtNumber = callOutCsrOrTtNumber;
    this.timeComplainReceived = timeComplainReceived;
    this.timeReachedToSite = timeReachedToSite;
    this.timeFaultReserved = timeFaultReserved;
    this.customer1Impacted = customer1Impacted;
    this.customer2Impacted = customer2Impacted;
    this.customer3Impacted = customer3Impacted;
    this.customer4Impacted = customer4Impacted;
    this.mainCategoryOfFault = mainCategoryOfFault;
    this.equipmentComponentCausedFault = equipmentComponentCausedFault;
    this.equipmentComponentRepaired = equipmentComponentRepaired;
    this.equipmentComponentReplaced = equipmentComponentReplaced;
    this.fixResolutionTemporaryOrPermanent = fixResolutionTemporaryOrPermanent;
    this.actionsRequiredForPermanentResolution = actionsRequiredForPermanentResolution;
    this.createdAt = createdAt;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Version
  @Column(name = "version")
  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "site_id")
  public Site getSite() {
    return this.site;
  }

  public void setSite(Site site) {
    this.site = site;
  }

  @Column(name = "access_code", length = 250)
  public String getAccessCode() {
    return this.accessCode;
  }

  public void setAccessCode(String accessCode) {
    this.accessCode = accessCode;
  }

  @Column(name = "call_out_csr_or_tt_number", length = 250)
  public String getCallOutCsrOrTtNumber() {
    return this.callOutCsrOrTtNumber;
  }

  public void setCallOutCsrOrTtNumber(String callOutCsrOrTtNumber) {
    this.callOutCsrOrTtNumber = callOutCsrOrTtNumber;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_complain_received", nullable = false, length = 19)
  public Date getTimeComplainReceived() {
    return this.timeComplainReceived;
  }

  public void setTimeComplainReceived(Date timeComplainReceived) {
    this.timeComplainReceived = timeComplainReceived;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_reached_to_site", nullable = false, length = 19)
  public Date getTimeReachedToSite() {
    return this.timeReachedToSite;
  }

  public void setTimeReachedToSite(Date timeReachedToSite) {
    this.timeReachedToSite = timeReachedToSite;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_fault_reserved", nullable = false, length = 19)
  public Date getTimeFaultReserved() {
    return this.timeFaultReserved;
  }

  public void setTimeFaultReserved(Date timeFaultReserved) {
    this.timeFaultReserved = timeFaultReserved;
  }

  @Column(name = "customer1_impacted", length = 250)
  public String getCustomer1Impacted() {
    return this.customer1Impacted;
  }

  public void setCustomer1Impacted(String customer1Impacted) {
    this.customer1Impacted = customer1Impacted;
  }

  @Column(name = "customer2_impacted", length = 250)
  public String getCustomer2Impacted() {
    return this.customer2Impacted;
  }

  public void setCustomer2Impacted(String customer2Impacted) {
    this.customer2Impacted = customer2Impacted;
  }

  @Column(name = "customer3_impacted", length = 250)
  public String getCustomer3Impacted() {
    return this.customer3Impacted;
  }

  public void setCustomer3Impacted(String customer3Impacted) {
    this.customer3Impacted = customer3Impacted;
  }

  @Column(name = "customer4_impacted", length = 250)
  public String getCustomer4Impacted() {
    return this.customer4Impacted;
  }

  public void setCustomer4Impacted(String customer4Impacted) {
    this.customer4Impacted = customer4Impacted;
  }

  @Column(name = "main_category_of_fault", length = 250)
  public String getMainCategoryOfFault() {
    return this.mainCategoryOfFault;
  }

  public void setMainCategoryOfFault(String mainCategoryOfFault) {
    this.mainCategoryOfFault = mainCategoryOfFault;
  }

  @Column(name = "equipment_component_caused_fault", length = 250)
  public String getEquipmentComponentCausedFault() {
    return this.equipmentComponentCausedFault;
  }

  public void setEquipmentComponentCausedFault(String equipmentComponentCausedFault) {
    this.equipmentComponentCausedFault = equipmentComponentCausedFault;
  }

  @Column(name = "equipment_component_repaired", length = 250)
  public String getEquipmentComponentRepaired() {
    return this.equipmentComponentRepaired;
  }

  public void setEquipmentComponentRepaired(String equipmentComponentRepaired) {
    this.equipmentComponentRepaired = equipmentComponentRepaired;
  }

  @Column(name = "equipment_component_replaced", length = 250)
  public String getEquipmentComponentReplaced() {
    return this.equipmentComponentReplaced;
  }

  public void setEquipmentComponentReplaced(String equipmentComponentReplaced) {
    this.equipmentComponentReplaced = equipmentComponentReplaced;
  }

  @Column(name = "fix_resolution_temporary_or_permanent", length = 250)
  public String getFixResolutionTemporaryOrPermanent() {
    return this.fixResolutionTemporaryOrPermanent;
  }

  public void setFixResolutionTemporaryOrPermanent(String fixResolutionTemporaryOrPermanent) {
    this.fixResolutionTemporaryOrPermanent = fixResolutionTemporaryOrPermanent;
  }

  @Column(name = "actions_required_for_permanent_resolution", length = 250)
  public String getActionsRequiredForPermanentResolution() {
    return this.actionsRequiredForPermanentResolution;
  }

  public void setActionsRequiredForPermanentResolution(String actionsRequiredForPermanentResolution) {
    this.actionsRequiredForPermanentResolution = actionsRequiredForPermanentResolution;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, length = 19)
  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessCode == null) ? 0 : accessCode.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((site == null) ? 0 : site.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CallOutVisit other = (CallOutVisit) obj;
    if (accessCode == null) {
      if (other.accessCode != null)
        return false;
    } else if (!accessCode.equals(other.accessCode))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (site == null) {
      if (other.site != null)
        return false;
    } else if (!site.equals(other.site))
      return false;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CallOutVisit [");
    if (id != null)
      builder.append("id=").append(id).append(", ");
    if (version != null)
      builder.append("version=").append(version).append(", ");
    if (user != null)
      builder.append("user=").append(user).append(", ");
    if (site != null)
      builder.append("site=").append(site).append(", ");
    if (accessCode != null)
      builder.append("accessCode=").append(accessCode).append(", ");
    if (callOutCsrOrTtNumber != null)
      builder.append("callOutCsrOrTtNumber=").append(callOutCsrOrTtNumber).append(", ");
    if (timeComplainReceived != null)
      builder.append("timeComplainReceived=").append(timeComplainReceived).append(", ");
    if (timeReachedToSite != null)
      builder.append("timeReachedToSite=").append(timeReachedToSite).append(", ");
    if (timeFaultReserved != null)
      builder.append("timeFaultReserved=").append(timeFaultReserved).append(", ");
    if (customer1Impacted != null)
      builder.append("customer1Impacted=").append(customer1Impacted).append(", ");
    if (customer2Impacted != null)
      builder.append("customer2Impacted=").append(customer2Impacted).append(", ");
    if (customer3Impacted != null)
      builder.append("customer3Impacted=").append(customer3Impacted).append(", ");
    if (customer4Impacted != null)
      builder.append("customer4Impacted=").append(customer4Impacted).append(", ");
    if (mainCategoryOfFault != null)
      builder.append("mainCategoryOfFault=").append(mainCategoryOfFault).append(", ");
    if (equipmentComponentCausedFault != null)
      builder.append("equipmentComponentCausedFault=").append(equipmentComponentCausedFault).append(", ");
    if (equipmentComponentRepaired != null)
      builder.append("equipmentComponentRepaired=").append(equipmentComponentRepaired).append(", ");
    if (equipmentComponentReplaced != null)
      builder.append("equipmentComponentReplaced=").append(equipmentComponentReplaced).append(", ");
    if (fixResolutionTemporaryOrPermanent != null)
      builder.append("fixResolutionTemporaryOrPermanent=").append(fixResolutionTemporaryOrPermanent).append(", ");
    if (actionsRequiredForPermanentResolution != null)
      builder.append("actionsRequiredForPermanentResolution=").append(actionsRequiredForPermanentResolution)
        .append(", ");
    if (createdAt != null)
      builder.append("createdAt=").append(createdAt);
    builder.append("]");
    return builder.toString();
  }
  
  

}
