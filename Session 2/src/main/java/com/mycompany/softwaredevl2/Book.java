/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl2;

/**
 *
 * @author aws.admin
 */
public class Book {
    //fields
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;
    //constructor
    public Book(String title, 
    String author, String isbn, double price, 
    int quantity){
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.price = price;
    this.quantity = quantity;
    }
    //read accessors
    public String getTitle(){return title;} 
    public String getAuthor(){return author;} 
    public String getISBN(){return isbn;} 
    public double getPrice(){return price;} 
    public int getQuantity(){return 
    quantity;} 
    //set accessors
    public void setPrice(double price){
    this.price=price;
    } 
    public void setQuantity(int quantity){
    this.quantity=quantity;
    }
    public void getQuantity(int quantity){
    this.quantity=quantity;
    } 
}//end of class

