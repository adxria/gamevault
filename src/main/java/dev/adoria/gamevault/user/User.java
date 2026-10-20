package dev.adoria.gamevault.user;

import jakarta.persistence.*;

import java.util.Locale;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(name = "uk_users_username", columnNames = "username"),
    @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 32)
    private String username;
    @Column(nullable = false, length = 64)
    private String displayName;
    @Column(nullable = false, length = 254)
    private String email;
    @Column(nullable = false, length = 255)
    private String passwordHash;

    // NOTE(adoria): This no-argument constructor is mandatory by hibernate
    protected User() {
    }

    public static String normalize(String input) {
        return input.toLowerCase(Locale.ROOT);
    }

    public User(String username, String displayName, String email, String passwordHash) {
        this.username = normalize(username);
        this.displayName = displayName;
        this.email = normalize(email);
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = normalize(email);
    }

    public String getEmail() {
        return email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}