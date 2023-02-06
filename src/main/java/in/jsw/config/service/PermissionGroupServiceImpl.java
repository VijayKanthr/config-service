package in.jsw.config.service;

import in.jsw.config.model.Permission;
import in.jsw.config.model.Screen;
import in.jsw.config.model.User;
import in.jsw.config.repository.PermissionGroupRepository;
import in.jsw.config.repository.ScreenRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService{

    @PersistenceContext
    private EntityManager entityManager;

    private  final PermissionGroupRepository permissionGroupRepository;

    private final ScreenRepository screenRepository;

    public PermissionGroupServiceImpl(PermissionGroupRepository permissionGroupRepository, ScreenRepository screenRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
        this.screenRepository = screenRepository;
    }


    @Override
    @CacheEvict(value = "permissions",allEntries = true)
    public Permission create(Permission permission) {
        return permissionGroupRepository.save(permission);
    }

    @Override
    @Cacheable(value = "permissions")
    public List<Permission> findAll() throws InterruptedException {
        Thread.sleep(3000);
        return permissionGroupRepository.findAll();
    }

    @Override
    public void mapPermissionGroupToScreen(Long permissionId, Long screenId) {

        // TODO: 13-01-2023 this will change based on requirement.

        Optional<Screen> screen= screenRepository.findById(screenId);

        if (screen.isPresent()){

            Permission permission =permissionGroupRepository.findById(permissionId).get();
            permission.addScreen(screen.get());
            permissionGroupRepository.save(permission);
        }

    }
}
