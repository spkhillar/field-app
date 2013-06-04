package com.telenoetica.web.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telenoetica.service.util.ServiceUtil;
import com.telenoetica.web.util.JqGridFilter.Rule;
import com.telenoetica.web.util.JqGridFilter.RuleOperator;

public class JqGridFilterQueryBuilder {

	  private static final Logger LOGGER = Logger.getLogger(JqGridFilterQueryBuilder.class);

	  private JqGridFilterQueryBuilder() {

	  }

	  public static List<Rule> getJpqlPredicate(JqGridFilter jqgridFilter, String[] excludedProperties,
	     Map<String, Object> paramObject,List<String> predicateString,Class clazz,String groupOperator) {

	    List<String> rules = new ArrayList<String>();
	    String predicateRules = null;
	    List<Rule> excludedFilterList = new ArrayList<Rule>();
	    List<Rule> filterRuleList = jqgridFilter.getRules();
	    int paramIndex = 0;
	    for (Rule rule : filterRuleList) {
	      if (ArrayUtils.contains(excludedProperties, rule.getField())) {
	        excludedFilterList.add(rule);
	        continue;
	      }
	      predicateRules = getRuleString(rule, paramObject, paramIndex,clazz);
	      if (predicateRules == null) {
	        continue;
	      }
	      rules.add(predicateRules);
	      paramIndex++;
	    }

	    String predicate = StringUtils.join(rules, groupOperator);
	    predicateString.add(predicate);
	    System.err.println("..predicate..."+predicate);
	    //LOGGER.debug("...predicateString.." + predicateString);
	    return excludedFilterList;
	  }

	  private static String getRuleString(Rule rule, Map<String, Object> paramObject, int paramIndex,Class clazz) {
	    Field field = ServiceUtil.findField(clazz.getName(), rule.getField());
	    if (field == null) {
	      return null;
	    }
	    String predicateRule = createRuleString(rule, paramObject, paramIndex, field.getType());
	    return predicateRule;
	  }
	  
	  public static String createRuleString(Rule rule, Map<String, Object> paramObject, int paramIndex,Class fieldValueType) {
	  
	    String predicateRule = new String();

	    String operator = null;
	    if (RuleOperator.cn.name().equals(rule.getOp())) {
	      operator = "like";
	    } else if (RuleOperator.eq.name().equals(rule.getOp())) {
	      operator = "=";
	    } else if (RuleOperator.ne.name().equals(rule.getOp())) {
	      operator = "<>";
	    } else if (RuleOperator.gt.name().equals(rule.getOp())) {
	      operator = ">";
	    } else if (RuleOperator.lt.name().equals(rule.getOp())) {
	      operator = "<";
	    } else if (RuleOperator.bw.name().equals(rule.getOp())) {
	      operator = "like";
	    } else if (RuleOperator.ew.name().equals(rule.getOp())) {
	      operator = " like ";
	    }
	    predicateRule = rule.getField() + " " + operator + " :param" + paramIndex;

	    paramObject.put("param" + paramIndex, ServiceUtil.getTypedValue(fieldValueType, rule.getData(), rule.getOp()));
	    return predicateRule;
	  }


}
