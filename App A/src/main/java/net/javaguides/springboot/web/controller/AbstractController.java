package net.javaguides.springboot.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {
    protected ResponseEntity response(Object o, HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(new CustomMap()
                        .put("data", o)
                        .put("message", message)
                        .put("status", status.value())
                );
    }

    public ResponseEntity response(Object o) {
        return ResponseEntity
                .status(200)
                .body(o);
    }
}
