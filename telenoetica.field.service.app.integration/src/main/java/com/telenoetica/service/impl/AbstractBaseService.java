/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.util.ApplicationServiceException;

/**
 * The Class AbstractBaseService.
 */
public abstract class AbstractBaseService {

  protected static final Logger logger = Logger.getLogger(AbstractBaseService.class);

  /** The user service. */
  @Autowired
  protected UserService userService;

  /** The site service. */
  @Autowired
  protected SiteService siteService;

  /**
   * Gets the user.
   *
   * @param username the username
   * @return the user
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
   * Gets the site.
   *
   * @param siteName the site name
   * @return the site
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
