package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

}
