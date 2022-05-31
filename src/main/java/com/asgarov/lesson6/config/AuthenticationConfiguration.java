package com.asgarov.lesson6.config;

import com.asgarov.lesson6.security.provider.OtpAuthProvider;
import com.asgarov.lesson6.security.provider.UsernamePasswordAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.List;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    protected AuthenticationManager authenticationManager(
            UsernamePasswordAuthProvider usernamePasswordAuthProvider,
            OtpAuthProvider otpAuthProvider) {
        return new ProviderManager(List.of(usernamePasswordAuthProvider, otpAuthProvider));
    }
}
