import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final String APP_NAME = "Gametracker";
        final String APP_VERSION = "v1.0";
        System.out.println(APP_NAME + " v" + APP_VERSION);
        System.out.println("=======");
        System.out.println("Login or create a new user");
        Cli.INSTANCE.getCommands().get("help").execute(new String[]{"execute"});

        while (UserManager.INSTANCE.getCurrentUser().isBlank()) {
            Cli.INSTANCE.acceptInput();
        }

        System.out.println("Hello, " + UserManager.INSTANCE.getCurrentUser());
    }
}