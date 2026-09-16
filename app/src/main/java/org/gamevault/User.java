package org.gamevault;

public class User {
    String name;
    String password;

    public User(String name, String password) {
        // FIXME(adoria): what if a password of less than 8 chars was provided?
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}