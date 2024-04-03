package com.library.api.libraryapi.security;

import com.library.api.libraryapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        var user = userRepository.findById(UUID.fromString(id));

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No such user with id " + id);
        }

        var roleName = user.get().getRole().getName();

        return new User(
                user.get().getId().toString(),
                user.get().getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName)));
    }
}