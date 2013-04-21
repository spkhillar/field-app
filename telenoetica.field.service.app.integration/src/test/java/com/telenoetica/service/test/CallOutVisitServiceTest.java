package com.telenoetica.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.SiteDAO;
import com.telenoetica.jpa.repositories.UserDAO;
import com.telenoetica.jpa.test.BaseTest;
import com.telenoetica.service.CallOutVisitService;

@ContextConfiguration(locations = {
		"classpath:applicationContext-service.xml" },inheritLocations=true)
public class CallOutVisitServiceTest extends BaseTest{

	@Autowired
	private CallOutVisitService callOutVisitService;
	
	@Autowired
	private SiteDAO siteDao;
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void test(){
	  
	  User user = userDAO.findOne(1L);
	  Site site = siteDao.findOne(1L);
	  
	  CallOutVisit callOutVisit = new CallOutVisit();
	  callOutVisit.setUser(user);
	  callOutVisit.setSite(site);
	  callOutVisit.setTimeComplainReceived(new Date());
	  callOutVisit.setTimeFaultReserved(new Date());
	  callOutVisit.setTimeReachedToSite(new Date());
	  
	  callOutVisit = callOutVisitService.saveAndUpdate(callOutVisit);
	  
	  System.err.println("...Saved..."+callOutVisit);
	  
	  callOutVisit.setAccessCode("BBBBB");
	  
	  callOutVisitService.saveAndUpdate(callOutVisit);
	  
	  callOutVisit = callOutVisitService.retrieve(callOutVisit.getId());
	  

    
    System.err.println("...Retrieved..."+callOutVisit);
	} 
}
