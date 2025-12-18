package dev.siea.uilabs.exceptions;

public class NoMoreSlotsException extends RuntimeException {
    public NoMoreSlotsException(String message) {
        super(message);
    }
}
