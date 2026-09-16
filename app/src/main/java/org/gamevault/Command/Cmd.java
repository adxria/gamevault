package org.gamevault.Command;

public abstract class Cmd {
    String name;
    String usage;
    String description;

    Cmd(String name, String description) {
        this.name = name;
        this.usage = this.name;
        this.description = description;
    }

    Cmd(String name, String description, String usage) {
        this.name = name;
        this.usage = usage;
        this.description = description;
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

    // TODO(adoria): I need to make my auto-reformatter crop long comments to fit into 120 chars

    // NOTE(adoria): this will be reframed. It's not clear for me right now that we need two execute functions with and
    // without the arguments. Sure, it may look clean, but may lead to duplicated code when the logic is similar in both
    // functions.
    public CmdResult execute() {
        return this.execute(null);
    }

    public abstract CmdResult execute(String rawArgs);
}
