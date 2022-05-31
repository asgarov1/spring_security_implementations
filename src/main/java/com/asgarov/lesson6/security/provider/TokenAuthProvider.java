package com.asgarov.lesson6.security.provider;

import com.asgarov.lesson6.security.authentication.TokenAuthentication;
import com.asgarov.lesson6.security.manager.TokenManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenAuthProvider implements AuthenticationProvider {

    private final TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = String.valueOf(authentication.getName());
        boolean exists = tokenManager.contains(token);
        if (exists) {
            return new TokenAuthentication(token, null, List.of(() -> "read"));
        } else {
            throw new BadCredentialsException("No such token found!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
