package com.asgarov.lesson6.config;

import com.asgarov.lesson6.security.filter.TokenAuthenticationFilter;
import com.asgarov.lesson6.security.filter.UsernamePasswordAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

    private final UsernamePasswordAuthFilter usernamePasswordAuthFilter;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(tokenAuthenticationFilter, BasicAuthenticationFilter.class);
    }
}
