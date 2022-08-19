package com.doze.pojo;

public class UserDetailed {
    private int id;
    private int userId;
    private String shopName;
    private int isNewOrder;

    public UserDetailed() {
    }

    public UserDetailed(int id, int userId, String shopName, int isNewOrder) {
        this.id = id;
        this.userId = userId;
        this.shopName = shopName;
        this.isNewOrder = isNewOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getIsNewOrder() {
        return isNewOrder;
    }

    public void setIsNewOrder(int isNewOrder) {
        this.isNewOrder = isNewOrder;
    }

    @Override
    public String toString() {
        return "userDetailed{" +
                "id=" + id +
                ", userId=" + userId +
                ", shopName='" + shopName + '\'' +
                ", isNewOrder=" + isNewOrder +
                '}';
    }
}
