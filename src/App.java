import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private final ArrayList<Game> games = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        showHelp();

        while (true) {
            promptAndParseInput();
        }
    }

    private void showHelp() {
        // TODO(adoria): pull the version out to a const variable
        // TODO(adoria): might do this for the app name as well.
        System.out.println("Gametracker v1.0");
        System.out.println("================");
        System.out.println("Usage: command [options]");
        System.out.println("Available commands:");
        System.out.println("  help - show this message");
        System.out.println("  list - print all games");
        System.out.println("  add <GAME_TITLE> - add a game to the list");
        System.out.println("      ^ Spaces in the title are allowed.");
        System.out.println("  rm <GAME_ID> - delete a game by id");
        System.out.println("  edit <GAME_ID> <NEW_TITLE> - edit title of a game by id");
        System.out.println("  quit / q - close app");
    }

    private void promptAndParseInput() {
        System.out.print(">> ");
        String userInput = scanner.nextLine();

        int spaceIndex = userInput.indexOf(' ');
        String cmd = "";
        String args = "";
        if (spaceIndex != -1) {
            cmd = userInput.substring(0, spaceIndex);
            args = userInput.substring(spaceIndex + 1);
        } else {
            cmd = userInput;
        }

        if ("help".equals(cmd)) {
            if (!args.isBlank()) {
                System.out.println("Error! Command " + cmd + " cannot take any arguments: \"" + args + "\"");
                return;
            }
            this.showHelp();
        } else if ("list".equals(cmd)) {
            if (!args.isBlank()) {
                System.out.println("Error! Command " + cmd + " cannot take any arguments: \"" + args + "\"");
                return;
            }
            listGames();
        } else if ("add".equals(cmd)) {
            if (args.isBlank()) {
                System.out.println("Error! User must specify the game's title as an argument.");
                System.out.println("  Usage: add <GAME_TITLE>");
                return;
            }

            games.add(new Game(args));
        } else if ("rm".equals(cmd)) {
            if (args.isBlank()) {
                System.out.println("Error! User must specify the game's id as an argument.");
                System.out.println("  Usage: rm <GAME_ID>");
                System.out.println("  Hint: User might want to use list command first to get the id");
                // TODO(adoria): this is unpleasant, we need a find command as well
                return;
            }

            int inputedId = -1;
            try {
                inputedId = Integer.parseInt(args);
            } catch (NumberFormatException e) {
                System.out.println("Error! The game's id must be a number: " + args);
                return;
            }

            int finalInputedId = inputedId;
            boolean result = this.games.removeIf(game -> game.getId() == finalInputedId);
            if (result) {
                System.out.println("Success!");
            } else {
                System.out.println("Error! Couldn't delete the game...");
            }
        } else if ("edit".equals(cmd)) {
            if (args.isBlank()) {
                System.out.println("Error! User must specify the game's id and new title as arguments.");
                System.out.println("  Usage: edit <GAME_ID> <NEW_TITLE>");
                System.out.println("  Hint: User might want to use list command first to get the id");
                // TODO(adoria): this is unpleasant, we need a find command as well
                return;
            }

            int innerSpaceIndex = args.indexOf(' ');
            if (innerSpaceIndex == -1) {
                System.out.println("Error! User must specify the game's id and new title as arguments.");
                System.out.println("  Usage: edit <GAME_ID> <NEW_TITLE>");
                System.out.println("  Hint: User might want to use list command first to get the id");
                // TODO(adoria): this is unpleasant, we need a find command as well
                return;
            }
            String inputIdStr = args.substring(0, innerSpaceIndex);
            String newTitle = args.substring(innerSpaceIndex + 1);

            int inputedId = -1;
            try {
                inputedId = Integer.parseInt(inputIdStr);
            } catch (NumberFormatException e) {
                System.out.println("Error! The game's id must be a number: " + inputIdStr);
                return;
            }

            for (Game game : games) {
                if (game.getId() != inputedId) continue;
                game.setTitle(newTitle);
                System.out.println("Success!");
                return;
            }

            System.out.println("Error! No game with id " + inputedId + " has been found.");
        } else if ("quit".equals(cmd) || "q".equals(cmd)) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else {
            System.out.println("Error! Couldn't find command: " + cmd);
        }
    }

    private void listGames() {
        if (this.games.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        for (Game game : this.games) {
            System.out.println(game.getId() + ". " + game.getTitle());
        }
    }
}