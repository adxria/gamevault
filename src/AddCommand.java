import java.util.Set;

public class AddCommand extends Command {
    AddCommand() {
        super("add",
              "add <GAME_TITLE>",
              "adds a game into the library",
              Set.of(UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) { GameManager.INSTANCE.addGame(tokens[1]); }
}
