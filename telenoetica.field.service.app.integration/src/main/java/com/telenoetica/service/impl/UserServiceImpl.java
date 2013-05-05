package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.entities.UserRole;
import com.telenoetica.jpa.repositories.RoleDAO;
import com.telenoetica.jpa.repositories.UserDAO;
import com.telenoetica.jpa.repositories.UserRoleDAO;
import com.telenoetica.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;
  
  @Autowired
  private RoleDAO roleDAO;
  
  @Autowired
  private UserRoleDAO userRoleDAO;
  
  public User retrieve(Long id) {
    return userDAO.findOne(id);
  }

  public User saveOrUpdate(User user) {
    
    Role role = roleDAO.findOne(user.getRoleId());
    UserRole userRole = new UserRole(user, role);
    userRole = userRoleDAO.save(userRole);
    user.setUserRole(userRole);
    
    return userDAO.save(user);
  }

  public void delete(User baseEntity) {
    userDAO.save(baseEntity);
  }

  @Override
  public Page<User> findByUserNameLike(String userName, int page, int rows) {
    return userDAO.findByUserNameLike("%"+userName+"%", getPage(page, rows));
  }

  @Override
  public Page<User> findByFirstNameLike(String firstName, int page, int rows) {
    return userDAO.findByFirstNameLike("%"+firstName+"%", getPage(page, rows));
  }

  @Override
  public Page<User> findByLastNameLike(String lastName, int page, int rows) {
    return userDAO.findByLastNameLike("%"+lastName+"%", getPage(page, rows));
  }

  @Override
  public Page<User> findByEmailLike(String email, int page, int rows) {
    return userDAO.findByEmailLike("%"+email+"%", getPage(page, rows));
  }

  @Override
  public Page<User> findByEnabled(Boolean enabled, int page, int rows) {
    return userDAO.findByEnabled(enabled, getPage(page, rows));
  }

  @Override
  public Page<User> findByRole(Long role, int page, int rows) {
    return userDAO.findByRole(role, getPage(page, rows));
  }

  @Override
  public Page<User> findALL(int page, int rows) {
    return userDAO.findAll(getPage(page, rows));
  }
  

  @Override
  public List<Role> listRoles() {
    // TODO Auto-generated method stub
    return roleDAO.findAll();
  }

  
  private Pageable getPage(int page, int rows){
    return new PageRequest(page-1, rows);
  }

}
