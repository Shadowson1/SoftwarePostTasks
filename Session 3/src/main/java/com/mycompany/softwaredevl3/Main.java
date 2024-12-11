/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl3;

/**
 *
 * @author aws.admin
 */
import java.time.LocalDateTime;
public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Josh", 9);
        Dog dog1 = new Dog("Rob", 12);
        
        dog1.bark();
        cat1.meow();
        dog1.eat();
        cat1.eat();
        
        DryFood rice = new DryFood(102, "Uncle Ben", LocalDateTime.now().plusMonths(6), false, "Keep within bag");
        
        System.out.println(rice.getUseBefore());
        
        
        
        Bike bike1 = new Bike("Blaze Mk1", "Sony", 4, 28);
        Car car1 = new Car("Porsche", "Audi", 10, 2200);
        Truck truck1 = new Truck("Hauler", "Strength & Co", 6, 654);
        
        bike1.displayInfo();
        bike1.classification();
        car1.displayInfo();
        car1.classification();
        truck1.displayInfo();
        truck1.classification();
        
        
        Bird bird1 = new Bird("Mike", 4);
        
        Animal[] zoo = new Animal[3];
        zoo[0] = cat1;
        zoo[1] = dog1;
        zoo[2] = bird1;
        
        for(int i=0; i < zoo.length; i++){
            zoo[i].makeSound();
        }
                
    }
}
