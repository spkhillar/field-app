package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Client;

public interface ClientService extends BaseService<Client>{
	List<Client> getClients();
}
