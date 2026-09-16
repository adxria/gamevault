package org.gamevault.Command;

import org.gamevault.Cli;
import org.gamevault.User;
import org.gamevault.UserManager;
import org.gamevault.UserRole;

import java.util.Set;

public class HelpCmd extends Cmd {
    public HelpCmd() {
        super(
                "help",
                "shows this message.",
                Set.of(UserRole.GUEST, UserRole.DEFAULT, UserRole.ADMIN)
        );

    }

    @Override
    public CmdResult execute(String rawArgs) {
        StringBuilder sb = new StringBuilder("Available commands:\n");
        User currentUser = UserManager.INSTANCE.getCurrentUser();

        // TODO(adoria): no commands available case??

        for (Cmd cmd : Cli.INSTANCE.getCommands().values()) {
            if (!cmd.getAllowedRoles().contains(currentUser.getRole())) {
                continue;
            }

            sb.append("   ").append(cmd.getUsage()).append(" : ").append(cmd.getDescription()).append('\n');
        }

        return CmdResult.success(sb.toString());
    }
}