package org.gamevault;

import org.gamevault.Command.*;

import java.util.*;

public enum Cli {
    INSTANCE;
    final Map<String, Cmd> commands = new HashMap<>();
    final Scanner scanner = new Scanner(System.in);

    private void registerCmd(final Cmd cmd) {
        commands.put(cmd.getName(), cmd);
    }

    Cli() {
        // TODO(adoria): maybe there is a better way to do that...
        registerCmd(new HelpCmd());
        registerCmd(new SignUpCmd());
        registerCmd(new LogInCmd());
        registerCmd(new LogOutCmd());

        registerCmd(new AddGameCmd());
        registerCmd(new EditGameCmd());
        registerCmd(new ListGamesCmd());
        registerCmd(new RemoveGameCmd());
    }

    public void acceptInput() {
        System.out.print(">> ");
        String userInput = scanner.nextLine();

        String keyword;
        String args;

        int firstSpaceIndex = userInput.indexOf(' ');
        if (firstSpaceIndex == -1) {
            keyword = userInput.toLowerCase();
            args = null;
        } else {
            keyword = userInput.substring(0, firstSpaceIndex).toLowerCase();
            // TODO(adoria): where to trim?..
            args = userInput.substring(firstSpaceIndex + 1).trim();
        }

        if (!this.commands.containsKey(keyword)) {
            System.out.println("Error! Unknown command: " + keyword);
            return;
        }

        Cmd cmd = this.commands.get(keyword);
        if (!cmd.getAllowedRoles().contains(UserManager.INSTANCE.getCurrentUser().getRole())) {
            System.out.println("Error! You are not allowed to use this command.");
            return;
        }

        CmdResult result = this.commands.get(keyword).execute(args);
        if (result.isSuccess()) {
            System.out.print(result.getOutput());
        } else {
            System.out.print("Error! " + result.getOutput());
        }
    }

    public Map<String, Cmd> getCommands() {
        return commands;
    }
}
