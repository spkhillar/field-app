package com.telenoetica.service.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.User;

public class ServiceUtil {

	private static Map<String, Field[]> fieldMapping = new LinkedHashMap<String, Field[]>();

	static {
		String clazzName = User.class.getName();
		Field[] field = getAllFields(User.class);
		fieldMapping.put(clazzName, field);

		clazzName = CallOutVisit.class.getName();
		field = getAllFields(CallOutVisit.class);
		fieldMapping.put(clazzName, field);
	}

	private ServiceUtil() {

	}

	public static Field findField(String className, String fieldName) {
		Field[] fields = fieldMapping.get(className);
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Field[] getAllFields(Class klass) {
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(klass.getDeclaredFields()));
		if (klass.getSuperclass() != null) {
			fields.addAll(Arrays.asList(getAllFields(klass.getSuperclass())));
		}
		return fields.toArray(new Field[] {});
	}

	public static String checkAndReturnValue(String value) {
		if (StringUtils.isBlank(value)) {
			return "";
		}
		return value;
	}

	public static String checkAndReturnValue(Date date) {
		if (date == null) {
			return "";
		}
		return getDateInFormat(date, "MM/dd/yyyy HH:mm:ss");
	}

	public static String getDateInFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);

	}

	public static String checkAndReturnValue(Boolean enabled) {
		return enabled.toString();
	}

	public static Pageable getPage(int page, int rows, String sortOrder,
			String orderByField) {
		Sort.Direction sortDirection = Sort.Direction.ASC;
		if ("desc".equalsIgnoreCase(sortOrder)) {
			sortDirection = Sort.Direction.DESC;
		}
		Pageable pagebale = new PageRequest(page - 1, rows, sortDirection,
				orderByField);
		return pagebale;
	}

	public static Pageable getPage(int page, int rows) {
		Pageable pagebale = new PageRequest(page - 1, rows);
		return pagebale;
	}

	public static Object getTypedValue(Class clazz, String value,
			String ruleOperator) {
		if (ClassUtils.isAssignable(clazz, Long.class)) {
			return Long.parseLong(value);
		} else if (ClassUtils.isAssignable(clazz, Boolean.class)) {
			return Boolean.valueOf(value);
		} else if (ClassUtils.isAssignable(clazz, Integer.class)) {
			return Integer.parseInt(value);
		} else {
			String retunValue = new String(value);
			if ("cn".equals(ruleOperator)) {
				retunValue = "%" + retunValue + "%";
			} else if ("bw".equals(ruleOperator)) {
				retunValue = retunValue + "%";
			} else if ("ew".equals(ruleOperator)) {
				retunValue = "%" + retunValue;
			}
			return retunValue;
		}

	}
}
