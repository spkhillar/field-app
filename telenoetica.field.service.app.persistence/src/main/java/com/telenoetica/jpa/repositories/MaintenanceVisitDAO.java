/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.MaintenanceVisit;

/**
 * The Interface MaintenanceVisitDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface MaintenanceVisitDAO extends JpaRepository<MaintenanceVisit, Long> {

}
