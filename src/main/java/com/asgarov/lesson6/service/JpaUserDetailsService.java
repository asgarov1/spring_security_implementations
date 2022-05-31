package com.asgarov.lesson6.service;

import com.asgarov.lesson6.entity.User;
import com.asgarov.lesson6.repository.UserRepository;
import com.asgarov.lesson6.security.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username '" + username + "' not found!")
                );
        return new SecurityUser(user);
    }
}
