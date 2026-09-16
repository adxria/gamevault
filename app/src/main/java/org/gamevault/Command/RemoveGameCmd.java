package org.gamevault.Command;

import org.gamevault.GameManager;
import org.gamevault.UserRole;

import java.util.Set;

public class RemoveGameCmd extends Cmd {
    public RemoveGameCmd() {
        super(
                "rm",
                "rm <GAME_ID>",
                "Removes a game by id.",
                Set.of(UserRole.ADMIN)
        );
    }

    @Override
    public CmdResult execute(String rawArgs) {
        // FIXME(adoria): needs proper validation
        int id = Integer.parseInt(rawArgs);
        String title = GameManager.INSTANCE.getGame(id).getTitle();
        GameManager.INSTANCE.removeGame(id);
        return CmdResult.success("Successfully removed game " + title + " by id: " + id + ".\n");
    }
}