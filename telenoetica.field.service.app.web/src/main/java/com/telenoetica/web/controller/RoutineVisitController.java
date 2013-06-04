package com.telenoetica.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.web.rest.RestResponse;

@Controller
@RequestMapping(value = "/routine")
@SessionAttributes("routineForm")
public class RoutineVisitController extends BaseController{

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
		return "Saved Successfuly with id:" + savedRoutineVisit.getId();
	}

	@RequestMapping(value = "/new")
	public String create() {
		return "routine.new";
	}

	@RequestMapping(value = "/rest", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	@ResponseBody
	public RestResponse saveApi(@RequestBody RoutineVisit routineVisit) {
		RoutineVisit savedRoutineVisit = routineVisitService.saveOrUpdate(routineVisit);
		RestResponse response = new RestResponse(0, "Saved Successfuly with id:" + savedRoutineVisit.getId());
		return response;
	}

	@RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public RoutineVisit saveApi(@PathVariable Long id) {
		RoutineVisit routineVisit = routineVisitService.retrieve(id);
		return routineVisit;
	}

}
