package com.telenoetica.jpa.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface GenericQueryExecutorDAO {

	  <T> List<T> executeQuery(String ejbql,Class<T> clazz);
	  
	  <T> Page<T> executeQuery(String ejbql,Class<T> clazz,int page,int pageSize);

	  <T> Page<T> executeQuery(String ejbql, Class<T> clazz, Map<String, Object> params, int page, int pageSize);

	   <T> List<T> executeQuery(String ejbql, Class<T> clazz, Map<String, Object> params);

}
