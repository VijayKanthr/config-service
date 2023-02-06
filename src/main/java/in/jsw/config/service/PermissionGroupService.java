package in.jsw.config.service;

import in.jsw.config.model.Permission;
import in.jsw.config.model.Role;
import in.jsw.config.model.User;

import java.util.List;

public interface PermissionGroupService {

    Permission create(Permission permission);
    List<Permission> findAll() throws InterruptedException;
    void mapPermissionGroupToScreen(Long permissionId, Long screenId);



}
