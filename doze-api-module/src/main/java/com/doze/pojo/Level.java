package com.doze.pojo;

public class Level {

    private int userId;
    private String username;
    private String level;

    public Level() {
    }

    public Level(int userId, String username, String level) {
        this.userId = userId;
        this.username = username;
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Level{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
