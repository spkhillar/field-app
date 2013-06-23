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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.jpa.repositories.MaintenanceVisitDAO;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ApplicationServiceException;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

@Service("maintenanceVisitService")
public class MaintenanceVisitServiceImpl extends AbstractBaseService implements
		MaintenanceVisitService {

	@Autowired
	private MaintenanceVisitDAO maintenanceVisitDAO;

	/** The excel layout service. */
	@Resource(name = "defaultExcelLayoutService")
	private ExcelLayoutService excelLayoutService;

	/** The excel filler service. */
	@Resource(name = "defaultExcelLayoutFillerService")
	private ExcelFillerService excelFillerService;

	/** The generic query executor dao. */
	@Autowired
	private GenericQueryExecutorDAO genericQueryExecutorDAO;

	/** The Constant PAGE_SIZE. */
	private static final int PAGE_SIZE = 50;

	/** The Constant MAINTENANCE_VIST_EXCEL_COLUMN_LIST. */
	private static final String[] MAINTENANCE_VIST_EXCEL_COLUMN_LIST = new String[] {
			"Access Code", "User Name", "Site", "Created At",
			"Category of maintenance", "Spares used/ Items replaced - 1",
			"Spares used/ Items replaced - 2",
			"Spares used/ Items replaced - 3",
			"Spares used/ Items replaced - 4",
			"Spares used/ Items replaced - 5",
			"Spares used/ Items replaced - 6", "Consumables used -1",
			"Consumables used -2", "Consumables used -3",
			"Consumables used -4", "Consumables used -5",
			"Consumables used -6", "Run-Hours after PM of DG1 ",
			"Run-Hours after PM of DG2" };

	@Override
	@Transactional
	public MaintenanceVisit retrieve(final Long id) {
		return maintenanceVisitDAO.findOne(id);
	}

	@Override
	@Transactional
	public MaintenanceVisit saveOrUpdate(final MaintenanceVisit visit) {
		if (visit.getUser() == null) {
			User user = getUser(visit.getUserId());
			visit.setUser(user);
		}

		if (visit.getSite() == null) {
			Site site = getSite(visit.getSiteId());
			visit.setSite(site);
		} else {
			throw new ApplicationServiceException(
					"Site is required for creating a Routine Visit");
		}
		return maintenanceVisitDAO.save(visit);
	}

	@Override
	@Transactional
	public void delete(final MaintenanceVisit baseEntity) {
		maintenanceVisitDAO.delete(baseEntity);
	}

	@Override
	@Transactional
	public Page<MaintenanceVisit> getMaintenanceVisits(final Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				Sort.Direction.DESC, "createdAt");
		return maintenanceVisitDAO.findAll(request);
	}

	@Override
	public List<MaintenanceVisit> getVisits() {
		// TODO Auto-generated method stub
		return maintenanceVisitDAO.findAll();
	}

	@Override
	public Page<MaintenanceVisit> findALL(final int page, final int rows,
			final String sortOrder, String orderByField) {
		if ("userId".equals(orderByField)) {
			orderByField = "user.userName";
		} else if ("siteId".equals(orderByField)) {
			orderByField = "site.name";
		}
		return maintenanceVisitDAO.findAll(ServiceUtil.getPage(page, rows,
				sortOrder, orderByField));
	}

	@Override
	public Page<MaintenanceVisit> findALL(final int page, final int rows,
			final String predicate, final Map<String, Object> params) {
		String ejbql = "from MaintenanceVisit where " + predicate;
		return genericQueryExecutorDAO.executeQuery(ejbql,
				MaintenanceVisit.class, params, page, rows);
	}

	@Override
	public void exportReport(final String filterPredicate,
			final Map<String, Object> paramObject,
			final HttpServletResponse httpServletResponse,
			final String attachmentFileName) {
		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("routine-visit");

		// 3.create coulmn headers
		List<String> excelColumns = new ArrayList<String>(
				Arrays.asList(MAINTENANCE_VIST_EXCEL_COLUMN_LIST));
		List<MaintenanceVisit> maintenanceVisits = null;
		// step 5 get entities
		if (StringUtils.isBlank(filterPredicate)) {
			maintenanceVisits = getVisits();
		} else {
			String ejbql = "from MaintenanceVisit where " + filterPredicate;
			maintenanceVisits = genericQueryExecutorDAO.executeQuery(ejbql,
					MaintenanceVisit.class, paramObject);
		}

		// step 6 populate values as per the headings
		List<List<Object>> targetValues = new ArrayList<List<Object>>();
		for (MaintenanceVisit maintenanceVisit : maintenanceVisits) {
			List<Object> values = new ArrayList<Object>();
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getAccessCode()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getUserId()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSiteId()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCreatedAt()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCategoryOfMaintenance()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSparesUsedItemsReplaced1()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSparesUsedItemsReplaced2()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSparesUsedItemsReplaced3()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSparesUsedItemsReplaced4()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSparesUsedItemsReplaced5()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getSparesUsedItemsReplaced6()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCosumablesUsed1()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCosumablesUsed2()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCosumablesUsed3()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCosumablesUsed4()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCosumablesUsed5()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getCosumablesUsed6()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getRunHoursAfterPmdG1()));
			values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit
					.getRunHourAfterPmdG2()));
			targetValues.add(values);
		}

		// step 7 initialize renderer model
		ExcelRendererModel excelRendererModel = new ExcelRendererModel(
				worksheet, 10000, excelColumns, "Maintenance Visit");

		// step 8 invoke layout service
		excelLayoutService.buildReport(excelRendererModel);

		// step 9 fill report content
		excelFillerService.fillReport(excelRendererModel, targetValues);

		// step 10 write report
		ExcelWriter.write(httpServletResponse, workbook, attachmentFileName);

	}

}
