package com.telenoetica.jpa.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.telenoetica.jpa.repositories.CallOutVisitDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-jpa-config.xml")
public class Test {

	@Autowired
	private CallOutVisitDAO callOutVisitDAO;
	
	@org.junit.Test
	public void test1(){
		System.out.println("..Hello...");
		callOutVisitDAO.count();
		
	}
	
}
