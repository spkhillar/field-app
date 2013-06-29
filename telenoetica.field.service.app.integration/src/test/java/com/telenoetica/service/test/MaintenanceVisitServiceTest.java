package com.telenoetica.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.service.MaintenanceVisitService;

public class MaintenanceVisitServiceTest extends BaseServiceTest {

	@Autowired
	private MaintenanceVisitService maintenanceVisitService;

	@Test
	public void testVisit() {
		maintenanceVisitService.getVisits();
	}

}
