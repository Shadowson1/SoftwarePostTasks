/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task3;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
 // Create a data object with initial account details, then creating an account array to then append the data to.
    Data account1 = new Data("John Doe", "123456789", 1000.00, "1p2");
    Data account2 = new Data("Robert Downing", "2314232", 1200.00, "testing");
    Data account3 = new Data("Stacy Beckham", "7391872", 2300.10, "Beckhamington");
    
    ArrayList<Data> accountArray = new ArrayList<>();
    accountArray.add(account1);
    accountArray.add(account2);
    accountArray.add(account3);
    
    /*
    To make the system work with an array of accounts, nesting was needed. An initial menu asking for an account number to then access the GUI menu for transactions.
    After getting into the account, the actions can then be made, and leaving that account returns back to the outer loop to ask if they wish to open another account.
    */
    
    boolean exit = false;
    boolean accountFound;
    do {
        accountFound = false;
        System.out.println("\nWelcome, please input your account number, input 0 if you wish to exit: ");
        Scanner sc = new Scanner(System.in);
        String inputtedAccount = sc.nextLine();
        
        if(inputtedAccount.equals("0")) {
            exit = true;
            System.out.println("Thank you for using us today.");
        }
        
        
        else {
            for(int i = 0; i < accountArray.size(); i++) {
                if (accountFound == true) {
                    break;
                }
                if(inputtedAccount.equals(accountArray.get(i).getAccountNumber())) {
                    System.out.println("Account found, input password: ");
                    String password = sc.nextLine();
                    if(password.equals(accountArray.get(i).getAccountPassword())) {                    
                    accountFound = true;
                    int choice;
                    do {
                        // Display menu
                        System.out.println("\nWelcome, " + accountArray.get(i).getAccountName() + "\n"
                                + "\t1. View account information \n"
                                + "\t2. Deposit \n"
                                + "\t3. Withdraw \n"
                                + "\t4. Exit \n"
                                + "Enter your choice: ");
                        choice = sc.nextInt();
                        
                        switch (choice) {
                            case 1 -> viewInformation(accountArray.get(i));
                            case 2 -> deposit(accountArray.get(i));
                            case 3 -> withdraw(accountArray.get(i));
                            case 4 -> exit(accountArray.get(i));
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    } while (choice != 4);
                    }
                    else {System.out.println("Invalid password, returning to main menu."); break;}
                }
            }if (accountFound == false) {
                System.out.println("We couldn't find an account with that number, please try again.");
        }     
 } 
 } while (exit == false); 
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
