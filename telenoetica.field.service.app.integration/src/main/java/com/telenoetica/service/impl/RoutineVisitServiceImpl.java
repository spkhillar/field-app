package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.RoutineVisitDAO;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.util.ApplicationServiceException;

@Service("routineVisitService")
@Transactional
public class RoutineVisitServiceImpl implements RoutineVisitService {

	@Autowired
	private RoutineVisitDAO routineVisitDAO;

	@Autowired
	private SiteService siteService;

	@Autowired
	private UserService userService;

	private static final int PAGE_SIZE = 50;

	@Override
	public RoutineVisit retrieve(Long id) {
		return routineVisitDAO.findOne(id);
	}

	@Override
	public RoutineVisit saveOrUpdate(RoutineVisit routineVisit) {

		if(routineVisit.getUser() == null){
			User user = userService.retrieve(1L);
			routineVisit.setUser(user);
		}

		if(routineVisit.getSite() == null){
			Site site = siteService.findSite(routineVisit.getSiteId());
			if(site == null){
				throw new ApplicationServiceException("Site \""+ routineVisit.getSiteId() +"\" not found in system");
			}
		}else{
			throw new ApplicationServiceException("Site is required for creating a Routine Visit");
		}

		return routineVisitDAO.save(routineVisit);
	}

	@Override
	public void delete(RoutineVisit baseEntity) {
		routineVisitDAO.delete(baseEntity);

	}

	@Override
	public Page<RoutineVisit> getVisits(Integer pageNumber) {

		PageRequest request =
				new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
		return routineVisitDAO.findAll(request);
	}

	@Override
	public List<RoutineVisit> getVisits() {
		return routineVisitDAO.findAll();
	}

}
