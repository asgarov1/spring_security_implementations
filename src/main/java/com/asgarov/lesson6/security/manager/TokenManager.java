package com.asgarov.lesson6.security.manager;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {

    private final Set<String> tokens = new HashSet<>();

    public void add(String token) {
        tokens.add(token);
    }

    public boolean contains(String token) {
        return tokens.contains(token);
    }

    public void remove(String token) {
        tokens.remove(token);
    }
}
