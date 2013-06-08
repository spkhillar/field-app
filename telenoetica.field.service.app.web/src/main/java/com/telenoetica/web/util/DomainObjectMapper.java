/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.BaseEntity;
import com.telenoetica.jpa.entities.User;

/**
 * The Class DomainObjectMapper.
 *
 * @author  Shiv Prasad Khillar
 */
public class DomainObjectMapper {

	/**
	 * List entities.
	 *
	 * @param pages the pages
	 * @return the list
	 */
	public static List<Object> listEntities(Page<?> pages) {
		List<Object> list = new ArrayList<Object>();
		for (Object baseEntity : pages) {
			list.add(baseEntity);
		}
		return list;
	}
}
