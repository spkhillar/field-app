/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * User
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 3228774824582399072L;
  private Long id;
  private Integer version;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private Boolean enabled;
  private Date createdAt;
  private Set<UserRole> userRoles = new HashSet<UserRole>(0);

  /*
   * private Set callOutVisits = new HashSet(0); private Set routineVisits = new
   * HashSet(0); private Set maintenanceVisits = new HashSet(0); private Set
   * dieselVisits = new HashSet(0);
   */

  public User() {}

  public User(Date createdAt) {
    this.createdAt = createdAt;
  }

  public User(String username, String password, String firstName, String lastName, String email, Boolean enabled,
      Date createdAt, Set<UserRole> userRoles/*
                                              * ,Set callOutVisits, Set
                                              * routineVisits, Set
                                              * maintenanceVisits, Set
                                              * dieselVisits
                                              */) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.enabled = enabled;
    this.createdAt = createdAt;
    this.userRoles = userRoles;
    /*
     * this.maintenanceVisits = maintenanceVisits; this.dieselVisits =
     * dieselVisits; this.callOutVisits = callOutVisits; this.routineVisits =
     * routineVisits;
     */
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

  @Column(name = "username", length = 250)
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password", length = 250)
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "first_name", length = 250)
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", length = 250)
  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "email", length = 250)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "enabled")
  public Boolean getEnabled() {
    return this.enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, length = 19)
  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  public Set<UserRole> getUserRoles() {
    return this.userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }
  /*
   * @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") public Set
   * getCallOutVisits() { return this.callOutVisits; }
   * 
   * public void setCallOutVisits(Set callOutVisits) { this.callOutVisits =
   * callOutVisits; }
   * 
   * @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") public Set
   * getRoutineVisits() { return this.routineVisits; }
   * 
   * public void setRoutineVisits(Set routineVisits) { this.routineVisits =
   * routineVisits; }
   * 
   * @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") public Set
   * getMaintenanceVisits() { return this.maintenanceVisits; }
   * 
   * public void setMaintenanceVisits(Set maintenanceVisits) {
   * this.maintenanceVisits = maintenanceVisits; }
   * 
   * @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") public Set
   * getDieselVisits() { return this.dieselVisits; }
   * 
   * public void setDieselVisits(Set dieselVisits) { this.dieselVisits =
   * dieselVisits; }
   */

  
  
  @Override
  public String toString() {
    final int maxLen = 10;
    StringBuilder builder = new StringBuilder();
    builder.append("User [");
    if (id != null)
      builder.append("id=").append(id).append(", ");
    if (version != null)
      builder.append("version=").append(version).append(", ");
    if (username != null)
      builder.append("username=").append(username).append(", ");
    if (password != null)
      builder.append("password=").append(password).append(", ");
    if (firstName != null)
      builder.append("firstName=").append(firstName).append(", ");
    if (lastName != null)
      builder.append("lastName=").append(lastName).append(", ");
    if (email != null)
      builder.append("email=").append(email).append(", ");
    if (enabled != null)
      builder.append("enabled=").append(enabled).append(", ");
    if (createdAt != null)
      builder.append("createdAt=").append(createdAt).append(", ");
    if (userRoles != null)
      builder.append("userRoles=").append(toString(userRoles, maxLen));
    builder.append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    User other = (User) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

  private String toString(Collection<?> collection, int maxLen) {
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    int i = 0;
    for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
      if (i > 0)
        builder.append(", ");
      builder.append(iterator.next());
    }
    builder.append("]");
    return builder.toString();
  }
  
  
}
