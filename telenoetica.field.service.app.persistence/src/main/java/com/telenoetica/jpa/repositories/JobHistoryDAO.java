package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.JobHistory;

public interface JobHistoryDAO extends JpaRepository<JobHistory, Long> {

}
