package com.doze.pojo;

public class CookBook {

    private int id;
    private int uid;
    private String cookName;
    private String cookPicture;
    private double price;
    private String isGood;
    private String isSell;
    private String cookLabel;
    private int purchaseQuantity;

    public CookBook() {
    }

    public CookBook(int id, int uid, String cookName, String cookPicture, double price, String isGood, String isSell, String cookLabel, int purchaseQuantity) {
        this.id = id;
        this.uid = uid;
        this.cookName = cookName;
        this.cookPicture = cookPicture;
        this.price = price;
        this.isGood = isGood;
        this.isSell = isSell;
        this.cookLabel = cookLabel;
        this.purchaseQuantity = purchaseQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public String getCookPicture() {
        return cookPicture;
    }

    public void setCookPicture(String cookPicture) {
        this.cookPicture = cookPicture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsGood() {
        return isGood;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood;
    }

    public String getIsSell() {
        return isSell;
    }

    public void setIsSell(String isSell) {
        this.isSell = isSell;
    }

    public String getCookLabel() {
        return cookLabel;
    }

    public void setCookLabel(String cookLabel) {
        this.cookLabel = cookLabel;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    @Override
    public String toString() {
        return "CookBook{" +
                "id=" + id +
                ", uid=" + uid +
                ", cookName='" + cookName + '\'' +
                ", cookPicture='" + cookPicture + '\'' +
                ", price=" + price +
                ", isGood='" + isGood + '\'' +
                ", isSell='" + isSell + '\'' +
                ", cookLabel='" + cookLabel + '\'' +
                ", purchaseQuantity=" + purchaseQuantity +
                '}';
    }
}
