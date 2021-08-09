package net.javaguides.springboot.exceptions;

public class Unauthorized extends RuntimeException {
    public Unauthorized() {
        super("You are not authorized to access this resource");
    }
}

