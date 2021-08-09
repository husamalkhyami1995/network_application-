package net.javaguides.springboot.exceptions;

public class IllformedRequest extends RuntimeException {
    public IllformedRequest() {
    }

    public IllformedRequest(String message) {
        super(message);
    }
}

