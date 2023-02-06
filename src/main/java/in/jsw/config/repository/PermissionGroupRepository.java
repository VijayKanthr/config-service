package in.jsw.config.repository;

import in.jsw.config.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends JpaRepository<Permission,Long> {
}
