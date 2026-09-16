package org.gamevault;

public class App {
    public static void main(String[] args) {
        System.out.print(Cli.INSTANCE.getCommands().get("help").execute().getOutput());
        while (true) {
            Cli.INSTANCE.acceptInput();
        }
    }
}
