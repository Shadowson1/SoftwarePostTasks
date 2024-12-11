/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwaredevl61;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aws.admin
 */
public class ValidatePasswordTest {
    
    public ValidatePasswordTest() {
    }
   @Test
    void test() {
    fail("Nothing is Implemented");
    }
    
    @Test
    public void testIsValidLength() {
    assertTrue(ValidatePassword.isValidLength("Password12!"), "Password with 12 characters should bevalid.");
 } 
    
    @Test
    public void testContainValidCharacters() {
    assertTrue(ValidatePassword.containsValidCharacters("Password12!"), "Password with atleast one lowercase, uppercase and number character should be valid.");
 } 
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class ValidatePassword.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ValidatePassword.main(args);
    }

    /**
     * Test of containsValidCharacters method, of class ValidatePassword.
     */
    @Test
    public void testContainsValidCharacters() {
        System.out.println("containsValidCharacters");
        String password = "Password12!";
        boolean expResult = true;
        boolean result = ValidatePassword.containsValidCharacters(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsUpperCase method, of class ValidatePassword.
     */
    @Test
    public void testContainsUpperCase() {
        System.out.println("containsUpperCase");
        String password = "";
        boolean expResult = false;
        boolean result = ValidatePassword.containsUpperCase(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsLowerCase method, of class ValidatePassword.
     */
    @Test
    public void testContainsLowerCase() {
        System.out.println("containsLowerCase");
        String password = "";
        boolean expResult = false;
        boolean result = ValidatePassword.containsLowerCase(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsNumber method, of class ValidatePassword.
     */
    @Test
    public void testContainsNumber() {
        System.out.println("containsNumber");
        String password = "";
        boolean expResult = false;
        boolean result = ValidatePassword.containsNumber(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsSpecialCharacter method, of class ValidatePassword.
     */
    @Test
    public void testContainsSpecialCharacter() {
        System.out.println("containsSpecialCharacter");
        String password = "";
        boolean expResult = false;
        boolean result = ValidatePassword.containsSpecialCharacter(password);
        assertEquals(expResult, result);
    }
    
}
