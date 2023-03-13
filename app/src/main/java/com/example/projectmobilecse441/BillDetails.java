package com.example.projectmobilecse441;

public class BillDetails {
    private int id;
    private int billCode;
    private String bookTitle;
    private String author;
    private int price;
    private int quantitySale;

    public BillDetails(int id, int billCode, String bookTitle, String author, int price, int quantitySale) {
        this.id = id;
        this.billCode = billCode;
        this.bookTitle = bookTitle;
        this.author = author;
        this.price = price;
        this.quantitySale = quantitySale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillCode() {
        return billCode;
    }

    public void setBillCode(int billCode) {
        this.billCode = billCode;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(int quantitySale) {
        this.quantitySale = quantitySale;
    }
}
