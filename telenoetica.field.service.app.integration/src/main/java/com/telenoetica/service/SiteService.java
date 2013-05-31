package com.telenoetica.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.Site;

public interface SiteService extends BaseService<Site>{
	
	public Page<Site> getSites(Integer pageNumber);
	
	List<Site> getSites();
}
