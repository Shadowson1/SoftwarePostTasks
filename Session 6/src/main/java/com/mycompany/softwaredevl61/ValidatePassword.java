/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.softwaredevl61;

import java.util.Scanner;

/**
 *
 * @author aws.admin
 */
public class ValidatePassword {

    public static void main(String[] args) {
        System.out.println("Please input password: ");
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        if (containsValidCharacters(password) && isValidLength(password)) {
            System.out.println("True.");
        } else {
            System.out.println("False.");
        }
    }
    
    public static boolean isValidLength(String password) {
        return password != null && password.length() >= 5 && password.length() <=10 ;
    }
    
    public static boolean containsUpperCase(String password) {
        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsLowerCase(String password) {
        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsNumber(String password) {
        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsSpecialCharacter(String password) {
        char ch;
        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if (!(Character.isDigit(ch) || Character.isUpperCase(ch) || Character.isLowerCase(ch))) {
                return true;
            }
        }
        return false;        
    }
    
    public static boolean containsValidCharacters (String password) {
        boolean capitalFlag; boolean lowerCaseFlag; boolean numberFlag; boolean specialCharacterFlag;
        numberFlag = containsNumber(password);
        capitalFlag = containsUpperCase(password);
        lowerCaseFlag = containsLowerCase(password);
        specialCharacterFlag = containsSpecialCharacter(password);
        System.out.println(capitalFlag + " " + lowerCaseFlag + " " + numberFlag + " " + specialCharacterFlag);
        return numberFlag && capitalFlag && lowerCaseFlag && specialCharacterFlag;
    }
}
