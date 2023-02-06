package in.jsw.config.service;

import in.jsw.config.model.Role;

import java.util.List;

public interface RoleService {

    Role create(Role role);
    List<Role> findAll() throws InterruptedException;

    void mapRoleToPermissionGroup(Long roleId, Long permissionId);

}
