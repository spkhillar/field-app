package com.telenoetica.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.jpa.entities.BaseEntity;
import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.UserService;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.util.DomainObjectMapper;
import com.telenoetica.web.util.JqgridResponse;

@RequestMapping(value = "/user")
@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/list")
  public String getUsersPage() {
    return "admin.users";
  }

  @RequestMapping(value = "/records", produces = "application/json")
  public @ResponseBody
  JqgridResponse<User> records(@RequestParam("_search") Boolean search,
      @RequestParam(value = "filters", required = false) String filters,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "rows", required = false) Integer rows,
      @RequestParam(value = "sidx", required = false) String sidx,
      @RequestParam(value = "sord", required = false) String sord) {

    Page<User> users = userService.findALL(page, rows);
    List<BaseEntity> list = DomainObjectMapper.listEntities(users);
    JqgridResponse<User> response = new JqgridResponse<User>();
    response.setRows(list);
    response.setRecords(Long.valueOf(users.getTotalElements()).toString());
    response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
    response.setPage(Integer.valueOf(users.getNumber() + 1).toString());
    return response;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  @ResponseBody
  public RestResponse create(@RequestParam String userName, @RequestParam String password,
      @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
      @RequestParam boolean enabled, @RequestParam Long roleId) {
    User user = new User(userName, password, firstName, lastName, email, enabled);
    user.setRoleId(roleId);
    user = userService.saveOrUpdate(user);
    String message = "Saved Successfully with Id" + user.getId();
    RestResponse response = new RestResponse(0, message);
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public RestResponse update(@RequestParam String userName, @RequestParam String password,
      @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
      @RequestParam boolean enabled, @RequestParam Long roleId) {
    User user = new User(userName, password, firstName, lastName, email, enabled);
    user.setRoleId(roleId);
    // user = userService.saveOrUpdate(user);
    String message = "Saved Successfully with Id" + user.getId();
    RestResponse response = new RestResponse(0, message);
    return response;
  }

  @RequestMapping(value = "/roles")
  @ResponseBody
  public String getRoles() {
    List<Role> roleList = userService.listRoles();
    StringBuilder sb = new StringBuilder();
    for (Role role : roleList) {
      sb.append(role.getId()).append(":").append(role.getName()).append(";");
    }
    String roles = sb.substring(0, sb.length() - 1);
    return roles;
  }

  @RequestMapping(value = "/export")
  public void export(@RequestParam("_search") Boolean search,
      @RequestParam(value = "filters", required = false) String filters,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "rows", required = false) Integer rows,
      @RequestParam(value = "sidx", required = false) String sidx,
      @RequestParam(value = "sord", required = false) String sord,HttpServletResponse httpServletResponse) {
    
    userService.exportUsers(httpServletResponse, "users.xls");
  }

}
