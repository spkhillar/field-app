package com.telenoetica.service;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.Site;

public interface SiteService extends BaseService<Site>{
	
	public Page<Site> getSites(Integer pageNumber);
}
