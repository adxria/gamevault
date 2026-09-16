package org.gamevault;

public class User {
    String name;
    String password;
    UserRole role;

    // NOTE(adoria): this is a nameless and passwordless constructor for guest user. Used only on login prompt.
    public User() {
        this.role = UserRole.GUEST;
    }

    public User(String name, String password) {
        // FIXME(adoria): what if a password of less than 8 chars was provided?
        this.name = name;
        this.password = password;
        this.role = UserRole.DEFAULT;
    }

    public void elevateRole() {
        this.role = UserRole.ADMIN;
    }

    public void revokeRole() {
        this.role = UserRole.DEFAULT;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }
}