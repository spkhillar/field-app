package com.telenoetica.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.jpa.entities.RoutineVisit;

@Controller
@RequestMapping(value="/routine")
public class RoutineVisitController {
  
  @RequestMapping(value="/save",method = RequestMethod.POST,headers={"Accept=application/json"},consumes = {
"application/json"})
  @ResponseBody
  public String save( @RequestBody RoutineVisit routineVisit){
    return "sucess";
  }
  @RequestMapping(value="/create")
  public String create(){
    return "routine.create";
  }

}
