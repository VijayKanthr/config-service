package in.jsw.config.controller;

import in.jsw.config.model.Role;
import in.jsw.config.service.RoleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role roleObj = roleService.create(role);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("roles", "/roles" + roleObj.getId().toString());
        return new ResponseEntity<>(roleObj, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<Role>> getAllRoles() throws InterruptedException{
        List<Role> roleList = roleService.findAll();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> mapRoleToPermissionGroup(@RequestParam Long roleId, @RequestParam Long permissionId) {
        roleService.mapRoleToPermissionGroup(roleId,permissionId);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Mapping Done", HttpStatus.OK);
    }




}
