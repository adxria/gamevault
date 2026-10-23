package dev.adoria.gamevault.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class GameVaultUserDetails extends org.springframework.security.core.userdetails.User {
    private final Long userId;

    public GameVaultUserDetails(Long userId, String username, String passwordHash) {
        super(username, passwordHash, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }
}
