/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
 // Create a data object with initial account details 
    Data account = new Data("John Doe", "123456789", 1000.00);
     int choice;
     do {
     // Display menu
     System.out.println("\nWelcome, " + account.getAccountName() + "\n"
     + "\t1. View account information \n"
     + "\t2. Deposit \n"
     + "\t3. Withdraw \n"
     + "\t4. Exit \n"
     + "Enter your choice: ");
     Scanner sc = new Scanner(System.in);
     choice = sc.nextInt();

    switch (choice) {
     case 1 -> viewInformation(account);
     case 2 -> deposit(account);
     case 3 -> withdraw(account);
     case 4 -> exit(account);
     default -> System.out.println("Invalid choice. Please try again.");
 }
 } while (choice != 4);
 } 
    
    /* The following methods utilise OOP abstraction and encapulsation, by putting the data into a seperate data class and the main class handling GUI
    we encapsulate the data of the account within the data class and call upon its methods along with its accessors and mutators. Whilst abstraction
    is done through seperating the roles between the two classes, where the main class handles GUI and the processes, the data class simply is used to
    hold the data. By them being seperate, the data cannot be accessed directly, which improves security. 
    */
    public static void viewInformation(Data account) {
        // displaying the account info
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        System.out.printf("%-20s %-10s $%.2f %n", account.getAccountName(), account.getAccountNumber(), account.getAccountBalance());
    }
    
    public static void deposit(Data account) {
        // depositing finances
        System.out.print("Enter deposit amount: ");
        Scanner sc = new Scanner(System.in);
        double amount = sc.nextDouble();
        if (amount > 0) {
        double newAmount = amount + account.getAccountBalance();
        account.setAccountBalance(newAmount);
        System.out.println("Successfully deposited $" + amount);
        System.out.println("Updated Balance: $" + account.getAccountBalance());
        } else {
        System.out.println("Invalid deposit amount.");
        }
    }
    
    public static void withdraw(Data account) {
        // withdrawing finances
        System.out.print("Enter withdrawal amount: ");
        Scanner sc = new Scanner(System.in);
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= account.getAccountBalance()) {
        double newAmount = account.getAccountBalance() - amount;
        account.setAccountBalance(newAmount);
        System.out.println("Successfully withdrew $" + amount);
        System.out.println("Updated Balance: $" + account.getAccountBalance());
        } else {
        System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
    
    public static void exit(Data account) {
        System.out.println("Thank you for using our service!");
    }
}
