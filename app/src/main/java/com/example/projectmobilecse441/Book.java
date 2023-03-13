package com.example.projectmobilecse441;

import java.util.Date;

public class Book {
    private int Id;
    private String bookTitle;
    private String price;
    private String author;
    private String quantity;
    private String category;
    private String image;
    private String publishDate;
    private String description;

    public Book(int Id, String bookTitle, String price, String author, String quantity, String category, String image, String publishDate, String description) {
        this.Id = Id;
        this.bookTitle = bookTitle;
        this.price = price;
        this.author = author;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
        this.publishDate = publishDate;
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
