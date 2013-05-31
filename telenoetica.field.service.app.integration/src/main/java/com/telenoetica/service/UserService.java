package com.telenoetica.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;

public interface UserService extends BaseService<User> {
  
  Page<User> findByUserNameLike(String userName, int page,int rows);
  Page<User> findByFirstNameLike(String firstName, int page,int rows);
  Page<User> findByLastNameLike(String lastName, int page,int rows);
  Page<User> findByEmailLike(String email, int page,int rows);
  Page<User> findByEnabled(Boolean enabled, int page,int rows);
  Page<User> findByRole(Long role, int page,int rows);
  Page<User> findALL(int page, int rows);
  List<User> findALL();
  
  List<Role> listRoles();
  
  void exportUsers(HttpServletResponse httpServletResponse,String attachmentFileName);

}
