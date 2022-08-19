package com.doze.pojo;

public class User {
    private int id;
    private String username;
    private String password;
    private String level;
    private String enable;

    public User() {
    }

    public User(int id, String username, String password, String level, String enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level='" + level + '\'' +
                ", enable='" + enable + '\'' +
                '}';
    }
}
