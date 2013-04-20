/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RolePermission
 */
@Entity
@Table(name = "role_permission")
public class RolePermission implements java.io.Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 7549364120469444471L;
  private Long id;
  private Permission permission;
  private Role role;

  public RolePermission() {}

  public RolePermission(Permission permission, Role role) {
    this.permission = permission;
    this.role = role;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_id")
  public Permission getPermission() {
    return this.permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
