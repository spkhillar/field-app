package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.CallOutVisitDAO;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.util.ApplicationServiceException;

@Service("callOutVisitService")
public class CallOutVisitServiceImpl implements CallOutVisitService {

	@Autowired
	private CallOutVisitDAO callOutVisitDAO;

	@Autowired
	private SiteService siteService;

	@Autowired
	private UserService userService;

	private static final int PAGE_SIZE = 50;

	@Transactional
	public CallOutVisit retrieve(Long id) {
		return this.callOutVisitDAO.findOne(id);
	}

	@Transactional
	public CallOutVisit saveOrUpdate(CallOutVisit callOutVisit) {
		if (callOutVisit.getUser() == null) {
			User user = userService.retrieve(1L);
			callOutVisit.setUser(user);
		}

		if (callOutVisit.getSite() == null) {
			Site site = siteService.findSite(callOutVisit.getSiteId());
			if (site == null) {
				throw new ApplicationServiceException("Site \""
						+ callOutVisit.getSiteId() + "\" not found in system");
			}
			callOutVisit.setSite(site);
		} else {
			throw new ApplicationServiceException(
					"Site is required for creating a Routine Visit");
		}
		return this.callOutVisitDAO.save(callOutVisit);
	}

	@Transactional
	public void delete(CallOutVisit callOutVisit) {
		this.callOutVisitDAO.delete(callOutVisit);
	}

	@Transactional
	public Page<CallOutVisit> getCallOutVisits(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				Sort.Direction.DESC, "createdAt");
		return callOutVisitDAO.findAll(request);
	}

}
