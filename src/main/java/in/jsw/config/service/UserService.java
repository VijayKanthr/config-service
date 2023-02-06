package in.jsw.config.service;

//import in.jsw.config.model.Module;
import in.jsw.config.model.Modules;
import in.jsw.config.model.User;

import java.util.List;

public interface UserService {

    User create(User user);
    void mapUserRole(Long userId, Long roleId);
    List<User> findAll() throws InterruptedException;
    void mapUserToModule(Long userId, Modules module);
}
