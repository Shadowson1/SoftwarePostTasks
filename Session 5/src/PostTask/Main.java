/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PostTask;

import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*; //Importing java.sql package

public class Main {
    public static void main(String[] args) {
    
    try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystem", "root", "rizz");//Establishing connection
            System.out.println("Connected With the database successfully"); //Message after successful connection    
    
    /*
    To make the system work with an array of accounts, nesting was needed. An initial menu asking for an account number to then access the GUI menu for transactions.
    After getting into the account, the actions can then be made, and leaving that account returns back to the outer loop to ask if they wish to open another account.
    */
    
    boolean exit = false;
    boolean accountFound = false;
    do {
        accountFound = false;
        System.out.println("\nWelcome, please input your account number, input 0 if you wish to exit: ");
        Scanner sc = new Scanner(System.in);
        String inputtedAccount = sc.nextLine();
        
        
        
        
        
        if(inputtedAccount.equals("0")) {
            System.out.println("Thank you for using us today.");
            break;
        }
        
        
        else {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where accountID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, inputtedAccount);
            ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    String accountPassword=resultSet.getString("accountPassword");
                    System.out.println("Account found, input password: ");
                    String password = sc.nextLine();
                    if(password.equals(accountPassword)) {                    
                    int choice;
                    System.out.println("Login Successful. " + resultSet.getString("users_userName"));
                    PreparedStatement getIsAdministrator = connection.prepareStatement("select * from users where userName=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    getIsAdministrator.setString(1, resultSet.getString("users_userName"));
                    ResultSet returnIsAdministrator = getIsAdministrator.executeQuery();
                    returnIsAdministrator.last();
                    int isAdmin = returnIsAdministrator.getInt("isAdministrator");
                    do {
                        if (isAdmin == 0) {
                            // Display menu
                            System.out.println("\nWelcome, " + resultSet.getString("users_userName") + "\n"
                                    + "\t1. View account information \n"
                                    + "\t2. Deposit \n"
                                    + "\t3. Withdraw \n"
                                    + "\t4. Exit \n"
                                    + "Enter your choice: ");
                            choice = sc.nextInt();

                            switch (choice) {
                                case 1:
                                    viewInformation(resultSet);
                                    break;
                                case 2:
                                    resultSet.updateDouble("accountBalance", deposit(connection, resultSet));
                                    break;
                                case 3:
                                    resultSet.updateDouble("accountBalance", withdraw(connection, resultSet));
                                    break;
                                case 4:
                                    exit();
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        resultSet.updateRow(); }
                        else {
                            // Display menu
                            System.out.println("\nWelcome, " + resultSet.getString("users_userName") + "\n"
                                    + "\t1. View account information \n"
                                    + "\t2. Deposit \n"
                                    + "\t3. Withdraw \n"
                                    + "\t4. Exit \n"
                                    + "\t5. Administrator Menu \n"
                                    + "Enter your choice: ");
                            choice = sc.nextInt();

                            switch (choice) {
                                case 1:
                                    viewInformation(resultSet);
                                    break;
                                case 2:
                                    resultSet.updateDouble("accountBalance", deposit(connection, resultSet));
                                    break;
                                case 3:
                                    withdraw(connection, resultSet);
                                    break;
                                case 4:
                                    exit();
                                    break;
                                case 5:
                                    administratorMenu(connection);
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        }
                    } while (choice != 4);
                    preparedStatement.close();
                    getIsAdministrator.close();
                    }
                    else {System.out.println("Invalid password, returning to main menu.");}
                }
        } 
 } while (exit == false);  
    
    } catch (SQLException e) {

                System.out.println("Error while connecting to the database"); //Message if something goes wrong while conneting to the database

                        }
 }
    /* The following methods utilise OOP abstraction and encapulsation, by putting the data into a seperate data class and the main class handling GUI
    we encapsulate the data of the account within the data class and call upon its methods along with its accessors and mutators. Whilst abstraction
    is done through seperating the roles between the two classes, where the main class handles GUI and the processes, the data class simply is used to
    hold the data. By them being seperate, the data cannot be accessed directly, which improves security. 
    */
    public static void viewInformation(ResultSet resultSet) {
        // displaying the account info
        System.out.printf("%-20s %-10s %s %n", "Name", "Number", "Balance");
        System.out.println("-".repeat(40));
        try {
            System.out.printf("%-20s %-10s $%.2f %n", resultSet.getString("users_userName"), resultSet.getString("accountID"), resultSet.getDouble("accountBalance"));
        } catch (SQLException e) {
            System.out.println("Something went wrong.");
        }
        
    }
    
    public static double deposit(Connection connection, ResultSet resultSet) {
        // depositing finances
        try {
            PreparedStatement preparedStatement =connection.prepareStatement("update accounts set accountBalance=? where accountID=?");
            preparedStatement.setDouble(2, resultSet.getDouble("accountBalance"));
            System.out.print("Enter deposit amount: ");
            Scanner sc = new Scanner(System.in);
            double amount = sc.nextDouble();
            if (amount > 0) {
            double newAmount = amount + resultSet.getDouble("accountBalance");
            preparedStatement.setDouble(1, newAmount);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deposited $" + amount);
            System.out.println("Updated Balance: $" + newAmount);
            preparedStatement.close();
            return newAmount;
            } else {
            System.out.println("Invalid deposit amount.");
            preparedStatement.close();
            return resultSet.getDouble("accountBalance");
            } 
            } catch (SQLException e) {System.out.println("Error with SQL database."); return 0;}
        }
    
    public static double withdraw(Connection connection, ResultSet resultSet) {
        // withdrawing finances
        try {
            PreparedStatement preparedStatement =connection.prepareStatement("update accounts set accountBalance=? where accountID=?");
            preparedStatement.setString(2, resultSet.getString("accountBalance"));
            System.out.print("Enter withdrawal amount: ");
            Scanner sc = new Scanner(System.in);
            double amount = sc.nextDouble();
            if (amount > 0 && amount <= resultSet.getDouble("accountBalance")) {
            double newAmount = resultSet.getDouble("accountBalance") - amount;
            preparedStatement.setDouble(1, newAmount);
            preparedStatement.executeUpdate();
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("Updated Balance: $" + newAmount);
            preparedStatement.close();
            return newAmount;
            } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            preparedStatement.close();
            return resultSet.getDouble("accountBalance");
            }
            } catch (SQLException e) {System.out.println("Error with SQL database."); return 0;}
    }
    
    public static void exit() {
        System.out.println("Thank you for using our service!");
    }
    
    public static void administratorMenu(Connection connection) {
        boolean returnCheck = false;
        boolean confirm = false;
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
        System.out.println("\nWelcome Administrator \n"
                                    + "\t1. Insert an Account \n"
                                    + "\t2. Delete an Account \n"
                                    + "\t3. Update an Account \n"
                                    + "\t4. Return \n"
                                    + "Enter your choice: ");
                            choice = sc.nextInt();
                            try {
                                String username; String accountID; String password; double balance; String pushCommand;
                            switch (choice) {
                                case 1:
                                    PreparedStatement insert = connection.prepareStatement("insert into accounts values(?,?,?,?)");
                                    System.out.println("Enter the Account ID: ");
                                    accountID = sc.next();
                                    insert.setString(1, accountID);
                                    System.out.println("Enter the User Name: ");
                                    sc.nextLine();
                                    username = sc.nextLine();
                                    insert.setString(2, username);
                                    while(true) {
                                    try {
                                    System.out.println("Enter the Balance: ");
                                    balance = sc.nextDouble();
                                    insert.setDouble(3, balance);
                                    break;
                                    } catch (Exception e) {System.out.println("Error, try again."); balance = 0.00; sc.next();}
                                    }
                                    System.out.println("Enter the Password: ");
                                    password = sc.next();
                                    insert.setString(4, password);
                                    System.out.println(accountID + " " + username + " " + balance + " " + password);
                                    while (!confirm) {
                                        System.out.println("Do you wish to insert these details? (Y/N) ");
                                        pushCommand = sc.next();
                                        if (pushCommand.equalsIgnoreCase("y")) {
                                            confirm = true;
                                            insert.executeUpdate();
                                            System.out.println("Confirmed, Inserting new Account.");
                                        }
                                        else if (pushCommand.equalsIgnoreCase("n")) {
                                            confirm = true;
                                            System.out.println("Confirmed, aborting Insert.");
                                        }
                                        else {
                                            System.out.println("Invalid response, try again. ");
                                        }
                                    }     
                                    insert.close();
                                    confirm = false;
                                    break;
                                case 2:
                                    PreparedStatement delete = connection.prepareStatement("delete from accounts where accountID=?");
                                    System.out.println("Enter the Account ID of the account you wish to delete: ");
                                    delete.setString(1, sc.next());
                                    while (!confirm) {
                                        System.out.println("Do you wish to delete this account? (Y/N) ");
                                        pushCommand = sc.next();
                                        if (pushCommand.equalsIgnoreCase("y")) {
                                            confirm = true;
                                            delete.executeUpdate();
                                            System.out.println("Confirmed, deleting account.");
                                        }
                                        else if (pushCommand.equalsIgnoreCase("n")) {
                                            confirm = true;
                                            System.out.println("Confirmed, aborting deletion.");
                                        }
                                        else {
                                            System.out.println("Invalid response, try again. ");
                                        }
                                    }  
                                    delete.close();
                                    confirm = false;
                                    break;
                                case 3:
                                    System.out.println("Input the account ID that you would like to update the information of: ");
                                    accountID = sc.next();
                                    while (!confirm) {
                                    System.out.println("\nWhat variable would you like to update? \n"
                                            + "\t1. Username\n"
                                            + "\t2. Balance\n"
                                            + "\t3. Password\n"
                                            + "\t4. Return\n");
                                    switch (sc.nextInt()) {
                                        case 1:
                                            PreparedStatement updateUser = connection.prepareStatement("update accounts set users_userName=? where accountID=?");
                                            updateUser.setString(2, accountID);
                                            sc.nextLine();
                                            System.out.println("What do you want to set the Username as?");
                                            username = sc.nextLine();
                                            updateUser.setString(1, username);
                                            while (!confirm) {
                                                System.out.println("Do you wish to update? (Y/N) ");
                                                pushCommand = sc.next();
                                                if (pushCommand.equalsIgnoreCase("y")) {
                                                    confirm = true;
                                                    updateUser.executeUpdate();
                                                    System.out.println("Confirmed, updating.");
                                                }
                                                else if (pushCommand.equalsIgnoreCase("n")) {
                                                    confirm = true;
                                                    System.out.println("Confirmed, aborting update.");
                                                }
                                                else {
                                                    System.out.println("Invalid response, try again. ");
                                                }
                                            }
                                            updateUser.close();
                                            confirm = false;
                                            break;
                                        case 2:
                                            PreparedStatement updateBalance = connection.prepareStatement("update accounts set accountBalance=? where accountID=?");
                                            updateBalance.setString(2, accountID);
                                            System.out.println("What do you want to set the Balance as?");
                                            balance = sc.nextDouble();
                                            updateBalance.setDouble(1, balance);
                                            while (!confirm) {
                                                System.out.println("Do you wish to update? (Y/N) ");
                                                pushCommand = sc.next();
                                                if (pushCommand.equalsIgnoreCase("y")) {
                                                    confirm = true;
                                                    updateBalance.executeUpdate();
                                                    System.out.println("Confirmed, updating.");
                                                }
                                                else if (pushCommand.equalsIgnoreCase("n")) {
                                                    confirm = true;
                                                    System.out.println("Confirmed, aborting update.");
                                                }
                                                else {
                                                    System.out.println("Invalid response, try again. ");
                                                }
                                            }
                                            updateBalance.close();
                                            confirm = false;
                                            break;
                                        case 3:
                                            PreparedStatement updatePassword = connection.prepareStatement("update accounts set accountPassword=? where accountID=?");
                                            updatePassword.setString(2, accountID);
                                            System.out.println("What do you want to set the Password as?");
                                            password = sc.next();
                                            updatePassword.setString(1, password);
                                            while (!confirm) {
                                                System.out.println("Do you wish to update? (Y/N) ");
                                                pushCommand = sc.next();
                                                if (pushCommand.equalsIgnoreCase("y")) {
                                                    confirm = true;
                                                    updatePassword.executeUpdate();
                                                    System.out.println("Confirmed, updating.");
                                                }
                                                else if (pushCommand.equalsIgnoreCase("n")) {
                                                    confirm = true;
                                                    System.out.println("Confirmed, aborting update.");
                                                }
                                                else {
                                                    System.out.println("Invalid response, try again. ");
                                                }
                                            }
                                            updatePassword.close();
                                            confirm = false;
                                            break;
                                        case 4:
                                            confirm = true;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                    } confirm = false;
                                    break;
                                case 4:
                                    returnCheck = true;
                                    break;
                            }
                            } catch(SQLException e) {
                                System.out.println("Error occured, check your inputted details, returning to Administrator Menu.");
                                System.err.println("Message: " + e.getMessage());
                            }
        } while(!returnCheck);
    }

}
