/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Class AccessController.
 * 
 * @author Shiv Prasad Khillar
 */
@Controller
public class AccessController {

	/**
	 * Login.
	 * 
	 * @param model
	 *            the model
	 * @param message
	 *            the message
	 * @return the string
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(required = false) String message) {
		model.addAttribute("message", message);
		return "login";
	}

	/**
	 * Denied.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied() {
		return "denied";
	}

	/**
	 * Login failure.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/login/failure", method = RequestMethod.GET)
	public String loginFailure(Model model) {
		String message = "Login Failure! Invalid User Id or Password.";
		model.addAttribute("message", message);
		return "redirect:/login";
	}

	/**
	 * Logout success.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/logout/success", method = RequestMethod.GET)
	public String logoutSuccess(Model model) {
		String message = "Loggedout Successfully.";
		model.addAttribute("message", message);
		return "redirect:/login";
	}

	/**
	 * Logout session time out.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/logout/session", method = RequestMethod.GET)
	public String logoutSessionTimeOut(Model model) {
		String message = "Loggedout as Session had time out.";
		model.addAttribute("message", message);
		return "redirect:/login";
	}

	/**
	 * Session time out.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/sessiontimeout", method = RequestMethod.GET)
	public String sessionTimeOut() {
		return "sessiontimeout";
	}
}