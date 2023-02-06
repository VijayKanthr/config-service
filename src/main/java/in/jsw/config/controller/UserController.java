package in.jsw.config.controller;


//import in.jsw.config.model.Module;
import in.jsw.config.model.Modules;
import in.jsw.config.model.User;
import in.jsw.config.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userObj = userService.create(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("users", "/users" + userObj.getId().toString());
        return new ResponseEntity<>(userObj, httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<String> mapUserRole(@RequestParam Long userId, @RequestParam Long roleId) {
        userService.mapUserRole(userId,roleId);
        // userService.update(id, user);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Mapping Done", HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUsers() throws InterruptedException{
        List<User> usersList = userService.findAll();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @PutMapping("/module")
    public ResponseEntity<String> mapUserToModule(@RequestBody Modules module,
                                                  @RequestParam(value = "userId") Long userId) {
        userService.mapUserToModule(userId,module);
        // userService.update(id, user);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Mapping Done", HttpStatus.OK);
    }




}
