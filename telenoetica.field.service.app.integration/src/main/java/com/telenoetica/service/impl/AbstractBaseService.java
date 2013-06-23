package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.util.ApplicationServiceException;

public abstract class AbstractBaseService {

	@Autowired
	protected UserService userService;

	@Autowired
	protected SiteService siteService;

	/**
	 * @param username
	 * @return
	 */
	public User getUser(final String username) {
		User user = userService.findByUserName(username);
		if (user == null) {
			throw new ApplicationServiceException("User " + username
					+ "not found in system");
		}
		return user;
	}

	/**
	 * @param siteName
	 * @return
	 */
	public Site getSite(final String siteName) {
		Site site = siteService.findSite(siteName);
		if (site == null) {
			throw new ApplicationServiceException("Site \"" + siteName
					+ "\" not found in system");
		}
		return site;
	}

}
