package com.telenoetica.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.telenoetica.jpa.entities.User;
import com.telenoetica.web.util.JqGridFilter;
import com.telenoetica.web.util.JqGridFilter.Rule;
import com.telenoetica.web.util.JqGridFilterQueryBuilder;
import com.telenoetica.web.util.JqGridObjectMapper;

public abstract class AbstractJqGridFilterController extends BaseController{

	public abstract String[] getFilterExclusionProperties();

	public abstract Map<String, String> getFilterExcludedPropertyQueryMapping();

	public abstract Map<String, String> getFilterExcludedPropertyOrderMapping();

	public String getFilteredRecords(String filters, String sord, String sidx,
			Map<String, Object> paramObject) {
		JqGridFilter jqgridFilter = JqGridObjectMapper.map(filters);
		String groupOperator = " " + jqgridFilter.getGroupOp() + " ";
		List<String> predicateString = new ArrayList<String>();
		String[] execludedFilters = getFilterExclusionProperties();
		List<Rule> excludedRules = JqGridFilterQueryBuilder.getJpqlPredicate(
				jqgridFilter, execludedFilters, paramObject, predicateString,
				User.class, groupOperator);
		List<String> excludedPredicates = getExcludedPredicates(excludedRules,
				paramObject);

		String predicateFirstPart = predicateString.get(0);
		String finalPredicate = predicateFirstPart;
		String predicateSecondPart = "";
		if (CollectionUtils.isNotEmpty(excludedPredicates)) {
			predicateSecondPart = StringUtils.join(excludedPredicates,
					groupOperator);
			finalPredicate = finalPredicate + groupOperator
					+ predicateSecondPart;
		}
		String orderByField = getOrderByfield(sidx);
		if (orderByField != null) {
			finalPredicate = finalPredicate + " order by " + orderByField + " "
					+ sord;
		}
		return finalPredicate;
		// return null;
	}

	public List<String> getExcludedPredicates(List<Rule> excludedRules,
			Map<String, Object> paramObject) {
		List<String> excludedPredicates = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(excludedRules)) {
			int paramSize = paramObject.size();
			String exeRule = null;
			for (Rule rule : excludedRules) {
				String value = getFilterExcludedPropertyQueryMapping().get(
						rule.getField());
				if (StringUtils.isBlank(value)) {
					continue;
				}
				Rule newRule = new Rule(null, value, rule.getOp(),
						rule.getData());
				exeRule = JqGridFilterQueryBuilder.createRuleString(newRule,
						paramObject, paramSize, Long.class);
				excludedPredicates.add(exeRule);
			}
		}
		return excludedPredicates;
	}

	public String getOrderByfield(String filter) {
		String orderByField = filter;
		String excludedPropMapping = this.getFilterExcludedPropertyOrderMapping()
				.get(filter);
		if (StringUtils.isNotBlank(excludedPropMapping)) {
			return excludedPropMapping;
		}
		return orderByField;
	}

}
