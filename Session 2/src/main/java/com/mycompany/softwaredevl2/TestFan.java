/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl2;

import java.util.Scanner;

/**
 *
 * @author aws.admin
 */
public class TestFan{
    public static void main(String[] args){
    //create a fan object
    Fan myFan = new Fan();
    //confirm fan object created
    Scanner sc = new Scanner(System.in);
    System.out.println("\nFan object created" );
    myFan.turnOn();
    System.out.println("Is my Fan on?");
    if (myFan.getFanIsOn() == true) {
        myFan.setSpeed(30);
        System.out.println("Fan is currently on and set to 30.");
    } else {
        System.out.println("Fan is off");
    }
    //find out if it is on or off
/*    System.out.println("Is my Fan on? " + myFan.getFanIsOn() );
    //switch on fan
    System.out.println("\nSwitching Fan on");
    myFan.turnOn();
    System.out.println("Is my Fan on? " 
    + myFan.getFanIsOn() );
    //switch off fan
    System.out.println("\nSwitching Fan off");
    myFan.turnOff();
    System.out.println("Is my Fan on? " 
    + myFan.getFanIsOn() ); */
    } //end of the main method
} //end of the class

