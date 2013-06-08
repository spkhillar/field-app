/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.DieselVisitDAO;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ApplicationServiceException;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

/**
 * The Class DieselVisitServiceImpl.
 * 
 * @author Shiv Prasad Khillar
 */
@Service("dieselVisitService")
@Transactional
public class DieselVisitServiceImpl implements DieselVisitService {

	/** The diesel visit dao. */
	@Autowired
	private DieselVisitDAO dieselVisitDAO;

	/** The site service. */
	@Autowired
	private SiteService siteService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The generic query executor dao. */
	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	/** The excel layout service. */
	@Resource(name = "defaultExcelLayoutService")
	private ExcelLayoutService excelLayoutService;

	/** The excel filler service. */
	@Resource(name = "defaultExcelLayoutFillerService")
	private ExcelFillerService excelFillerService;

	/** The Constant ROUTINE_VIST_EXCEL_COLUMN_LIST. */
	private static final String[] DIESEL_VIST_EXCEL_COLUMN_LIST = new String[] {
			"Access Code", "User Name", "Site", "Created At", "DRN/DTN Number",
			"Diesel Supply Type", "Transfer Site", "Vendor Name",
			"Diesel Level T1 before receipt", "Diesel Level T2 before receipt",
			"Diesel received (Ltrs)", "Run Hour Gen 1", "Run Hour Gen 2",
			"DRN booked at site", "Diesel log book maintained",
			"PHCN Installed", "PHCN Hrs per day", "Hybrid/PIU installed",
			"Hybrid/PIU hrs per Day" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
	 */
	@Override
	public DieselVisit retrieve(Long id) {
		return dieselVisitDAO.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.
	 * entities.BaseEntity)
	 */
	@Override
	public DieselVisit saveOrUpdate(DieselVisit dieselVisit) {

		if (dieselVisit.getUser() == null) {
			User user = userService.retrieve(1L);
			dieselVisit.setUser(user);
		}

		if (dieselVisit.getSite() == null) {
			Site site = siteService.findSite(dieselVisit.getSiteId());
			if (site == null) {
				throw new ApplicationServiceException("Site \""
						+ dieselVisit.getSiteId() + "\" not found in system");
			}
			dieselVisit.setSite(site);
		} else {
			throw new ApplicationServiceException(
					"Site is required for creating a Routine Visit");
		}

		if (StringUtils.isNotBlank(dieselVisit.getTransferredSiteId())) {
			Site transferredSite = siteService.findSite(dieselVisit
					.getTransferredSiteId());
			if (transferredSite == null) {
				throw new ApplicationServiceException("Transfer Site \""
						+ dieselVisit.getTransferredSiteId()
						+ "\" not found in system");
			}
			dieselVisit.setTransferredSite(transferredSite);
		}

		return dieselVisitDAO.save(dieselVisit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telenoetica.service.BaseService#delete(com.telenoetica.jpa.entities
	 * .BaseEntity)
	 */
	@Override
	public void delete(DieselVisit baseEntity) {
		dieselVisitDAO.delete(baseEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telenoetica.service.DieselVisitService#getVisits()
	 */
	@Override
	public List<DieselVisit> getVisits() {
		return dieselVisitDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telenoetica.service.DieselVisitService#findALL(int, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Page<DieselVisit> findALL(int page, int rows, String sortOrder,
			String orderByField) {
		if ("userId".equals(orderByField)) {
			orderByField = "user.userName";
		} else if ("siteId".equals(orderByField)) {
			orderByField = "site.name";
		} else if ("transferredSiteId".equals(orderByField)) {
			orderByField = "transferredSite.name";
		}
		return dieselVisitDAO.findAll(ServiceUtil.getPage(page, rows,
				sortOrder, orderByField));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.telenoetica.service.DieselVisitService#findALL(int, int,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Page<DieselVisit> findALL(int page, int rows, String predicate,
			Map<String, Object> params) {
		String ejbql = "from DieselVisit where " + predicate;
		return genericQueryExecutorDAO.executeQuery(ejbql, DieselVisit.class,
				params, page, rows);
	}

	/**
	 * Export Diesel Visit.
	 * 
	 * @param filterPredicate
	 *            the filter predicate
	 * @param paramObject
	 *            the param object
	 * @param httpServletResponse
	 *            the http servlet response
	 * @param attachmentFileName
	 *            the attachment file name
	 * @see com.telenoetica.service.RoutineVisitService#exportReport(java.lang.String,
	 *      java.util.Map, javax.servlet.http.HttpServletResponse,
	 *      java.lang.String)
	 */
	@Override
	public void exportReport(String filterPredicate,
			Map<String, Object> paramObject,
			HttpServletResponse httpServletResponse, String attachmentFileName) {

		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("diesel-visit");

		// 3.create coulmn headers
		List<String> excelColumns = new ArrayList<String>(
				Arrays.asList(DIESEL_VIST_EXCEL_COLUMN_LIST));
		List<DieselVisit> dieselVisits = null;
		// step 5 get entities
		if (StringUtils.isBlank(filterPredicate)) {
			dieselVisits = getVisits();
		} else {
			String ejbql = "from DieselVisit where " + filterPredicate;
			dieselVisits = genericQueryExecutorDAO.executeQuery(ejbql,
					DieselVisit.class, paramObject);
		}

		// step 6 populate values as per the headings
		List<List<Object>> targetValues = new ArrayList<List<Object>>();
		for (DieselVisit dieselVisit : dieselVisits) {
			List<Object> values = new ArrayList<Object>();
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getAccessCode()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit.getUserId()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit.getSiteId()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getCreatedAt()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDrnNumber()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDieselTransferOrBulkSupply()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getTransferredSiteId()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getBulkNameOfVendor()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDieselLevelT1BeforeVisit()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDieselLevelT2BeforeVisit()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDieselReceivedLtrs()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getRunHourGen1()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getRunHourGen2()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDrnBookAtSite()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getDieselLogBookMaintained()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getPhcnInstalled()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getPhcnHrsPerDay()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getHybridOrPiuInstalled()));
			values.add(ServiceUtil.checkAndReturnValue(dieselVisit
					.getHybridOrPiuHrsPerDay()));
			targetValues.add(values);
		}

		// step 7 initialize renderer model
		ExcelRendererModel excelRendererModel = new ExcelRendererModel(
				worksheet, 5000, excelColumns, "Diesel Visit");

		// step 8 invoke layout service
		excelLayoutService.buildReport(excelRendererModel);

		// step 9 fill report content
		excelFillerService.fillReport(excelRendererModel, targetValues);

		// step 10 write report
		ExcelWriter.write(httpServletResponse, workbook, attachmentFileName);

	}

}
