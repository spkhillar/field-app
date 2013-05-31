package com.telenoetica.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.service.DieselVisitService;

public class DieselVisitServiceTest extends BaseServiceTest {

  @Autowired
  private DieselVisitService dieselVisitService;
  
  @Test
  public void testVisit(){
    dieselVisitService.getVisits();
  }
  
}
