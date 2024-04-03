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

@Component
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No such user with email " + email);
        }

        var roleName = user.get().getRole().getName();

        return new User(
                user.get().getId().toString(),
                user.get().getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName)));
    }
}