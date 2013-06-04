/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telenoetica.jpa.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {

  Page<User> findByUserNameLike(String userName, Pageable pageable);
  Page<User> findByFirstNameLike(String firstName, Pageable pageable);
  Page<User> findByLastNameLike(String lastName, Pageable pageable);
  Page<User> findByEmailLike(String email, Pageable pageable);
  Page<User> findByEnabled(Boolean enabled, Pageable pageable);
  
  
  @Query("select u from User u where u.userRole.role.id = :role")
  Page<User> findByRole(@Param("role") Long role, Pageable pageable);
  
}
