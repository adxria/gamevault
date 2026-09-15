import java.util.Set;

public class EditCommand extends Command {
    EditCommand() {
        super("edit",
                "edit <GAME_ID> <GAME_TITLE>",
                "edits game's title",
                Set.of(UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) {
        int id = Integer.parseInt(tokens[1]);
        GameManager.INSTANCE.editGame(id, tokens[2]);
    }
}
