package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;
  
  @Autowired
  private RoleDAO roleDAO;
  
  @Autowired
  private UserRoleDAO userRoleDAO;
  
  @Resource(name="defaultExcelLayoutService")
  private ExcelLayoutService excelLayoutService;
  
  @Resource(name="defaultExcelLayoutFillerService")
  private ExcelFillerService excelFillerService;
  
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
    return roleDAO.findAll();
  }

  
  private Pageable getPage(int page, int rows){
    return new PageRequest(page-1, rows);
  }

  @Override
  public List<User> findALL() {
    return userDAO.findAll();
  }

  @Override
  public void exportUsers(HttpServletResponse httpServletResponse, String attachmentFileName) {

    // 1. Create new workbook
    HSSFWorkbook workbook = new HSSFWorkbook();

    // 2. Create new worksheet
    HSSFSheet worksheet = workbook.createSheet("users");

    //3.create coulmn headers
    @SuppressWarnings("serial")
    List<String> excelColumns = new ArrayList<String>() {
      {
        add("User Name");
        add("First Name");
        add("Last Name");
        add("Email");
        add("Enabled");
        add("Role");
        add("Created Date");
      }
    };
    //step 5 get entities
    List<User> userList =  findALL();

    //step 6 populate values as per the headings
    List<List<Object>> targetValues = new ArrayList<List<Object>>();
    for (User user : userList) {
      List<Object> values = new ArrayList<Object>();
      values.add(ServiceUtil.checkAndReturnValue(user.getUserName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getFirstName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getLastName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getEmail()));
      values.add(ServiceUtil.checkAndReturnValue(user.getEnabled()));
      values.add(ServiceUtil.checkAndReturnValue(user.getUserRole().getRole().getName()));
      values.add(ServiceUtil.checkAndReturnValue(user.getCreatedAt()));
      targetValues.add(values);
    }
    
    //step 7 initialize renderer model
    ExcelRendererModel excelRendererModel = new ExcelRendererModel(worksheet, 5000, excelColumns, "Users");
    
    //step 8 invoke layout service
    excelLayoutService.buildReport(excelRendererModel);
    
    //step 9 fill report content
    excelFillerService.fillReport(excelRendererModel, targetValues);
    
    //step 10 write report
    ExcelWriter.write(httpServletResponse, workbook,attachmentFileName);
  
    
  }

}
