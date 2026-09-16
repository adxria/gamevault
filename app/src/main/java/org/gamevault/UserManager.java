package org.gamevault;

import java.util.HashMap;
import java.util.Map;

public enum UserManager {
    INSTANCE;
    private User currentUser;
    Map<String, User> users = new HashMap<>();

    public void logInAs(String username, String password) {
        // FIXME(adoria): the user may be inexistent...
        // incorrect username error
        User user = this.users.get(username);

        if (password.equals(user.getPassword())) {
            this.currentUser = user;
        } else {
            // FIXME(adoria): Incorrect password error.
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User user) {
        this.users.put(user.name, user);
    }
}