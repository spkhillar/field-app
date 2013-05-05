package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

}
