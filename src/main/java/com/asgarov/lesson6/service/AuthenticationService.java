package com.asgarov.lesson6.service;

import com.asgarov.lesson6.entity.Otp;
import com.asgarov.lesson6.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final OtpRepository otpRepository;

    public void authenticate(Authentication authentication) {
        authenticationManager.authenticate(authentication);
    }

    public void saveOtp(Otp otpEntity) {
        otpRepository.save(otpEntity);
    }
}
