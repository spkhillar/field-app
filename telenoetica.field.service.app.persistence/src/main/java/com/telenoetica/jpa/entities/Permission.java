package com.telenoetica.jpa.entities;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Permission 
 */
@Entity
@Table(name = "permission", catalog = "field_service_db")
public class Permission implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3939120811223951025L;
	private Long id;
	private String name;
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

	public Permission() {
	}

	public Permission(String name, Set<RolePermission> rolePermissions) {
		this.name = name;
		this.rolePermissions = rolePermissions;
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

	@Column(name = "name", length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

}
