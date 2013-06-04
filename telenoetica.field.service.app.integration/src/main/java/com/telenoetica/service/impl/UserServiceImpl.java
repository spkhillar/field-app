package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.entities.UserRole;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
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

	// private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Resource(name = "defaultExcelLayoutService")
	private ExcelLayoutService excelLayoutService;

	@Resource(name = "defaultExcelLayoutFillerService")
	private ExcelFillerService excelFillerService;

	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	@Override
	public User retrieve(Long id) {
		return userDAO.findOne(id);
	}

	@Override
	public User saveOrUpdate(User user) {
		Role role = roleDAO.findOne(user.getRoleId());
		UserRole userRole = new UserRole(user, role);
		user.setUserRole(userRole);
		return userDAO.save(user);
	}

	@Override
	public void delete(User baseEntity) {
		// User user = userDAO.findOne(baseEntity.getId());
		//userRoleDAO.delete(user.getUserRole());
		userDAO.delete(baseEntity.getId());
	}

	@Override
	public Page<User> findALL(int page, int rows, String sortOrder, String orderByField) {
		if ("roleId".equals(orderByField)) {
			orderByField = "userRole.role.id";
		}
		return userDAO.findAll(ServiceUtil.getPage(page, rows, sortOrder, orderByField));
	}

	@Override
	public List<Role> listRoles() {
		return roleDAO.findAll();
	}

	@Override
	public List<User> findALL() {
		return userDAO.findAll();
	}

	@Override
	public void exportUsers(String filterPredicate, Map<String, Object> paramObject,HttpServletResponse httpServletResponse, String attachmentFileName) {

		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("users");

		// 3.create coulmn headers
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
		List<User> userList = null;
		// step 5 get entities
		if(StringUtils.isBlank(filterPredicate )){
			userList = findALL();
		}else{
			String ejbql = "from User where "+filterPredicate;
			userList = genericQueryExecutorDAO.executeQuery(ejbql, User.class,paramObject);
		}

		// step 6 populate values as per the headings
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

		// step 7 initialize renderer model
		ExcelRendererModel excelRendererModel = new ExcelRendererModel(worksheet, 5000, excelColumns, "Users");

		// step 8 invoke layout service
		excelLayoutService.buildReport(excelRendererModel);

		// step 9 fill report content
		excelFillerService.fillReport(excelRendererModel, targetValues);

		// step 10 write report
		ExcelWriter.write(httpServletResponse, workbook, attachmentFileName);

	}

	@Override
	public User update(User user) {

		User savedUser = userDAO.findOne(user.getId());

		BeanUtils.copyProperties(user, savedUser, new String[] { "id", "roleId", "password", "userRole", "version",
		"createdAt" });
		if(StringUtils.isNotBlank(user.getPassword()) && !savedUser.getPassword().equals(user.getPassword())){
			savedUser.setPassword(user.getPassword());
		}
		savedUser = userDAO.save(savedUser);
		if (!user.getRoleId().equals(savedUser.getRoleId())) {
			userRoleDAO.delete(savedUser.getUserRole());
			Role role = roleDAO.findOne(user.getRoleId());
			UserRole userRole = new UserRole(savedUser, role);
			userRole = userRoleDAO.save(userRole);
			savedUser.setUserRole(userRole);
		}
		return savedUser;
	}

	@Override
	public Page<User> findALL(int page, int rows, String predicate, Map<String, Object> params) {
		String ejbql = "from User where " + predicate;
		return genericQueryExecutorDAO.executeQuery(ejbql, User.class, params, page, rows);
	}
}
