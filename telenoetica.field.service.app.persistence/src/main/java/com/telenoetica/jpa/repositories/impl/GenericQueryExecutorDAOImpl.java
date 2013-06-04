package com.telenoetica.jpa.repositories.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;

@Repository
public class GenericQueryExecutorDAOImpl implements GenericQueryExecutorDAO {

	private static final Logger LOGGER = Logger
			.getLogger(GenericQueryExecutorDAOImpl.class);

	@PersistenceContext(unitName = "springJpaPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public <T> List<T> executeQuery(String ejbql, Class<T> clazz) {
		return executeQuery(ejbql, clazz, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executeQuery(String ejbql, Class<T> clazz,
			Map<String, Object> params) {
		LOGGER.debug("Executing Query.." + ejbql);
		Query query = entityManager.createQuery(ejbql, clazz);
		setParameters(query, params);
		return query.getResultList();
	}

	@Override
	public <T> Page<T> executeQuery(String ejbql, Class<T> clazz, int page,
			int pageSize) {
		return executeQuery(ejbql, clazz, null, page, pageSize);
	}

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

	private void setParameters(Query query, Map<String, Object> params) {
		if (MapUtils.isNotEmpty(params)) {
			for (Map.Entry<String, Object> substitute : params.entrySet()) {
				query.setParameter(substitute.getKey(), substitute.getValue());
			}
		}
	}

}
