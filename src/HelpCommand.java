public class HelpCommand extends Command {
    HelpCommand() {
        // TODO(adoria): make a way to omit usage for the case when the command doesn't have a special usage instructions
        super("help",
              "help",
              "shows this message");
    }

    @Override
    void execute(String[] tokens) {
        for (Command cmd : Cli.INSTANCE.getCommands().values()) {
            System.out.println("   " + cmd.getUsage() + " - " + cmd.getDescription());
        }
    }
}