package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.UserDAO;
import com.telenoetica.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;
  
  public User retrieve(Long id) {
    return userDAO.findOne(id);
  }

  public User saveOrUpdate(User baseEntity) {
    return null;
  }

  public void delete(User baseEntity) {

  }

}
