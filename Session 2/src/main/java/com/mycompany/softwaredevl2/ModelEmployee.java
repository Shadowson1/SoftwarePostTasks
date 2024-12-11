/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwaredevl2;

/**
 *
 * @author aws.admin
 */
public class ModelEmployee {
    //fields
    private String name;
    private String position;
    private String employeeID;
    private double salary;
    //constructor
    public ModelEmployee(String name, String position, String employeeID, double salary){
    this.name = name;
    this.position = position;
    this.employeeID = employeeID;
    this.salary = salary;
    }
    //read accessors
    public String getName(){return name;} 
    public String getPosition(){return position;} 
    public String getEmployeeID(){return employeeID;} 
    public double getSalary(){return salary;} 
    public void getEmployeeInfo() {
        System.out.println(name + ": " + employeeID + "\n Position: " + position + "\n Salary: " + salary);
    }
    //set accessors
    public void changeSalary(double salary){
        this.salary=salary;
    } 
    public void promotion(String newPosition) {
        System.out.println(name + " Has been promoted to " + newPosition);
        this.position=newPosition;
        
    }
    // Main class to test the Employee Class
    public static void main(String[] args) {
        // Create ModelEmployee Object
        ModelEmployee employee1 = new ModelEmployee("Joseph Williams", "Marketing Assistant", "JA2081", 27000);
        
        //Print Employee Info
        employee1.getEmployeeInfo();
        
        //Alter Employee Salary
        employee1.changeSalary(30000);
        
        //Promote Employee
        employee1.promotion("Marketing Manager");
        
        //Print changed
        employee1.getEmployeeInfo();
        
        
    }
}
