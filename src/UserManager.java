import java.util.HashMap;
import java.util.Objects;

public enum UserManager {
    INSTANCE;

    private final HashMap<String, User> users = new HashMap<>();
    private final User guestInstance = new User();
    private User currentUser;

    UserManager() {
        this.registerUser("admin", "password");
        this.users.get("admin").setRole(UserRole.ADMIN);
        this.logout();
    }

    public User getCurrentUser() { return currentUser; }

    public void signIn(String username, String password) {
        if ( !Objects.equals(users.get(username).getPassword(), password) ) {
            throw new AuthenticationException("Invalid username or password.");
        }

        this.currentUser = users.get(username);
        // FIXME(adoria): this should not be here:
        System.out.println("Hello, " + UserManager.INSTANCE.getCurrentUser().getUsername() + "!");
        Cli.INSTANCE.getCommands().get("help").execute(null);
    }

    public void registerUser(String username, String password) {
        if (this.users.containsKey(username)) {
            throw new AuthenticationException("This username is already taken.");
        }

        User user = new User(username, password, UserRole.DEFAULT);
        this.users.put(user.getUsername(), user);
    }

    public void logout() { this.currentUser = this.guestInstance; }
}