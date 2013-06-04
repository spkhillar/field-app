package com.telenoetica.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.jpa.entities.Role;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.UserService;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.util.DomainObjectMapper;
import com.telenoetica.web.util.JqGridResponse;

@RequestMapping(value = "/user")
@Controller
public class UserController  extends AbstractJqGridFilterController{

	@Autowired
	private UserService userService;

	private final String[] excludedPropsInFilter = new String[]{"roleId"};

	private static final Map<String,String> excludedPropQueryMapping = new HashMap<String, String>();

	private static final Map<String,String> excludedPropOrderMapping = new HashMap<String, String>();
	static{
		excludedPropQueryMapping.put("roleId", "userRole.role.id");
		excludedPropOrderMapping.put("roleId", "userRole.role.name");
	}

	@RequestMapping(value = "/list")
	public String getUsersPage() {
		return "admin.users";
	}

	@RequestMapping(value = "/records", produces = "application/json")
	public @ResponseBody
	JqGridResponse<User> records(@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		Page<User> users = null;
		if (search == true) {
			Map<String, Object> paramObject = new LinkedHashMap<String, Object>();
			String filterPredicate = getFilteredRecords(filters, sord, sidx, paramObject);
			users = userService.findALL(page, rows, filterPredicate, paramObject);
		} else {
			users = userService.findALL(page, rows, sord, sidx);
		}
		List<Object> list = DomainObjectMapper.listEntities(users);
		JqGridResponse<User> response = new JqGridResponse<User>();
		response.setRows(list);
		response.setRecords(Long.valueOf(users.getTotalElements()).toString());
		response.setTotal(Integer.valueOf(users.getTotalPages()).toString());
		response.setPage(Integer.valueOf(users.getNumber() + 1).toString());
		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse create(User user) {
		user = userService.saveOrUpdate(user);
		String message = "Saved Successfully with Id" + user.getId();
		RestResponse response = new RestResponse(0, message);
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse update(User user) {
		userService.update(user);
		String message = "User Updated Successfully";
		RestResponse response = new RestResponse(0, message);
		return response;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse delete(User user) {
		userService.delete(user);
		String message = "User deleted Successfully";
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
			@RequestParam(value = "sord", required = false) String sord, HttpServletResponse httpServletResponse) {
		String filterPredicate = null;
		Map<String, Object> paramObject = null;
		if (search) {
			paramObject = new LinkedHashMap<String, Object>();
			filterPredicate = getFilteredRecords(filters, sord, sidx, paramObject);
		}
		userService.exportUsers(filterPredicate, paramObject, httpServletResponse, "users.xls");
	}

	public Boolean checkUserName(String userName) {
		return true;

	}

	@Override
	public String[] getFilterExclusionProperties(){
		return excludedPropsInFilter; 
	}

	@Override
	public Map<String, String> getFilterExcludedPropertyQueryMapping() {
		return excludedPropQueryMapping;
	}

	@Override
	public Map<String, String> getFilterExcludedPropertyOrderMapping() {
		return excludedPropOrderMapping;
	}

}
