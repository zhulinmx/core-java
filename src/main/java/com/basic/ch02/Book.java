package com.basic.ch02;

import com.basic.ch04.annotation.Description;

@Description("I am a class annotation.")
public class Book {
    static {
        System.out.println("this is static content");
        System.out.println(Book.type);
    }
    private static int type = 1;
    private String name;
    private int price;

    public Book(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("this book is good" + name);
    }

    public void print(String person) {
        System.out.println(person+ " think this book is good");
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
