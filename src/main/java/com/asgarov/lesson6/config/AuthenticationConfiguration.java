package com.asgarov.lesson6.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfiguration {

    private final List<AuthenticationProvider> authenticationProviders;

    @Bean
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProviders);
    }
}
