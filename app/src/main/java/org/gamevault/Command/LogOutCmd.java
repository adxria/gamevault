package org.gamevault.Command;

import org.gamevault.UserManager;
import org.gamevault.UserRole;

import java.util.Set;

public class LogOutCmd extends Cmd {
    public LogOutCmd() {
        super(
                "log-out",
                "logs out from the account.",
                Set.of(UserRole.DEFAULT, UserRole.ADMIN)
        );
    }

    @Override
    public CmdResult execute(String rawArgs) {
        // FIXME(adoria): error handling...
        UserManager.INSTANCE.logOut();
        return CmdResult.success("Successfully logged out\n");
    }
}