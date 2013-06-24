package com.telenoetica.web.http.session.management;

import javax.servlet.http.HttpServletRequest;

import com.telenoetica.jpa.entities.User;

public interface LogoutEventPublisher {

  public abstract void publish(HttpServletRequest request,User user);

}