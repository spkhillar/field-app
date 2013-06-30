package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.service.AndroidHomeService;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.util.model.HomeAndroidObject;

@Service("androidHomeService")
public class AndroidHomeServiceImpl implements AndroidHomeService {

	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	@Autowired
	private CallOutVisitService callOutVisitService;

	@Autowired
	private DieselVisitService dieselVisitService;

	@Autowired
	private RoutineVisitService routineVisitService;

	@Autowired
	private MaintenanceVisitService maintenanceVisitService;

	@Override
	@Transactional(readOnly = true)
	public HomeAndroidObject getAndroidHomeObject() {
		List<String> sites = genericQueryExecutorDAO.executeQuery(
				"select name from Site", String.class);
		List<String> faults = genericQueryExecutorDAO.executeQuery(
				"select name from Fault", String.class);
		List<String> clients = genericQueryExecutorDAO.executeQuery(
				"select name from Client", String.class);
		List<String> maintenanceCategories = genericQueryExecutorDAO
				.executeQuery("select name from MaintenanceVisitCategory",
						String.class);
		List<String> spares = genericQueryExecutorDAO.executeQuery(
				"select name from Spare", String.class);
		HomeAndroidObject homeAndroidObject = new HomeAndroidObject(sites,
				spares, clients, faults, maintenanceCategories);
		return homeAndroidObject;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Integer> getchartData() {
		Date startDate;
		Date endDate;
		Calendar currentDate = Calendar.getInstance(); // Get the current date
		endDate = currentDate.getTime();
		startDate = new DateTime(endDate).minusDays(30).toDate();
		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("endDate", endDate);
		param.put("startDate", startDate);
		List<Integer> chartData = new ArrayList<Integer>(4);

		long rvCount = routineVisitService.findRecordCount(param);
		long cvCount = callOutVisitService.findRecordCount(param);
		long dvCount = dieselVisitService.findRecordCount(param);
		long mvCount = maintenanceVisitService.findRecordCount(param);
		chartData.add((int) rvCount);
		chartData.add((int) cvCount);
		chartData.add((int) dvCount);
		chartData.add((int) mvCount);
		return chartData;
	}

}
