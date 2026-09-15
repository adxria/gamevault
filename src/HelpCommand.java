import java.util.Set;

public class HelpCommand extends Command {
    HelpCommand() {
        // TODO(adoria): make a way to omit usage for the case when the command doesn't have a special usage instructions
        super("help",
              "help",
              "shows this message",
              Set.of(UserRole.GUEST, UserRole.DEFAULT, UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) {
        System.out.println("Available commands:");

        for (Command cmd : Cli.INSTANCE.getCommands().values()) {
            if (cmd.getAllowedRoles().contains(UserManager.INSTANCE.getCurrentUser().getRole())) {
                System.out.println("   " + cmd.getUsage() + " - " + cmd.getDescription());
            }
        }
    }
}