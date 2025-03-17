package org.automation.constants;

public enum SignInDetails {
    LOGIN_USER("tamasiisabelle@yahoo.com", "adminpassword1"), //username:TestAdminProject
    INVALID_USER("wrongusernametest", "123786");

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
