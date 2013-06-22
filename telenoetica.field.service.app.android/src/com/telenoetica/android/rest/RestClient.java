package com.telenoetica.android.rest;

import java.util.Collections;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public enum RestClient {

  INSTANCE;

  public <T> T executeRest(final String url, final String userName, final String password,
      final HttpMethod httpMethod, final Object requestObject, final Class<T> returnType,
      final MediaType contentType) {
    HttpAuthentication authHeader = new HttpBasicAuthentication(userName,
      password);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setAuthorization(authHeader);
    requestHeaders.setAccept(Collections
      .singletonList(MediaType.APPLICATION_JSON));

    // Create a new RestTemplate instance
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().add(
      new MappingJacksonHttpMessageConverter());
    ResponseEntity<T> response = null;
    if(contentType != null){
      requestHeaders.setContentType(contentType);
    }
    try {
      response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(requestObject,requestHeaders), returnType);
    } catch (RestClientException e) {
      e.printStackTrace();
      System.err.println( "I failed in post."+e.getMessage());
    }
    return response.getBody();
  }
}
