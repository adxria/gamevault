import java.util.Set;

public abstract class Command {
    private String name;
    private String usage;
    private String description;
    private final Set<UserRole> allowedRoles;

    public Command(String name, String usage, String description, Set<UserRole> allowedRoles) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.allowedRoles = allowedRoles;
    }

    public String getName() { return name; }
    public String getUsage() { return usage; }
    public String getDescription() { return description; }
    public Set<UserRole> getAllowedRoles() { return allowedRoles; }

    // FIXME(adoria): This should validate the user's role first
    abstract void execute(String[] tokens);
}