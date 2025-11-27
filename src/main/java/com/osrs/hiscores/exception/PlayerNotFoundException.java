package com.osrs.hiscores.exception;

public class PlayerNotFoundException extends RuntimeException {
    private final String username;

    public PlayerNotFoundException(String username) {
        super("Username doesn't exist: " + username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
