/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.util.ApplicationConstants;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.util.DomainObjectMapper;
import com.telenoetica.web.util.JqGridResponse;

/**
 * The Class DieselVisitController.
 * 
 * @author Shiv Prasad Khillar
 */
@Controller
@RequestMapping(value = "/diesel")
@SessionAttributes("dieselForm")
public class DieselVisitController extends AbstractJqGridFilterController {

	/** The diesel visit service. */
	@Autowired
	private DieselVisitService dieselVisitService;

	/** The excluded props in filter. */
	private final String[] excludedPropsInFilter = new String[] { "userId",
			"siteId", "createdAt", "transferredSiteId" };

	/** The Constant excludedPropQueryMapping. */
	private static final Map<String, String> excludedPropQueryMapping = new HashMap<String, String>();

	/** The Constant excludedPropOrderMapping. */
	private static final Map<String, String> excludedPropOrderMapping = new HashMap<String, String>();
	static {
		excludedPropQueryMapping.put("userId", "user.userName");
		excludedPropQueryMapping.put("siteId", "site.name");
		excludedPropQueryMapping.put("createdAt", "date(createdAt)");
		excludedPropQueryMapping.put("transferredSiteId",
				"transferredSite.name");

		excludedPropOrderMapping.put("userId", "user.userName");
		excludedPropOrderMapping.put("siteId", "site.name");
		excludedPropOrderMapping.put("transferredSiteId",
				"transferredSite.name");
	}

	/**
	 * Creates the form bean.
	 * 
	 * @return the Diesel visit
	 */
	@ModelAttribute("dieselForm")
	public DieselVisit createFormBean() {
		DieselVisit dieselVisit = new DieselVisit();
		dieselVisit
				.setDieselTransferOrBulkSupply(ApplicationConstants.BULK_TRANSFER);
		return dieselVisit;
	}

	/**
	 * Save.
	 * 
	 * @param dieselVisit
	 *            the diesel visit
	 * @return the string
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(DieselVisit dieselVisit) {
		DieselVisit savedDieselVisit = dieselVisitService
				.saveOrUpdate(dieselVisit);
		return "Saved Successfuly with id:" + savedDieselVisit.getId();
	}

	/**
	 * Creates the.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/new")
	public String create() {
		return "diesel.new";
	}

	/**
	 * Save api.
	 * 
	 * @param dieselVisit
	 *            the diesel visit
	 * @return the rest response
	 */
	@RequestMapping(value = "/rest", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	@ResponseBody
	public RestResponse saveApi(@RequestBody DieselVisit dieselVisit) {
		DieselVisit savedDieselVisit = dieselVisitService
				.saveOrUpdate(dieselVisit);
		RestResponse response = new RestResponse(0,
				"Saved Successfuly with id:" + savedDieselVisit.getId());
		return response;
	}

	/**
	 * Save api.
	 * 
	 * @param id
	 *            the id
	 * @return the diesel visit
	 */
	@RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public DieselVisit saveApi(@PathVariable Long id) {
		DieselVisit dieselVisit = dieselVisitService.retrieve(id);
		return dieselVisit;
	}

	/**
	 * Gets the users page.
	 * 
	 * @return the users page
	 */
	@RequestMapping(value = "/list")
	public String getDieselVisitPage() {
		return "diesel.list";
	}

	/**
	 * Records.
	 * 
	 * @param search
	 *            the search
	 * @param filters
	 *            the filters
	 * @param page
	 *            the page
	 * @param rows
	 *            the rows
	 * @param sidx
	 *            the sidx
	 * @param sord
	 *            the sord
	 * @return the jq grid response
	 */
	@RequestMapping(value = "/records", produces = "application/json")
	public @ResponseBody
	JqGridResponse<DieselVisit> records(
			@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		Page<DieselVisit> dieselVisits = null;

		if (search) {

			Map<String, Object> paramObject = new LinkedHashMap<String, Object>();
			String filterPredicate = getFilteredRecords(filters, sord, sidx,
					paramObject, DieselVisit.class);
			dieselVisits = dieselVisitService.findALL(page, rows,
					filterPredicate, paramObject);
		} else {
			dieselVisits = dieselVisitService.findALL(page, rows, sord, sidx);
		}
		List<Object> list = DomainObjectMapper.listEntities(dieselVisits);
		JqGridResponse<DieselVisit> response = new JqGridResponse<DieselVisit>();
		response.setRows(list);
		response.setRecords(Long.valueOf(dieselVisits.getTotalElements())
				.toString());
		response.setTotal(Integer.valueOf(dieselVisits.getTotalPages())
				.toString());
		response.setPage(Integer.valueOf(dieselVisits.getNumber() + 1)
				.toString());
		return response;
	}

	/**
	 * Export.
	 * 
	 * @param search
	 *            the search
	 * @param filters
	 *            the filters
	 * @param page
	 *            the page
	 * @param rows
	 *            the rows
	 * @param sidx
	 *            the sidx
	 * @param sord
	 *            the sord
	 * @param httpServletResponse
	 *            the http servlet response
	 */
	@RequestMapping(value = "/export")
	public void export(@RequestParam("_search") Boolean search,
			@RequestParam(value = "filters", required = false) String filters,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord,
			HttpServletResponse httpServletResponse) {
		String filterPredicate = null;
		Map<String, Object> paramObject = null;
		if (search) {
			paramObject = new LinkedHashMap<String, Object>();
			filterPredicate = getFilteredRecords(filters, sord, sidx,
					paramObject, DieselVisit.class);
		}
		dieselVisitService.exportReport(filterPredicate, paramObject,
				httpServletResponse, "diesel-visit.xls");
	}

	/**
	 * Gets the filter exclusion properties.
	 * 
	 * @return the filter exclusion properties
	 * @see com.telenoetica.web.controller.AbstractJqGridFilterController#getFilterExclusionProperties()
	 */
	@Override
	public String[] getFilterExclusionProperties() {
		return excludedPropsInFilter;
	}

	/**
	 * Gets the filter excluded property query mapping.
	 * 
	 * @return the filter excluded property query mapping
	 * @see com.telenoetica.web.controller.AbstractJqGridFilterController#getFilterExcludedPropertyQueryMapping()
	 */
	@Override
	public Map<String, String> getFilterExcludedPropertyQueryMapping() {
		return excludedPropQueryMapping;
	}

	/**
	 * Gets the filter excluded property order mapping.
	 * 
	 * @return the filter excluded property order mapping
	 * @see com.telenoetica.web.controller.AbstractJqGridFilterController#getFilterExcludedPropertyOrderMapping()
	 */
	@Override
	public Map<String, String> getFilterExcludedPropertyOrderMapping() {
		return excludedPropOrderMapping;
	}
}
