package com.telenoetica.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.RoutineVisit;

public interface RoutineVisitService extends BaseService<RoutineVisit>{
	
	public Page<RoutineVisit> getVisits(Integer pageNumber);
	
	List<RoutineVisit> getVisits();
	
}
