package com.library.api.libraryapi.controllers;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.RoleService;
import com.library.api.libraryapi.entities.Role;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/api/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/api/roles/{id}")
    public Role getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/api/roles/add")
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @PostMapping("/api/roles/update/{id}")
    public Role updateRole(@PathVariable UUID id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/api/roles/delete/{id}")
    public void deleteRole(@PathVariable UUID id) {
        roleService.deleteRoleById(id);
    }
}
