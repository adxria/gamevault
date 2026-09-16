package org.gamevault.Command;

import org.gamevault.GameManager;
import org.gamevault.UserRole;

import java.util.Set;

public class AddGameCmd extends Cmd {
    public AddGameCmd() {
        super(
                "add",
                "add <GAME_TITLE>",
                "Adds a game into the library.",
                Set.of(UserRole.ADMIN)
        );
    }

    @Override
    public CmdResult execute(String rawArgs) {
        GameManager.INSTANCE.addGame(rawArgs);
        return CmdResult.success("Successfully added the game: " + rawArgs + '\n');
    }
}
