public class LoginCommand extends Command {
    public LoginCommand() {
        super("login",
              "login <USERNAME> <PASSWORD>",
              "login to the system");
    }

    @Override
    void execute(String[] tokens) {
        UserManager.INSTANCE.signIn(tokens[1], tokens[2]);
    }
}