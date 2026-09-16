package org.gamevault.Command;

import org.gamevault.UserRole;

import java.util.Set;

public abstract class Cmd {
    private final String name;
    private final String usage;
    private final String description;
    private final Set<UserRole> allowedRoles;


    Cmd(String name, String usage, String description, Set<UserRole> allowedRoles) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.allowedRoles = allowedRoles;
    }

    Cmd(String name, String description, Set<UserRole> allowedRoles) {
        this(name, name, description, allowedRoles);
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public Set<UserRole> getAllowedRoles() {
        return allowedRoles;
    }

    // TODO(adoria): I need to make my auto-reformatter crop long comments to fit into 120 chars

    // NOTE(adoria): this will be reframed. It's not clear for me right now that we need two execute functions with and
    // without the arguments. Sure, it may look clean, but may lead to duplicated code when the logic is similar in both
    // functions.
    public CmdResult execute() {
        return this.execute(null);
    }

    public abstract CmdResult execute(String rawArgs);
}