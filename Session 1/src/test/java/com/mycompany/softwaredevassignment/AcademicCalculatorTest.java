/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwaredevassignment;

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
public class AcademicCalculatorTest {
    
    public AcademicCalculatorTest() {
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
     * Test of main method, of class AcademicCalculator.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AcademicCalculator.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ModuleRegistration method, of class AcademicCalculator.
     */
    @Test
    public void testModuleRegistration() {
        System.out.println("ModuleRegistration");
        int[] credits = {20, 20, 10};
        int[] mark = {70, 60, 50};
        AcademicCalculator instance = new AcademicCalculator();
        instance.ModuleRegistration(credits, mark);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MethodOne method, of class AcademicCalculator.
     */
    @Test
    public void testMethodOne() {
        System.out.println("MethodOne");
        double L5Average = 69.50;
        double L6Average = 75.00;
        AcademicCalculator.MethodOne(L5Average, L6Average);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of GetCourseAverage method, of class AcademicCalculator.
     */
    @Test
    public void testGetCourseAverage() {
        System.out.println("GetCourseAverage");
        int[] credits = {20, 20, 10};
        int[] marks = {70, 60, 50};
        double expResult = 62.0;
        double result = AcademicCalculator.GetCourseAverage(credits, marks);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of CheckClassification method, of class AcademicCalculator.
     */
    @Test
    public void testCheckClassification() {
        System.out.println("CheckClassification");
        double achievedAverage = 0.0;
        String expResult = "Fail";
        String result = AcademicCalculator.CheckClassification(achievedAverage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
