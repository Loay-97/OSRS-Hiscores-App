package com.osrs.hiscores.dto.auth;

public class RegisterRequest {

    private String email;
    private String password;
    private String displayName;



    // Required: no-args constructor for JSON deserialization

    public RegisterRequest() {
    }
    // Optional: extra constructor

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
