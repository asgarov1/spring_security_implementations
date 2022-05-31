package com.asgarov.lesson6.security.provider;

import com.asgarov.lesson6.entity.Otp;
import com.asgarov.lesson6.repository.OtpRepository;
import com.asgarov.lesson6.security.authentication.OtpAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OtpAuthProvider implements AuthenticationProvider {

    private final OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otpCredential = String.valueOf(authentication.getCredentials());

        Optional<Otp> otpEntity = otpRepository.findByUsernameAndOtp(username, otpCredential);
        if (otpEntity.isPresent()) {
            return new OtpAuthentication(username, otpCredential, List.of(() -> "read"));
        }

        throw new BadCredentialsException("Otp header not present!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
