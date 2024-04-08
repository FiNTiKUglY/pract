package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.library.api.libraryapi.repositories.RoleRepository;

@Service
public class RoleService {
    
    RoleRepository roleRepository;

    @Autowired 
    public RoleService() {
        //Constructor for service
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(UUID id) {
        Role role = new Role();
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isPresent()) {
            role = roleOpt.get();
        }
        return role;
    }

    public void deleteRoleById(UUID id) {
        roleRepository.deleteById(id);
    }

    public Role updateRole(UUID id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }
}
