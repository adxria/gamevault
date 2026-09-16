package org.gamevault;

import java.util.HashMap;
import java.util.Map;

public enum UserManager {
    INSTANCE;
    private final User guestInstance = new User();
    private final Map<String, User> users = new HashMap<>();
    private User currentUser = guestInstance;

    UserManager() {
        User admin = new User("admin", "pass");
        admin.elevateRole();
        addUser(admin);
    }

    public void logIn(String username, String password) {
        // FIXME(adoria): the user may be inexistent...
        // incorrect username error
        User user = this.users.get(username);

        if (password.equals(user.getPassword())) {
            this.currentUser = user;
        } else {
            // FIXME(adoria): Incorrect password error.
        }
    }

    public void logOut() {
        this.currentUser = guestInstance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User user) {
        this.users.put(user.name, user);
    }
}