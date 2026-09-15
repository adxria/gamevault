import java.util.Set;

public class LogoutCommand extends Command {
    public LogoutCommand() {
        super("logout",
                "logout",
                "logs out from the account",
                Set.of(UserRole.DEFAULT, UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) {
        UserManager.INSTANCE.logout();

        // FIXME(adoria): This should not be here...
        System.out.println("Successfully logged out!");
        System.out.println("Awaiting next command...");
        Cli.INSTANCE.getCommands().get("help").execute(null);
    }
}
