package com.tek.DSA;

public class Book {

    private int id;
    private String title;
    private float price;
    private String author;
    private boolean available;

    public Book(int id, String title, float price, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
//        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + available;
    }
}
