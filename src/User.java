public class User {
    private String username;
    private String password;
    private UserRole role;

    User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // NOTE(adoria): this is a nameless and passwordless constructor for guest user. Used only in login mode
    User() {
        this.role = UserRole.GUEST;
    }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setRole(UserRole role) { this.role = role; }
    public UserRole getRole() { return role; }
}