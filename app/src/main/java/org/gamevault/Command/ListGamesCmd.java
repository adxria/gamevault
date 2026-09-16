package org.gamevault.Command;

import org.gamevault.GameManager;
import org.gamevault.UserRole;

import java.util.Set;

public class ListGamesCmd extends Cmd {
    public ListGamesCmd() {
        super(
                "list",
                "lists games in the library",
                Set.of(UserRole.DEFAULT, UserRole.ADMIN)
        );
    }

    @Override
    public CmdResult execute(String rawArgs) {
        return CmdResult.success(
                "{id} Game's title\n" + "===================\n" + GameManager.INSTANCE.listGames() + '.'
        );
    }
}
