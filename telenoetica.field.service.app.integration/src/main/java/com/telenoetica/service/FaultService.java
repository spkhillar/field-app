package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Fault;

public interface FaultService extends BaseService<Fault>{
	List<Fault> getFaults();
}
