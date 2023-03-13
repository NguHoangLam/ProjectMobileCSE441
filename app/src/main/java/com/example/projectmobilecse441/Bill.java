package com.example.projectmobilecse441;

public class Bill {
    private int id;
    private int customerId;
    private int total;
    private String date;
    private String address;
    private int phone;

    public Bill(int id, int customerId, int total, String date, String address, int phone) {
        this.id = id;
        this.customerId = customerId;
        this.total = total;
        this.date = date;
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
