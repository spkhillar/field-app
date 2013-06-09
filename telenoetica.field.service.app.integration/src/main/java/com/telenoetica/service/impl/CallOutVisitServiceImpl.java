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

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.CallOutVisitDAO;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ApplicationServiceException;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

@Service("callOutVisitService")
public class CallOutVisitServiceImpl implements CallOutVisitService {

	@Autowired
	private CallOutVisitDAO callOutVisitDAO;

	@Autowired
	private SiteService siteService;

	@Autowired
	private UserService userService;
	
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

	/** The Constant CALLOUT_VIST_EXCEL_COLUMN_LIST. */
	private static final String[] CALLOUT_VIST_EXCEL_COLUMN_LIST = new String[] {
			"Access Code", "User Name", "Site", "Created At",
			"Call-out CSR or TT number", "Time when complain received", "Time when reached to site",
			"Time when fault resolved", "Customer1 Impacted", "Customer2 Impacted",
			"Customer3 Impacted", "Customer4 Impacted", "Main Category of fault",
			"Equipment/ Component that caused fault", "Equipment/ Componenet repaired",
			"Equipment/ Componenet replaced", "Is the fix/ resolution temporary or permanent? ",
			"Actions required for permanent resolution"};

	@Transactional
	public CallOutVisit retrieve(Long id) {
		return this.callOutVisitDAO.findOne(id);
	}

	@Transactional
	public CallOutVisit saveOrUpdate(CallOutVisit callOutVisit) {
		if (callOutVisit.getUser() == null) {
			User user = userService.retrieve(1L);
			callOutVisit.setUser(user);
		}

		if (callOutVisit.getSite() == null) {
			Site site = siteService.findSite(callOutVisit.getSiteId());
			if (site == null) {
				throw new ApplicationServiceException("Site \""
						+ callOutVisit.getSiteId() + "\" not found in system");
			}
			callOutVisit.setSite(site);
		} else {
			throw new ApplicationServiceException(
					"Site is required for creating a Routine Visit");
		}
		return this.callOutVisitDAO.save(callOutVisit);
	}

	@Transactional
	public void delete(CallOutVisit baseEntity) {
		this.callOutVisitDAO.delete(baseEntity);
	}

	@Transactional
	public Page<CallOutVisit> getCallOutVisits(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				Sort.Direction.DESC, "createdAt");
		return callOutVisitDAO.findAll(request);
	}

	@Override
	public List<CallOutVisit> getVisits() {
		// TODO Auto-generated method stub
		return callOutVisitDAO.findAll();
	}

	@Override
	public Page<CallOutVisit> findALL(int page, int rows, String sortOrder,
			String orderByField) {
		if ("userId".equals(orderByField)) {
			orderByField = "user.userName";
		} else if ("siteId".equals(orderByField)) {
			orderByField = "site.name";
		}
		return callOutVisitDAO.findAll(ServiceUtil.getPage(page, rows,
				sortOrder, orderByField));
	}

	@Override
	public Page<CallOutVisit> findALL(int page, int rows, String predicate,
			Map<String, Object> params) {
		String ejbql = "from CallOutVisit where " + predicate;
		return genericQueryExecutorDAO.executeQuery(ejbql, CallOutVisit.class,
				params, page, rows);
	}

	@Override
	public void exportReport(String filterPredicate,
			Map<String, Object> paramObject,
			HttpServletResponse httpServletResponse, String attachmentFileName) {
		// 1. Create new workbook
				HSSFWorkbook workbook = new HSSFWorkbook();

				// 2. Create new worksheet
				HSSFSheet worksheet = workbook.createSheet("routine-visit");

				// 3.create coulmn headers
				List<String> excelColumns = new ArrayList<String>(
						Arrays.asList(CALLOUT_VIST_EXCEL_COLUMN_LIST));
				List<CallOutVisit> callOutVisits = null;
				// step 5 get entities
				if (StringUtils.isBlank(filterPredicate)) {
					callOutVisits = getVisits();
				} else {
					String ejbql = "from CallOutVisit where " + filterPredicate;
					callOutVisits = genericQueryExecutorDAO.executeQuery(ejbql,
							CallOutVisit.class, paramObject);
				}

				// step 6 populate values as per the headings
				List<List<Object>> targetValues = new ArrayList<List<Object>>();
				for (CallOutVisit callOutVisit : callOutVisits) {
					List<Object> values = new ArrayList<Object>();
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getAccessCode()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit.getUserId()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit.getSiteId()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getCreatedAt()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getCallOutCsrOrTtNumber()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getTimeComplainReceived()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getTimeReachedToSite()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.gettimeFaultResolved()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getCustomer1Impacted()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getCustomer2Impacted()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getCustomer3Impacted()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getCustomer4Impacted()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getMainCategoryOfFault()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getEquipmentComponentCausedFault()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getEquipmentComponentRepaired()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getEquipmentComponentReplaced()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getFixResolutionTemporaryOrPermanent()));
					values.add(ServiceUtil.checkAndReturnValue(callOutVisit
							.getActionsRequiredForPermanentResolution()));
					targetValues.add(values);
				}

				// step 7 initialize renderer model
				ExcelRendererModel excelRendererModel = new ExcelRendererModel(
						worksheet, 10000, excelColumns, "CallOut Visit");

				// step 8 invoke layout service
				excelLayoutService.buildReport(excelRendererModel);

				// step 9 fill report content
				excelFillerService.fillReport(excelRendererModel, targetValues);

				// step 10 write report
				ExcelWriter.write(httpServletResponse, workbook, attachmentFileName);
		
	}

}
