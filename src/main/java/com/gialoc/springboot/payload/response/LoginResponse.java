package com.gialoc.springboot.payload.response;

public class LoginResponse {
    private String email;
    private String token;
    private String roleUser;

    public LoginResponse() {
    }

    public LoginResponse(String email, String token, String roleUser) {
        this.email = email;
        this.token = token;
        this.roleUser = roleUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }
}
