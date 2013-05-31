/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.entities.Site;

public interface FaultDAO extends JpaRepository<Fault, Long> {

}
