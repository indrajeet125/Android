package com.example.loginregistration;

public class User {
    private  String username,password,enail;

    public User(String username, String password, String enail) {
        this.username = username;
        this.password = password;
        this.enail = enail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEnail() {
        return enail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnail(String enail) {
        this.enail = enail;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enail='" + enail + '\'' +
                '}';
    }
}
