package com.telenoetica.service;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.CallOutVisit;

public interface CallOutVisitService extends BaseService<CallOutVisit>{
	
	public Page<CallOutVisit> getCallOutVisits(Integer pageNumber);
}
