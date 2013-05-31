package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Spare;

public interface SpareService extends BaseService<Spare>{
	List<Spare> getSpares();
}
