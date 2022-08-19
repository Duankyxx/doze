package com.doze.pojo;

import java.util.List;

public class Order {
    private int id;
    private int seatNumber;
    private String time;
    private String remarks;
    private double payment;
    private int uid;
    private List<Receipt> receipts;

    public Order() {
    }

    public Order(int id, int seatNumber, String time, String remarks, double payment, int uid, List<Receipt> receipts) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.time = time;
        this.remarks = remarks;
        this.payment = payment;
        this.uid = uid;
        this.receipts = receipts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", time='" + time + '\'' +
                ", remarks='" + remarks + '\'' +
                ", payment=" + payment +
                ", uid=" + uid +
                ", receipts=" + receipts +
                '}';
    }
}
