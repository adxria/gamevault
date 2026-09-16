package org.gamevault.Command;

public class CmdResult {
    private boolean success;
    private String output;

    CmdResult(boolean success, String output) {
        this.success = success;
        this.output = output;
    }

    static CmdResult success() {
        return new CmdResult(true, null);
    }

    static CmdResult success(String output) {
        return new CmdResult(true, output);
    }

    static CmdResult failure(String output) {
        return new CmdResult(false, output);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getOutput() {
        return output;
    }
}