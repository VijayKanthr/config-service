package in.jsw.config.controller;


import in.jsw.config.model.Permission;
import in.jsw.config.service.PermissionGroupService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/permission")
public class PermissionGroupController {

    private final PermissionGroupService permissionGroupService;


    public PermissionGroupController(PermissionGroupService permissionGroupService) {
        this.permissionGroupService = permissionGroupService;
    }

    @PostMapping
    public ResponseEntity<Permission> addPermission(@RequestBody Permission permission) {
        Permission permissionObj = permissionGroupService.create(permission);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("permissions", "/permissions" + permissionObj.getId().toString());
        return new ResponseEntity<>(permissionObj, httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<String> mapPermissionGroupToScreen(@RequestParam Long permissionId, @RequestParam Long screenId) {
        permissionGroupService.mapPermissionGroupToScreen(permissionId,screenId);
        // userService.update(id, user);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Mapping Done", HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<Permission>> getAllPermissions() throws InterruptedException{
        List<Permission> permissionList = permissionGroupService.findAll();
        return new ResponseEntity<>(permissionList, HttpStatus.OK);
    }
}
