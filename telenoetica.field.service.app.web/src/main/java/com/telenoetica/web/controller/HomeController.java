package com.telenoetica.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/routine", method = RequestMethod.GET)
	@ResponseBody
	public CallOutVisit getRoutineVisit(){
	  User user = new User();
	  Site site = new Site();
	  
	  user.setEmail("shivprasad.khillar@gmial.com");
	  user.setEnabled(true);
	  user.setFirstName("Shiv");
	  user.setLastName("khillar");
	  user.setUsername("spkhillar");
	  user.setId(1L);
	  
	  site.setName("Bellandur");
	  site.setId(2L);
	  
	 CallOutVisit callOutVisit = new CallOutVisit();
	 callOutVisit.setAccessCode("AAA");
	 
   callOutVisit.setUser(user);
   callOutVisit.setSite(site);
   callOutVisit.setTimeComplainReceived(new Date());
   callOutVisit.setTimeFaultReserved(new Date());
   callOutVisit.setTimeReachedToSite(new Date());
	  
	  return callOutVisit;
	}
	
	
	
}
