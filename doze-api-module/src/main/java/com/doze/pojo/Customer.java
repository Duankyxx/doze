package com.doze.pojo;

public class Customer {
    private String uid;
    private String tableNumber;

    public Customer() {
    }

    public Customer(String uid, String tableNumber) {
        this.uid = uid;
        this.tableNumber = tableNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return "customer{" +
                "uid='" + uid + '\'' +
                ", tableNumber='" + tableNumber + '\'' +
                '}';
    }
}
