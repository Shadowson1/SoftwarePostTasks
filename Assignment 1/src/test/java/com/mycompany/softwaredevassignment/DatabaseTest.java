/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwaredevassignment;

import java.util.ArrayList;
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
public class DatabaseTest {
    
    public DatabaseTest() {
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
     * Test of findAccount method, of class Database.
     */
    @Test
    public void testFindAccount() {
        System.out.println("findAccount");
        String username = "";
        String password = "";
        boolean expResult = false;
        boolean result = Database.findAccount(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of findisAdminfromAccount method, of class Database.
     */
    @Test
    public void testFindisAdminfromAccount() {
        System.out.println("findisAdminfromAccount");
        String username = "";
        String password = "";
        int expResult = 0;
        int result = Database.findisAdminfromAccount(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of findCoursefromAccount method, of class Database.
     */
    @Test
    public void testFindCoursefromAccount() {
        System.out.println("findCoursefromAccount");
        String username = "";
        String expResult = "";
        String result = Database.findCoursefromAccount(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of isCourse method, of class Database.
     */
    @Test
    public void testIsCourse() {
        System.out.println("isCourse");
        String course = "";
        boolean expResult = false;
        boolean result = Database.isCourse(course);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertSQL method, of class Database.
     */
    @Test
    public void testInsertSQL() {
        System.out.println("insertSQL");
        String table = "";
        ArrayList<String> data = null;
        String expResult = "";
        String result = Database.insertSQL(table, data);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateSQL method, of class Database.
     */
    @Test
    public void testUpdateSQL() {
        System.out.println("updateSQL");
        String table = "";
        int row = 0;
        int col = 0;
        String colName = "";
        String rowID = "";
        String value = "";
        String expResult = "";
        String result = Database.updateSQL(table, row, col, colName, rowID, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteSQL method, of class Database.
     */
    @Test
    public void testDeleteSQL() {
        System.out.println("deleteSQL");
        String table = "";
        String rowID = "";
        String expResult = "";
        String result = Database.deleteSQL(table, rowID);
        assertEquals(expResult, result);
    }
    
}
