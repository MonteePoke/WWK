package kurlyk.controllers;

import kurlyk.model.Role;
import kurlyk.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{role-name}")
    public Role getRole(@PathVariable("role-name") String roleName) {
        return roleService.findByName(roleName).orElseThrow(RuntimeException::new);
    }
}
