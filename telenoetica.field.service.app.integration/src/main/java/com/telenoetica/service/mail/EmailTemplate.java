package com.telenoetica.service.mail;

import java.util.List;

public class EmailTemplate {

  private List<String> to;

  private List<String> cc;

  private List<String> bcc;

  private String text;

  private String subject;

  private String attachmentFileName;

  public EmailTemplate() {}

  public EmailTemplate(List<String> to, String text, String subject) {
    super();
    this.to = to;
    this.text = text;
    this.subject = subject;
  }

  public EmailTemplate(List<String> to, List<String> cc, List<String> bcc, String text, String subject,
      String attachmentFileName) {
    super();
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.text = text;
    this.subject = subject;
    this.attachmentFileName = attachmentFileName;
  }

  public EmailTemplate(List<String> to, String text, String subject, String attachmentFileName) {
    super();
    this.to = to;
    this.text = text;
    this.subject = subject;
    this.attachmentFileName = attachmentFileName;
  }

  public List<String> getTo() {
    return to;
  }

  public void setTo(List<String> to) {
    this.to = to;
  }

  public List<String> getCc() {
    return cc;
  }

  public void setCc(List<String> cc) {
    this.cc = cc;
  }

  public List<String> getBcc() {
    return bcc;
  }

  public void setBcc(List<String> bcc) {
    this.bcc = bcc;
  }

  public String getAttachmentFileName() {
    return attachmentFileName;
  }

  public void setAttachmentFileName(String attachmentFileName) {
    this.attachmentFileName = attachmentFileName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

}
