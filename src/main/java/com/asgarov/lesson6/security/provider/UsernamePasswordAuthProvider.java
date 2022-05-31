package com.asgarov.lesson6.security.provider;

import com.asgarov.lesson6.security.authentication.UsernamePasswordAuthentication;
import com.asgarov.lesson6.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    private final JpaUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthentication(username, password, List.of(() -> "read"));
        }

        throw new BadCredentialsException("Username / Password combination didn't match!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
