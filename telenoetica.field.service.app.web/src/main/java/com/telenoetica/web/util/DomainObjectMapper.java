package com.telenoetica.web.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.BaseEntity;
import com.telenoetica.jpa.entities.User;

public class DomainObjectMapper {
  
  public static List<BaseEntity> listEntities(Page<User> pages){
    List<BaseEntity> list = new ArrayList<BaseEntity>();
    for (BaseEntity baseEntity : pages) {
      list.add(baseEntity);
    }
    return list;
  }
}
