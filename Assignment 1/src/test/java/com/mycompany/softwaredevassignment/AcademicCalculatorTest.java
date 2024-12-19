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
     * Test of MethodOne method, of class AcademicCalculator.
     */
    @Test
    public void testMethodOne() {
        System.out.println("MethodA");
        double L5Average = 69.50;
        double L6Average = 75.00;
        AcademicCalculator.MethodA(L5Average, L6Average);
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

    /**
     * Test of MethodA method, of class AcademicCalculator.
     */
    @Test
    public void testMethodA() {
        System.out.println("MethodA");
        double L5Average = 0.0;
        double L6Average = 0.0;
        double expResult = 0.0;
        double result = AcademicCalculator.MethodA(L5Average, L6Average);
        assertEquals(expResult, result);
    }

    /**
     * Test of MethodB method, of class AcademicCalculator.
     */
    @Test
    public void testMethodB() {
        System.out.println("MethodB");
        double L5Average = 0.0;
        double L6Average = 0.0;
        double expResult = 0.0;
        double result = AcademicCalculator.MethodB(L5Average, L6Average);
        assertEquals(expResult, result);
    }

    /**
     * Test of MethodC method, of class AcademicCalculator.
     */
    @Test
    public void testMethodC() {
        System.out.println("MethodC");
        double L6Average = 0.0;
        double expResult = 0.0;
        double result = AcademicCalculator.MethodC(L6Average);
        assertEquals(expResult, result);
    }

    /**
     * Test of MethodD method, of class AcademicCalculator.
     */
    @Test
    public void testMethodD() {
        System.out.println("MethodD");
        ArrayList marks = new ArrayList<Double>();
        marks.add(80.00); marks.add(80.00); marks.add(80.00);
        String expResult = "1st";
        String result = AcademicCalculator.MethodD(marks);
        assertEquals(expResult, result);
    }

    /**
     * Test of GetCourseAverage method, of class AcademicCalculator.
     */
    @Test
    public void testGetCourseAverage() {
        System.out.println("GetCourseAverage");
        ArrayList<Integer> credits = null;
        ArrayList<Integer> marks = null;
        double expResult = 0.0;
        double result = AcademicCalculator.GetCourseAverage(credits, marks);
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getHighestResult method, of class AcademicCalculator.
     */
    @Test
    public void testGetHighestResult() {
        System.out.println("getHighestResult");
        String[] methodResults = {"Fail", "2:1", "2:2"};
        String expResult = "2:1";
        String result = AcademicCalculator.getHighestResult(methodResults);
        assertEquals(expResult, result);
    }
    
}
