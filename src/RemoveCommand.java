import java.util.Set;

public class RemoveCommand extends Command {
    RemoveCommand() {
        super("rm",
                "rm <GAME_ID>",
                "removes a game from the library by id",
                Set.of(UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) {
        int id = Integer.parseInt(tokens[1]);
        GameManager.INSTANCE.removeGame(id);
    }
}
