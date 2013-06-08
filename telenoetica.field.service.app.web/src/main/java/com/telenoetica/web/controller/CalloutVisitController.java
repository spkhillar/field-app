package com.telenoetica.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.web.rest.RestResponse;

@Controller
@RequestMapping(value = "/callout")
@SessionAttributes("calloutForm")
public class CalloutVisitController {
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), true));
	    }

	@Autowired
	private CallOutVisitService callOutVisitService;

	@ModelAttribute("calloutForm")
	public CallOutVisit createFormBean() {
		return new CallOutVisit();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(CallOutVisit callOutVisit) {
		CallOutVisit savedCallOutVisit = callOutVisitService
				.saveOrUpdate(callOutVisit);
		return "Saved Successfuly with id:" + savedCallOutVisit.getId();
	}

	@RequestMapping(value = "/new")
	public String create() {
		return "callout.new";
	}

	@RequestMapping(value = "/rest", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	@ResponseBody
	public RestResponse saveApi(@RequestBody CallOutVisit callOutVisit) {
		CallOutVisit savedCallOutVisit = callOutVisitService
				.saveOrUpdate(callOutVisit);
		RestResponse response = new RestResponse(0,
				"Saved Successfuly with id:" + savedCallOutVisit.getId());
		return response;
	}

	@RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public CallOutVisit saveApi(@PathVariable Long id) {
		CallOutVisit callOutVisit = callOutVisitService.retrieve(id);
		return callOutVisit;
	}

}
