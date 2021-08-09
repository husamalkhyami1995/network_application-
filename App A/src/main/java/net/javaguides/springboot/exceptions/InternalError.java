package net.javaguides.springboot.exceptions;

public class InternalError extends RuntimeException {
    public InternalError() {
    }

    public InternalError(String message) {
        super(message);
    }

    public InternalError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalError(Throwable cause) {
        super(cause);
    }
}

