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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CallOutVisit.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "call_out_visit")
@JsonAutoDetect(JsonMethod.NONE)
public class CallOutVisit implements BaseEntity, java.io.Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1043305759290140709L;
  
  /** The id. */
  @JsonProperty
  private Long id;
  
  /** The version. */
  private Integer version;
  
  /** The user. */
  @JsonProperty
  private User user;
  
  /** The site. */
  @JsonProperty
  private Site site;
  
  /** The access code. */
  @JsonProperty
  private String accessCode;
  
  /** The call out csr or tt number. */
  @JsonProperty
  private String callOutCsrOrTtNumber;
  
  /** The time complain received. */
  @JsonProperty
  private Date timeComplainReceived;
  
  /** The time reached to site. */
  @JsonProperty
  private Date timeReachedToSite;
  
  /** The time fault reserved. */
  @JsonProperty
  private Date timeFaultResolved;
  
  /** The customer1 impacted. */
  @JsonProperty
  private String customer1Impacted;
  
  /** The customer2 impacted. */
  @JsonProperty
  private String customer2Impacted;
  
  /** The customer3 impacted. */
  @JsonProperty
  private String customer3Impacted;
  
  /** The customer4 impacted. */
  @JsonProperty
  private String customer4Impacted;
  
  /** The main category of fault. */
  @JsonProperty
  private String mainCategoryOfFault;
  
  /** The equipment component caused fault. */
  @JsonProperty
  private String equipmentComponentCausedFault;
  
  /** The equipment component repaired. */
  @JsonProperty
  private String equipmentComponentRepaired;
  
  /** The equipment component replaced. */
  @JsonProperty
  private String equipmentComponentReplaced;
  
  /** The fix resolution temporary or permanent. */
  @JsonProperty
  private String fixResolutionTemporaryOrPermanent;
  
  /** The actions required for permanent resolution. */
  @JsonProperty
  private String actionsRequiredForPermanentResolution;
  
  /** The created at. */
  @JsonProperty
  private Date createdAt;
  
  @JsonProperty
  private String siteId;
  
  /** The user id. */
  @JsonProperty
  private String userId;

  /**
   * Instantiates a new call out visit.
   */
  public CallOutVisit() {}

  /**
   * Instantiates a new call out visit.
   *
   * @param timeComplainReceived the time complain received
   * @param timeReachedToSite the time reached to site
   * @param timeFaultResolved the time fault reserved
   * @param createdAt the created at
   */
  public CallOutVisit(Date timeComplainReceived, Date timeReachedToSite, Date timeFaultResolved, Date createdAt) {
    this.timeComplainReceived = timeComplainReceived;
    this.timeReachedToSite = timeReachedToSite;
    this.timeFaultResolved = timeFaultResolved;
    this.createdAt = createdAt;
  }

  /**
   * Instantiates a new call out visit.
   *
   * @param user the user
   * @param site the site
   * @param accessCode the access code
   * @param callOutCsrOrTtNumber the call out csr or tt number
   * @param timeComplainReceived the time complain received
   * @param timeReachedToSite the time reached to site
   * @param timeFaultResolved the time fault reserved
   * @param customer1Impacted the customer1 impacted
   * @param customer2Impacted the customer2 impacted
   * @param customer3Impacted the customer3 impacted
   * @param customer4Impacted the customer4 impacted
   * @param mainCategoryOfFault the main category of fault
   * @param equipmentComponentCausedFault the equipment component caused fault
   * @param equipmentComponentRepaired the equipment component repaired
   * @param equipmentComponentReplaced the equipment component replaced
   * @param fixResolutionTemporaryOrPermanent the fix resolution temporary or permanent
   * @param actionsRequiredForPermanentResolution the actions required for permanent resolution
   * @param createdAt the created at
   */
  public CallOutVisit(User user, Site site, String accessCode, String callOutCsrOrTtNumber, Date timeComplainReceived,
      Date timeReachedToSite, Date timeFaultResolved, String customer1Impacted, String customer2Impacted,
      String customer3Impacted, String customer4Impacted, String mainCategoryOfFault,
      String equipmentComponentCausedFault, String equipmentComponentRepaired, String equipmentComponentReplaced,
      String fixResolutionTemporaryOrPermanent, String actionsRequiredForPermanentResolution, Date createdAt) {
    this.user = user;
    this.site = site;
    this.accessCode = accessCode;
    this.callOutCsrOrTtNumber = callOutCsrOrTtNumber;
    this.timeComplainReceived = timeComplainReceived;
    this.timeReachedToSite = timeReachedToSite;
    this.timeFaultResolved = timeFaultResolved;
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
		if (this.user != null) {
			userId = this.user.getUserName();
		}
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
		if (this.site != null) {
			this.siteId = this.site.getName();
		}
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
   * Gets the call out csr or tt number.
   *
   * @return the call out csr or tt number
   */
  @Column(name = "call_out_csr_or_tt_number", length = 250)
  public String getCallOutCsrOrTtNumber() {
    return this.callOutCsrOrTtNumber;
  }

  /**
   * Sets the call out csr or tt number.
   *
   * @param callOutCsrOrTtNumber the new call out csr or tt number
   */
  public void setCallOutCsrOrTtNumber(String callOutCsrOrTtNumber) {
    this.callOutCsrOrTtNumber = callOutCsrOrTtNumber;
  }

  /**
   * Gets the time complain received.
   *
   * @return the time complain received
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_complain_received", nullable = false, length = 19)
  public Date getTimeComplainReceived() {
    return this.timeComplainReceived;
  }

  /**
   * Sets the time complain received.
   *
   * @param timeComplainReceived the new time complain received
   */
  public void setTimeComplainReceived(Date timeComplainReceived) {
    this.timeComplainReceived = timeComplainReceived;
  }

  /**
   * Gets the time reached to site.
   *
   * @return the time reached to site
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_reached_to_site", nullable = false, length = 19)
  public Date getTimeReachedToSite() {
    return this.timeReachedToSite;
  }

  /**
   * Sets the time reached to site.
   *
   * @param timeReachedToSite the new time reached to site
   */
  public void setTimeReachedToSite(Date timeReachedToSite) {
    this.timeReachedToSite = timeReachedToSite;
  }

  /**
   * Gets the time fault reserved.
   *
   * @return the time fault reserved
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time_fault_reserved", nullable = false, length = 19)
  public Date gettimeFaultResolved() {
    return this.timeFaultResolved;
  }

  /**
   * Sets the time fault reserved.
   *
   * @param timeFaultResolved the new time fault reserved
   */
  public void settimeFaultResolved(Date timeFaultResolved) {
    this.timeFaultResolved = timeFaultResolved;
  }

  /**
   * Gets the customer1 impacted.
   *
   * @return the customer1 impacted
   */
  @Column(name = "customer1_impacted", length = 250)
  public String getCustomer1Impacted() {
    return this.customer1Impacted;
  }

  /**
   * Sets the customer1 impacted.
   *
   * @param customer1Impacted the new customer1 impacted
   */
  public void setCustomer1Impacted(String customer1Impacted) {
    this.customer1Impacted = customer1Impacted;
  }

  /**
   * Gets the customer2 impacted.
   *
   * @return the customer2 impacted
   */
  @Column(name = "customer2_impacted", length = 250)
  public String getCustomer2Impacted() {
    return this.customer2Impacted;
  }

  /**
   * Sets the customer2 impacted.
   *
   * @param customer2Impacted the new customer2 impacted
   */
  public void setCustomer2Impacted(String customer2Impacted) {
    this.customer2Impacted = customer2Impacted;
  }

  /**
   * Gets the customer3 impacted.
   *
   * @return the customer3 impacted
   */
  @Column(name = "customer3_impacted", length = 250)
  public String getCustomer3Impacted() {
    return this.customer3Impacted;
  }

  /**
   * Sets the customer3 impacted.
   *
   * @param customer3Impacted the new customer3 impacted
   */
  public void setCustomer3Impacted(String customer3Impacted) {
    this.customer3Impacted = customer3Impacted;
  }

  /**
   * Gets the customer4 impacted.
   *
   * @return the customer4 impacted
   */
  @Column(name = "customer4_impacted", length = 250)
  public String getCustomer4Impacted() {
    return this.customer4Impacted;
  }

  /**
   * Sets the customer4 impacted.
   *
   * @param customer4Impacted the new customer4 impacted
   */
  public void setCustomer4Impacted(String customer4Impacted) {
    this.customer4Impacted = customer4Impacted;
  }

  /**
   * Gets the main category of fault.
   *
   * @return the main category of fault
   */
  @Column(name = "main_category_of_fault", length = 250)
  public String getMainCategoryOfFault() {
    return this.mainCategoryOfFault;
  }

  /**
   * Sets the main category of fault.
   *
   * @param mainCategoryOfFault the new main category of fault
   */
  public void setMainCategoryOfFault(String mainCategoryOfFault) {
    this.mainCategoryOfFault = mainCategoryOfFault;
  }

  /**
   * Gets the equipment component caused fault.
   *
   * @return the equipment component caused fault
   */
  @Column(name = "equipment_component_caused_fault", length = 250)
  public String getEquipmentComponentCausedFault() {
    return this.equipmentComponentCausedFault;
  }

  /**
   * Sets the equipment component caused fault.
   *
   * @param equipmentComponentCausedFault the new equipment component caused fault
   */
  public void setEquipmentComponentCausedFault(String equipmentComponentCausedFault) {
    this.equipmentComponentCausedFault = equipmentComponentCausedFault;
  }

  /**
   * Gets the equipment component repaired.
   *
   * @return the equipment component repaired
   */
  @Column(name = "equipment_component_repaired", length = 250)
  public String getEquipmentComponentRepaired() {
    return this.equipmentComponentRepaired;
  }

  /**
   * Sets the equipment component repaired.
   *
   * @param equipmentComponentRepaired the new equipment component repaired
   */
  public void setEquipmentComponentRepaired(String equipmentComponentRepaired) {
    this.equipmentComponentRepaired = equipmentComponentRepaired;
  }

  /**
   * Gets the equipment component replaced.
   *
   * @return the equipment component replaced
   */
  @Column(name = "equipment_component_replaced", length = 250)
  public String getEquipmentComponentReplaced() {
    return this.equipmentComponentReplaced;
  }

  /**
   * Sets the equipment component replaced.
   *
   * @param equipmentComponentReplaced the new equipment component replaced
   */
  public void setEquipmentComponentReplaced(String equipmentComponentReplaced) {
    this.equipmentComponentReplaced = equipmentComponentReplaced;
  }

  /**
   * Gets the fix resolution temporary or permanent.
   *
   * @return the fix resolution temporary or permanent
   */
  @Column(name = "fix_resolution_temporary_or_permanent", length = 250)
  public String getFixResolutionTemporaryOrPermanent() {
    return this.fixResolutionTemporaryOrPermanent;
  }

  /**
   * Sets the fix resolution temporary or permanent.
   *
   * @param fixResolutionTemporaryOrPermanent the new fix resolution temporary or permanent
   */
  public void setFixResolutionTemporaryOrPermanent(String fixResolutionTemporaryOrPermanent) {
    this.fixResolutionTemporaryOrPermanent = fixResolutionTemporaryOrPermanent;
  }

  /**
   * Gets the actions required for permanent resolution.
   *
   * @return the actions required for permanent resolution
   */
  @Column(name = "actions_required_for_permanent_resolution", length = 250)
  public String getActionsRequiredForPermanentResolution() {
    return this.actionsRequiredForPermanentResolution;
  }

  /**
   * Sets the actions required for permanent resolution.
   *
   * @param actionsRequiredForPermanentResolution the new actions required for permanent resolution
   */
  public void setActionsRequiredForPermanentResolution(String actionsRequiredForPermanentResolution) {
    this.actionsRequiredForPermanentResolution = actionsRequiredForPermanentResolution;
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
  
  @Transient
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	@Transient
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
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

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
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
    if (timeFaultResolved != null)
      builder.append("timeFaultResolved=").append(timeFaultResolved).append(", ");
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
