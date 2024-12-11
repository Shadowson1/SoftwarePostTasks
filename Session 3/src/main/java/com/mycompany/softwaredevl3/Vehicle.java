/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl3;


public class Vehicle {
    private String name;
    private String manufacturer;
    private int age;
    private int mileage;
    
    public Vehicle (String name, String manufacturer, int age, int mileage) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.age = age;
        this.mileage = mileage;
    }
            
    public void displayInfo() {
        System.out.println("The " + name + " is " + age + " years old. Made by " + manufacturer + " and has driven " + mileage + " mile(s).");
    }
    
    public void classification() {
        System.out.println("The Vehicle is a category which defines a transportation device used to get people from start to finish.");
    }
    
}
    
class Car extends Vehicle {
    public Car (String name, String manufacturer, int age, int mileage) {
        super(name, manufacturer, age, mileage);
    }
    
    @Override
    public void classification() {
        System.out.println("The Car, typically holds 4 or more seats with storage to allow for groups to travel.");
    }
}


class Bike extends Vehicle {
    public Bike (String name, String manufacturer, int age, int mileage) {
        super(name, manufacturer, age, mileage);
    }
    
    @Override
    public void classification() {
        System.out.println("The Bike, a vehicle designed for speed and mobility, allowing its rider to reach its destination quickly.");
    }
}

class Truck extends Vehicle {
    public Truck (String name, String manufacturer, int age, int mileage) {
        super(name, manufacturer, age, mileage);
    }
    
    @Override
    public void classification() {
        System.out.println("The Truck, the workhorse of industry, designed to carry large amounts of cargo from place to place.");
    }
}