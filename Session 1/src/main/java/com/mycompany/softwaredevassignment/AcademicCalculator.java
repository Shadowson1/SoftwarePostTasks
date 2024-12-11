/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.softwaredevassignment;
import java.util.ArrayList;
/**
 *
 * @author aws.admin
 */
public class AcademicCalculator {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    public void ModuleRegistration(int[] credits, int[] mark) {
        int totalModules = credits.length;
        ArrayList<ArrayList<Integer>> combinedModules = new ArrayList<>(totalModules);
        for (int i = 0; i < credits.length; ++i) {
            combinedModules.add(new ArrayList());
            combinedModules.get(i).add(credits[i], mark[i]);
        }
        System.out.println(combinedModules);
        
    }
    
    public static double GetCourseAverage(int[] credits, int[] marks) {
        int creditsTotal = 0;
        int combinedTotal = 0;
        double courseAverage = 0;
        for (int i = 0; i < credits.length; ++i) {
            creditsTotal += credits[i];
            combinedTotal += (credits[i] * marks[i]);
        }
        courseAverage += combinedTotal / creditsTotal;
        return courseAverage;
    }
    
    public static String CheckClassification(double achievedAverage) {
        double first = 69.50;
        double twoOne = 59.50;
        double twoTwo = 49.50;
        double third = 39.50;
        
        if (achievedAverage >= first) {
            return("1st");
        }
        else if (achievedAverage >= twoOne) {
            return("2:1");
        }
        else if (achievedAverage >= twoTwo) {
            return("2:2");
        }
        else if (achievedAverage >= third) {
            return("3rd");
        }
        else {
            return("Fail");
        }
    }
    
    public static String MethodA(double L5Average, double L6Average) {
        int NoOfAverages = 2;
        double overallAverage = (L5Average + L6Average) / NoOfAverages;
        return CheckClassification(overallAverage);
    }
    
    public static String MethodB(double L5Average, double L6Average) {
        int NoOfAverages = 3;
        double overallAverage = (L5Average + L6Average + L6Average) / NoOfAverages;
        return CheckClassification(overallAverage);
    }
    
    public static String MethodC(double L5Average, double L6Average) {
        int NoOfAverages = 1;
        double overallAverage = L6Average / NoOfAverages;
        return CheckClassification(overallAverage);
    }
    
        public static String MethodD(int[] credits, int[] marks) {
        String first = "1st";
        int firstMode = 0;
        String secondFirst = "2:1";
        int secondFirstMode = 0;
        String secondSecond = "2:2";
        int secondSecondMode = 0;
        String third = "3rd";
        int thirdMode = 0;
        String fail = "Fail";
        int failMode = 0;
        for (int i = 0; i < credits.length; ++i) {
            int courseCredits = credits[i];
            int courseMarks = marks[i];
            double percentageResult = courseCredits / courseMarks;
            if (CheckClassification(percentageResult).equals(first)) {
                firstMode ++;
            }
            else if (CheckClassification(percentageResult).equals(secondFirst)) {
                secondFirstMode ++;
            }
            else if (CheckClassification(percentageResult).equals(secondSecond)) {
                secondSecondMode ++;
            }
            else if (CheckClassification(percentageResult).equals(third)) {
                thirdMode ++;
            }
            else if (CheckClassification(percentageResult).equals(fail)) {
                failMode ++;
            } 
        }
        
        if (firstMode >= secondFirstMode && firstMode >= secondSecondMode && firstMode >= thirdMode && firstMode >= failMode) {
            return first;
        }
        else if (secondFirstMode >= secondSecondMode && secondFirstMode >= thirdMode && secondFirstMode >= failMode) {
            return secondFirst;
        }
        else if (secondSecondMode >= thirdMode && secondSecondMode >= failMode) {
            return secondSecond;
        }
        else if (thirdMode >= failMode) {
            return third;
        }
        else {
            return fail;
        }
    }
}
