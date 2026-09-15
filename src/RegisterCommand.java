import java.util.Objects;
import java.util.Set;

public class RegisterCommand extends Command {
    public RegisterCommand() {
        super("register",
              "register <USERNAME> <PASSWORD> <PASSWORD>",
              "registers an account",
              Set.of(UserRole.GUEST, UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) {
        if (!Objects.equals(tokens[2], tokens[3])) {
            System.out.println("Error! Passwords don't match. Retry again.");
            return;
        }

        UserManager.INSTANCE.registerUser(tokens[1], tokens[2]);
    }
}