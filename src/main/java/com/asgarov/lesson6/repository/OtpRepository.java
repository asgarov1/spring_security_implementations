package com.asgarov.lesson6.repository;

import com.asgarov.lesson6.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByUsernameAndOtp(String username, String otp);
}
