import java.util.Set;

public class LoginCommand extends Command {
    LoginCommand() {
        super("login",
              "login <USERNAME> <PASSWORD>",
              "login to the system",
              Set.of(UserRole.GUEST));
    }

    @Override
    public void execute(String[] tokens) {
        UserManager.INSTANCE.signIn(tokens[1], tokens[2]);
    }
}