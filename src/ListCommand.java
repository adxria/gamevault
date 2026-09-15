import java.util.Set;

public class ListCommand extends Command {
    ListCommand() {
        super("list",
                "list",
                "lists all the game library",
                Set.of(UserRole.DEFAULT,UserRole.ADMIN));
    }

    @Override
    public void execute(String[] tokens) { GameManager.INSTANCE.listGames(); }
}
