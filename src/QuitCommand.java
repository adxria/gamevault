import java.util.Set;

public class QuitCommand extends Command {
    public QuitCommand() {
        super(
              "quit",
              "quit",
              "closes the program",
              Set.of(UserRole.GUEST, UserRole.DEFAULT, UserRole.ADMIN));
    }

    @Override
    void execute(String[] tokens) {
        System.exit(0);
    }
}
