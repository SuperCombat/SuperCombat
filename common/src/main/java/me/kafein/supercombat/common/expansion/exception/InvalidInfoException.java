package me.kafein.supercombat.common.expansion.exception;

public class InvalidInfoException extends IllegalStateException {

    public InvalidInfoException(String message) {
        super(message);
    }

    public InvalidInfoException() {
        super("The info for one of the expansions is invalid.");
    }
}
