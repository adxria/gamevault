import java.util.HashMap;
import java.util.Objects;

public enum UserManager {
    INSTANCE;

    private final HashMap<String, String> users = new HashMap<>();
    private String currentUser = "";

    UserManager() {
        this.users.put("admin", "password");
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void signIn(String username, String password) {
        if ( !Objects.equals(users.get(username), password) ) {
            throw new AuthenticationException("Invalid username or password.");
        }

        this.currentUser = username;
    }

    public void registerUser(String username, String password) {
        if (this.users.containsKey(username)) {
            throw new AuthenticationException("This username is already taken.");
        }

        this.users.put(username, password);
    }
}