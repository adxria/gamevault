import java.util.Objects;

public class RegisterCommand extends Command {
    public RegisterCommand() {
        super("register",
              "register <USERNAME> <PASSWORD> <PASSWORD>",
              "registers an account");
    }

    @Override
    void execute(String[] tokens) {
        if (!Objects.equals(tokens[2], tokens[3])) {
            System.out.println("Error! Passwords don't match. Retry again.");
            return;
        }

        UserManager.INSTANCE.registerUser(tokens[1], tokens[2]);
    }
}