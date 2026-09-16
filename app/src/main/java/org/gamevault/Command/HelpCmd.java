package org.gamevault.Command;

import org.gamevault.Cli;

public class HelpCmd extends Cmd {
    public HelpCmd() {
        super("help", "shows this message.");
    }

    @Override
    public CmdResult execute(String rawArgs) {
        StringBuilder sb = new StringBuilder("Available commands:\n");

        for (Cmd cmd : Cli.INSTANCE.getCommands().values()) {
            sb.append("   ").append(cmd.getUsage()).append(" : ").append(cmd.getDescription()).append('\n');
        }

        return CmdResult.success(sb.toString());
    }
}