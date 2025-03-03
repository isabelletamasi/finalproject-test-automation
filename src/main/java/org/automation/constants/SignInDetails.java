package org.automation.constants;

public enum SignInDetails {
    LOGIN_USER("usernametest", "test12345"),
    INVALID_USER("usernametest", "123786");

    private final String username;
    private String password;

    SignInDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    SignInDetails(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
