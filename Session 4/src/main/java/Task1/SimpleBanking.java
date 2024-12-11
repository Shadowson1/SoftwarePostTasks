/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task1;

import java.util.Scanner;

/**
 *
 * @author aws.admin
 */
public class SimpleBanking {
// Class level variables for account information
 static String accountName;
 static String accountNumber;
 static double accountBalance;
 // Class level Scanner object
 static Scanner sc = new Scanner(System.in);
 // Constructor to initialize account details
 public SimpleBanking(String name, String number, double balance) {
 this.accountName = name;
 this.accountNumber = number;
 this.accountBalance = balance;
 }

public static void viewInformation() {
 // display information
 System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
 System.out.println("-".repeat(40));
 System.out.printf("%-20s %-10s $%.2f %n", accountName, accountNumber, accountBalance);
 }

public static void deposit() {
 // depositing finances
 System.out.print("Enter deposit amount: ");
 int amount = sc.nextInt();
 if (amount > 0) {
 accountBalance += amount;
 System.out.println("Successfully deposited $" + amount);
 System.out.println("Updated Balance: $" + accountBalance);
 } else {
 System.out.println("Invalid deposit amount.");
 }
 }

public static void withdraw() {
 // withdrawing finances
 System.out.print("Enter withdrawal amount: ");
 int amount = sc.nextInt();
 if (amount > 0 && amount <= accountBalance) {
 accountBalance -= amount;
 System.out.println("Successfully withdrew $" + amount);
 System.out.println("Updated Balance: $" + accountBalance);
 } else {
 System.out.println("Invalid withdrawal amount or insufficient funds.");
 }
 }

public static void exit() {
 System.out.println("Thank you for using our service!");
}

public static void main(String[] args) {
 // Create a SimpleBanking object with initial account details 
    SimpleBanking account = new SimpleBanking("John Doe", "123456789", 1000.00);
     int choice;
     do {
     // Display menu
     System.out.println("\nWelcome, " + account.accountName + "\n"
     + "\t1. View account information \n"
     + "\t2. Deposit \n"
     + "\t3. Withdraw \n"
     + "\t4. Exit \n"
     + "Enter your choice: ");
     Scanner sc = new Scanner(System.in);
     choice = sc.nextInt();

    switch (choice) {
     case 1 -> account.viewInformation();
     case 2 -> account.deposit();
     case 3 -> account.withdraw();
     case 4 -> account.exit();
     default -> System.out.println("Invalid choice. Please try again.");
 }
 } while (choice != 4);
 } 
}

