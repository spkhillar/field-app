/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.web.rest.RestResponse;

/**
 * The Class BaseController.
 * 
 * @author Shiv Prasad Khillar
 */
public class BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	/**
	 * Handle internal service exception.
	 * 
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the rest response
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public RestResponse handleInternalServiceException(Exception ex,
			HttpServletRequest request) {
		logger.error("handleInternalServiceException-User-", ex);
		RestResponse restResponse = new RestResponse(500, ex.getMessage());
		return restResponse;
	}

}
