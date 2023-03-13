package com.example.projectmobilecse441;

public class Cart {
    private int cartId;
    private int orderId;
    private int bookId;
    private String bookTitle;
    private int price;
    private byte[] image;
    private int quantitySale;
    private int subTotalPrice;
    private int quantityStorage;
    private String author;

    public Cart(int cartId, int orderId, int bookId, String bookTitle, int price, byte[] image, int quantitySale, int subTotalPrice,String author,int quantityStorage) {
        this.cartId = cartId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.price = price;
        this.image = image;
        this.quantitySale = quantitySale;
        this.subTotalPrice = subTotalPrice;
        this.author = author;
        this.quantityStorage = quantityStorage;
    }

    public int getQuantityStorage() {
        return quantityStorage;
    }

    public void setQuantityStorage(int quantityStorage) {
        this.quantityStorage = quantityStorage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(int quantitySale) {
        this.quantitySale = quantitySale;
    }

    public int getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(int subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }
}
