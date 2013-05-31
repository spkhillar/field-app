package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.MaintenanceVisitCategory;

public interface MaintenanceVisitCategoryService extends BaseService<MaintenanceVisitCategory> {

  List<MaintenanceVisitCategory> getCategories();
}
