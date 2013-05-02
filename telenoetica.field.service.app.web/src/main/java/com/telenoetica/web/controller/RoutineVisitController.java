package com.telenoetica.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.service.RoutineVisitService;

@Controller
@RequestMapping(value = "/routine")

@SessionAttributes("routineForm")
public class RoutineVisitController {

  @Autowired
  private RoutineVisitService routineVisitService;
  
  @ModelAttribute("routineForm")
  public RoutineVisit createFormBean() {
    return new RoutineVisit();
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public String save(RoutineVisit routineVisit) {
    RoutineVisit savedRoutineVisit = routineVisitService.saveOrUpdate(routineVisit);
    return "Saved Successfuly with id:"+savedRoutineVisit.getId();
  }

  @RequestMapping(value = "/create")
  public String create() {
    return "routine.create";
  }

}
