import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public enum Cli {
    INSTANCE;

    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    void addCommand(Command cmd) {
        this.commands.put(cmd.getName(), cmd);
    }

    void acceptInput() {
        System.out.print(">> ");
        String userInput = scanner.nextLine();
        String[] tokens = userInput.trim().split("\\s+");

        if (commands.containsKey(tokens[0])) {
            commands.get(tokens[0]).execute(tokens);
        } else {
            System.out.println("Error! Couldn't find command: " + tokens[0]);
        }
    }

    Cli() {
        // Users related commands
        addCommand(new LoginCommand());
        addCommand(new RegisterCommand());
        addCommand(new HelpCommand());
        addCommand(new LogoutCommand());
        addCommand(new QuitCommand());

        // Games related commands
        addCommand(new ListCommand());
        addCommand(new AddCommand());
        addCommand(new RemoveCommand());
        addCommand(new EditCommand());
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
