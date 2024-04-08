package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.library.api.libraryapi.repositories.RoleRepository;

@Service
public class RoleService {
    
    RoleRepository roleRepository;

    @Autowired 
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(UUID id) throws NotFoundException {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            throw new NotFoundException();
        }
        return roleOpt.get();
    }

    public void deleteRoleById(UUID id) {
        roleRepository.deleteById(id);
    }

    public Role updateRole(UUID id, Role role) {
        role.setId(id);
        return roleRepository.save(role);
    }
}
