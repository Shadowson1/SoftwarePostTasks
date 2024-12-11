/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl3;

/**
 *
 * @author aws.admin
 */
public class Animal {
    String name;
     int age;
     public Animal(String name, int age) {
     this.name = name;
     this.age = age;
     }
     public void eat() {
     System.out.println(name + " is eating.");
     }
     public void displayInfo() {
     System.out.println("Name: " + name + ", Age: " + age);
    }
     
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

class Dog extends Animal {
 public Dog(String name, int age) {
 super(name, age);
 }
 // Specific method for Dog
 public void bark() {
 System.out.println(name + " is barking.");
 }
 //Overriding makeSound Method
  public void makeSound() {
 System.out.println(name + " is barking.");
 }
 // Overriding displayInfo method
 @Override
 public void displayInfo() {
 System.out.println("Dog's Name: " + name + ", Age: " + age);
 }
}

class Cat extends Animal {
 public Cat(String name, int age) {
 super(name, age);
 }
 // Specific method for Cat
 public void meow() {
 System.out.println(name + " is meowing.");
 }
 //Overriding makeSound Method
  public void makeSound() {
 System.out.println(name + " is meowing.");
 }
 // Overriding displayInfo method
 @Override
 public void displayInfo() {
 System.out.println("Cat's Name: " + name + ", Age: " + age);
 }
}
 
 class Bird extends Animal {
 public Bird(String name, int age) {
 super(name, age);
 }
 // Specific method for Bird
 public void chirp() {
 System.out.println(name + " is chirping.");
 }
 //Overriding makeSound Method
  public void makeSound() {
 System.out.println(name + " is chirping.");
 }
 // Overriding displayInfo method
 @Override
 public void displayInfo() {
 System.out.println("Cat's Name: " + name + ", Age: " + age);
 }
}


