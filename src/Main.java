public class Main {
    public static void main(String[] args) {
        final String APP_NAME = "Gametracker";
        final String APP_VERSION = "1.0";
        System.out.println(APP_NAME + " v" + APP_VERSION);
        System.out.println("=======");
        System.out.println("Login or create a new user");
        Cli.INSTANCE.getCommands().get("help").execute(null);

        while (true) {
            Cli.INSTANCE.acceptInput();
        }
    }
}