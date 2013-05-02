package com.telenoetica.service;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.RoutineVisit;

public interface RoutineVisitService extends BaseService<RoutineVisit>{
	
	public Page<RoutineVisit> getCallOutVisits(Integer pageNumber);
}
