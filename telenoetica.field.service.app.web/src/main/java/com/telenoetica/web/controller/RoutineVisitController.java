package com.telenoetica.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.RoutineVisit;

@Controller
@RequestMapping(value = "/routine")

@SessionAttributes("routineForm")
public class RoutineVisitController {

  
  @ModelAttribute("routineForm")
  public RoutineVisit createFormBean() {
    return new RoutineVisit();
  }
  
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public String save(RoutineVisit routineVisit) {
    return "sucess";
  }

  @RequestMapping(value = "/create")
  public String create() {
    return "routine.create";
  }

}
