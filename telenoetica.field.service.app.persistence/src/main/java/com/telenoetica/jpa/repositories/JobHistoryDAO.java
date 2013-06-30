/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.JobHistory;

/**
 * The Interface JobHistoryDAO.
 * 
 * @author Shiv Prasad Khillar
 */
public interface JobHistoryDAO extends JpaRepository<JobHistory, Long> {

	List<JobHistory> findByStartTimeBetween(Date startDate, Date endDate);

	JobHistory findById(long id);

}
