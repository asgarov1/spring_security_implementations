package com.asgarov.lesson6.config;

import com.asgarov.lesson6.security.filter.UsernamePasswordAuthFilter;
import com.asgarov.lesson6.security.provider.OtpAuthProvider;
import com.asgarov.lesson6.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Order(1)
@Configuration
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private OtpAuthProvider otpAuthProvider;

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(usernamePasswordAuthProvider)
                .authenticationProvider(otpAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
    }
}
