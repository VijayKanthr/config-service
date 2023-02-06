package in.jsw.config.service;


import in.jsw.config.model.Modules;
import in.jsw.config.model.Role;
import in.jsw.config.model.User;

import in.jsw.config.repository.RoleRepository;
import in.jsw.config.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

   // private final ModuleRepository moduleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository= roleRepository;

    }


    @Override
    @CacheEvict(value = "users",allEntries = true)
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void mapUserRole(Long userId, Long roleId) {

        // TODO: 11-01-2023 this will change based on requirement.
        Optional<Role> roleObj= roleRepository.findById(roleId);
        if (roleObj.isPresent()){

            User userObj =userRepository.findById(userId).get();
            userObj.addRole(roleObj.get());
            userRepository.save(userObj);
        }
    }

    @Override
    @Cacheable(value = "users")
    public List<User> findAll() throws InterruptedException {
        // Simulate server pressure and high computation
        Thread.sleep(3000);
        return (List<User>) userRepository.findAll();
    }

   /* @Override
    public void mapUserToModule(Long userId, User.ModuleData module) {

        Optional<User> user= userRepository.findById(userId);
        if (user.isPresent()){
            User userObj= user.get();
            userObj.addModule(module);
            userRepository.save(userObj);
        }

    }*/

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void mapUserToModule(Long userId, Modules module) {
        Optional<User> user= userRepository.findById(userId);
        if (user.isPresent()){

            User userObj= user.get();
           // userObj.addModule(module);
            //module.setUser(userObj);
            userObj.setModules(module);
           userRepository.save(userObj);

        }
    }


}
