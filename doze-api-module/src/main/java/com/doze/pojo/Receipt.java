package com.doze.pojo;

public class Receipt {
    private int id;
    private String CookName;
    private int quantity;
    private double price;
    private int serialNumber;

    public Receipt() {
    }

    public Receipt(int id, String cookName, int quantity, double price, int serialNumber) {
        this.id = id;
        CookName = cookName;
        this.quantity = quantity;
        this.price = price;
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookName() {
        return CookName;
    }

    public void setCookName(String cookName) {
        CookName = cookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "receipt{" +
                "id=" + id +
                ", CookName='" + CookName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
