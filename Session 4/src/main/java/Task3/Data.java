/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task3;

public class Data {
    // Class level variables for account information
    private String accountName;
    private String accountNumber;
    private double accountBalance;
    private String accountPassword;
    // Constructors
    public Data (String name, String number, double balance, String password) {
        this.accountName = name;
        this.accountNumber = number;
        this.accountBalance = balance;
        this.accountPassword = password;
    }
    
    // Accessors
    public String getAccountName() {return accountName; }
    public String getAccountNumber() {return accountNumber;}
    public double getAccountBalance() {return accountBalance;}
    public String getAccountPassword() {return accountPassword;}
    // Mutator
    public void setAccountBalance(double balance) {accountBalance = balance;}
    
    // Service Method
    public String getFormattedBalance() {return String.format("$ %.2f", accountBalance);}
}

