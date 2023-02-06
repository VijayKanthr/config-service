package in.jsw.config.service;

import in.jsw.config.model.Permission;
import in.jsw.config.model.Role;
import in.jsw.config.model.Screen;
import in.jsw.config.repository.PermissionGroupRepository;
import in.jsw.config.repository.RoleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @PersistenceContext
    private EntityManager entityManager;

    private final RoleRepository roleRepository;

    private final PermissionGroupRepository permissionGroupRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionGroupRepository permissionGroupRepository) {
        this.roleRepository = roleRepository;
        this.permissionGroupRepository = permissionGroupRepository;
    }

    @Override
    //@CachePut(value = "roles")
    @CacheEvict(value = "roles",allEntries = true)
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Cacheable(value = "roles")
    public List<Role> findAll() throws InterruptedException {
        // Simulate server pressure and high computation
        Thread.sleep(3000);
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public void mapRoleToPermissionGroup(Long roleId, Long permissionId) {

        // TODO: 13-01-2023 this will change based on requirement.

        Optional<Permission> permission= permissionGroupRepository.findById(permissionId);

        if (permission.isPresent()){

            Role role =roleRepository.findById(roleId).get();
            role.addPermission(permission.get());
            roleRepository.save(role);
        }

    }
}
