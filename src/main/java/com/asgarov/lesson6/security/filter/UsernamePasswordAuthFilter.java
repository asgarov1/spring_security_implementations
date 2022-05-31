package com.asgarov.lesson6.security.filter;

import com.asgarov.lesson6.entity.Otp;
import com.asgarov.lesson6.security.authentication.OtpAuthentication;
import com.asgarov.lesson6.security.authentication.UsernamePasswordAuthentication;
import com.asgarov.lesson6.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if (otp == null) {
            var authentication = new UsernamePasswordAuthentication(username, password);
            authenticationService.authenticate(authentication);

            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(UUID.randomUUID().toString());
            authenticationService. saveOtp(otpEntity);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            var authentication = new OtpAuthentication(username, otp);
            authenticationService.authenticate(authentication);
            response.setHeader("Authorization", UUID.randomUUID().toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}
