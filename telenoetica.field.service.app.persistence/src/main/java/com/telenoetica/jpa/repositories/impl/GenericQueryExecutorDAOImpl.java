/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;

/**
 * The Class GenericQueryExecutorDAOImpl.
 *
 * @author  Shiv Prasad Khillar
 */
@Repository
public class GenericQueryExecutorDAOImpl implements GenericQueryExecutorDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(GenericQueryExecutorDAOImpl.class);

	/** The entity manager. */
	@PersistenceContext(unitName = "springJpaPersistenceUnit")
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.telenoetica.jpa.repositories.GenericQueryExecutorDAO#executeQuery(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> List<T> executeQuery(String ejbql, Class<T> clazz) {
		return executeQuery(ejbql, clazz, null);
	}

	/* (non-Javadoc)
	 * @see com.telenoetica.jpa.repositories.GenericQueryExecutorDAO#executeQuery(java.lang.String, java.lang.Class, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executeQuery(String ejbql, Class<T> clazz,
			Map<String, Object> params) {
		LOGGER.debug("Executing Query.." + ejbql);
		Query query = entityManager.createQuery(ejbql, clazz);
		setParameters(query, params);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.telenoetica.jpa.repositories.GenericQueryExecutorDAO#executeQuery(java.lang.String, java.lang.Class, int, int)
	 */
	@Override
	public <T> Page<T> executeQuery(String ejbql, Class<T> clazz, int page,
			int pageSize) {
		return executeQuery(ejbql, clazz, null, page, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.telenoetica.jpa.repositories.GenericQueryExecutorDAO#executeQuery(java.lang.String, java.lang.Class, java.util.Map, int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> Page<T> executeQuery(String ejbql, Class<T> clazz,
			Map<String, Object> params, int page, int pageSize) {
		LOGGER.debug("Executing Query.." + ejbql);
		Query countQuery = entityManager
				.createQuery("select count(*) " + ejbql);
		setParameters(countQuery, params);

		Long total = (Long) countQuery.getSingleResult();
		Query query = entityManager.createQuery(ejbql, clazz);
		setParameters(query, params);
		int first = (page - 1) * pageSize;
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		List<T> collection = query.getResultList();
		PageRequest pageRequest = new PageRequest(page - 1, pageSize);
		Page<T> finalResult = new PageImpl<T>(collection, pageRequest, total);
		return finalResult;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param query the query
	 * @param params the params
	 */
	private void setParameters(Query query, Map<String, Object> params) {
		if (MapUtils.isNotEmpty(params)) {
			for (Map.Entry<String, Object> substitute : params.entrySet()) {
				if(ClassUtils.isAssignableValue(Date.class, substitute.getValue())){
					query.setParameter(substitute.getKey(), (Date)substitute.getValue(), TemporalType.DATE);
				}else{
					query.setParameter(substitute.getKey(), substitute.getValue());
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.telenoetica.jpa.repositories.GenericQueryExecutorDAO#executeProjectedQuery(java.lang.String)
	 */
	@Override
	public <T> List<T> executeProjectedQuery(String ejbql) {
		return executeProjectedQuery(ejbql, null);

	}

	/* (non-Javadoc)
	 * @see com.telenoetica.jpa.repositories.GenericQueryExecutorDAO#executeProjectedQuery(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executeProjectedQuery(String ejbql,Map<String, Object> params) {
		Query query = entityManager.createQuery(ejbql);
		setParameters(query, params);
		return query.getResultList();

	}

}
