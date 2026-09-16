package org.gamevault.Command;

import org.gamevault.GameManager;
import org.gamevault.UserRole;

import java.util.Set;

public class EditGameCmd extends Cmd {
    public EditGameCmd() {
        super(
                "edit",
                "edit <GAME_ID> <GAME_TITLE>",
                "Edits title of a game specified by ID (see list).",
                Set.of(UserRole.ADMIN)
        );
    }

    @Override
    public CmdResult execute(String rawArgs) {
        int firstSpaceId = rawArgs.indexOf(' ');

        if (firstSpaceId == -1) {
            return CmdResult.failure("The edit command should take two arguments.");
        }

        int id = Integer.parseInt(rawArgs.substring(0, firstSpaceId));
        String title = rawArgs.substring(firstSpaceId + 1).trim();

        GameManager.INSTANCE.editGame(id, title);
        return CmdResult.success("Successfully renamed game to " + title + " by id " + id + ".\n");
    }
}
