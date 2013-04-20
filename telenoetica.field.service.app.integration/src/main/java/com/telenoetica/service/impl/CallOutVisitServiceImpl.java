package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.repositories.CallOutVisitDAO;
import com.telenoetica.service.CallOutVisitService;

@Service("callOutVisitService")
public class CallOutVisitServiceImpl implements CallOutVisitService {
	
	@Autowired
	private CallOutVisitDAO callOutVisitDAO;

	@Transactional
	public CallOutVisit retrieve(Long id) {
		this.callOutVisitDAO.findOne(id);
		return null;
	}
	
	@Transactional
	public CallOutVisit saveAndUpdate(CallOutVisit callOutVisit) {
		return this.callOutVisitDAO.save(callOutVisit);
	}
	
	@Transactional
	public void delete(CallOutVisit callOutVisit) {
		this.callOutVisitDAO.delete(callOutVisit);
	}

}
