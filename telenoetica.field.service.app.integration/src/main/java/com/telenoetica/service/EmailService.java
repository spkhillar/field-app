package com.telenoetica.service;

import com.telenoetica.service.mail.EmailTemplate;

public interface EmailService {

  public void sendEmail(EmailTemplate emailTemplate);
  
}
