package org.gamevault.Command;

import org.gamevault.User;
import org.gamevault.UserManager;

import java.util.Objects;

public class SignUpCmd extends Cmd {
    public SignUpCmd() {
        super("sign-up", "Registers a new user.", "sign-up <USERNAME> <PASSWORD> <CONFIRM>");
    }

    @Override
    public CmdResult execute() {
        // TODO(adoria): Show help for this particular command. Can be implemented by extending the functionality of the
        //  help command
        return CmdResult.failure("This command should have arguments.");
    }

    @Override
    public CmdResult execute(String rawArgs) {
        String[] args = rawArgs.split("\\s+");

        // FIXME(adoria): extra or few arguments error...

        if (!Objects.equals(args[1], args[2])) {
            return CmdResult.failure("Passwords do not match.");
        }

        UserManager.INSTANCE.addUser(new User(args[0], args[1]));
        return CmdResult.success("Successfully registered user " + args[0] + ".\n");
    }
}
