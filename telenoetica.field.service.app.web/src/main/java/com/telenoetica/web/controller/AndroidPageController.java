package com.telenoetica.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/androidDownload")
public class AndroidPageController extends BaseController {

	/**
	 * Creates the.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/appexe")
	public String create() {
		return "androidDownload.appexe";
	}

}
