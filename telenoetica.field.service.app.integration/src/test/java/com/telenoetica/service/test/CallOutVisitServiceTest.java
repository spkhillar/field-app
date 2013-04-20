package com.telenoetica.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.telenoetica.jpa.test.BaseTest;
import com.telenoetica.service.CallOutVisitService;

@ContextConfiguration(locations = {
		"classpath:applicationContext-service.xml" },inheritLocations=true)
public class CallOutVisitServiceTest extends BaseTest{

	@Autowired
	private CallOutVisitService callOutVisitService;
	
	@Test
	public void test(){
		callOutVisitService.retrieve(1L);
	} 
}
