package org.gamevault.Command;

import org.gamevault.UserManager;
import org.gamevault.UserRole;

import java.util.Set;

public class LogInCmd extends Cmd {
    public LogInCmd() {
        super(
                "log-in",
                "log-in <USERNAME> <PASSWORD>",
                "Logs user into the system.",
                Set.of(UserRole.GUEST)
        );
    }

    @Override
    public CmdResult execute() {
        // FIXME(adoria): no args error...
        return null;
    }

    @Override
    public CmdResult execute(String rawArgs) {
        // FIXME(adoria): All args validation...
        String[] args = rawArgs.split("\\s+");

        // FIXME(adoria): error handling...
        UserManager.INSTANCE.logIn(args[0], args[1]);

        return CmdResult.success("Successfully logged in as " + args[0] + ".\n");
    }
}