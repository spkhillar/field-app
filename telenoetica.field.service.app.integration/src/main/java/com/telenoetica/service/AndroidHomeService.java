package com.telenoetica.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.telenoetica.util.model.HomeAndroidObject;

public interface AndroidHomeService {

	public HomeAndroidObject getAndroidHomeObject();

	public List<Integer> getchartData();

	public void exportAndroidApp(HttpServletResponse httpServletResponse);
}
