package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.Client;
import com.telenoetica.jpa.repositories.ClientDAO;
import com.telenoetica.service.ClientService;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientDAO clientDAO;
  
  @Override
  public Client retrieve(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Client saveOrUpdate(Client baseEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Client baseEntity) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Client> getClients() {
    // TODO Auto-generated method stub
    return clientDAO.findAll();
  }

}
