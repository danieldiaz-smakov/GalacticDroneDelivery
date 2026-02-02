package edu.narxoz.galactic.dispatcher;

public class Result {
    private final boolean ok;
    private final String reason;

    public Result(boolean ok, String reason) {
        this.ok = ok;
        this.reason = reason;
    }

    public boolean ok() {
        return ok;
    }

    public String reason() {
        return reason;
    }
}