package com.telenoetica.web.http.session.management;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.telenoetica.jpa.entities.User;
import com.telenoetica.web.event.LogoutSuccessEvent;

public class LogoutEventPublisherImpl implements LogoutEventPublisher {

  private WebApplicationContext getWebApplicationContext(final HttpServletRequest request){
    return   WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
  }

  /**
   * 
   */
  @Override
  public void publish(final HttpServletRequest request,final User user) {
    WebApplicationContext appx = getWebApplicationContext(request);
    LogoutSuccessEvent event = new LogoutSuccessEvent(user);
    System.err.println(".....");
    appx.publishEvent(event);
  }

}
