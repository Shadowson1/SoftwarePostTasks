/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl2;

/**
 *
 * @author aws.admin
 */
public class Fan{
    private boolean isOn; // Fan's on/off state
     private int speed; // Fan's speed (1 to 5)
    public Fan(){
    isOn = false; // Fan is off by default
     speed = 0; // Speed is 0 when the fan is off
    }
    public void turnOn(){ 
    isOn = true;
     speed = 1; // Speed is 1 when the fan is on (default state)
    }
    public void turnOff(){ 
    isOn = false;
     speed = 0; 
    }
     public void setSpeed(int newSpeed) { 
     speed = newSpeed;
     }
    public boolean getFanIsOn(){ 
    return isOn; 
    }
}
