package com.osrs.hiscores.exception;

import com.osrs.hiscores.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePlayerNotFound(PlayerNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Username doesn't exist: " + ex.getUsername());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}