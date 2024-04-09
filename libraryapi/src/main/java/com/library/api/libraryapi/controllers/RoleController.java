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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;


@RequestMapping("/api/roles")
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable UUID id) throws NotFoundException {
        return roleService.getRoleById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add")
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/update/{id}")
    public Role updateRole(@PathVariable UUID id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable UUID id) throws NotFoundException {
        roleService.deleteRoleById(id);
    }
}
