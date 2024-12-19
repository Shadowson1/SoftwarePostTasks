package com.mycompany.softwaredevassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
This class handles the queries that are done in relation to the SQL database
*/

public class Database {
    
    // Method to find if an account is present in the database, then returning whether it is found.
    public static boolean findAccount(String username, String password){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
            System.out.println("Connected With the database successfully"); //Message after successful connection  
            
            PreparedStatement checkForAccount = connection.prepareStatement("select username, password from account where username = ? and password = ?");
            checkForAccount.setString(1, username);
            checkForAccount.setString(2, password);
            ResultSet resultSet = checkForAccount.executeQuery();
            
            // If an account was found, it'll return true, if not, then false.
            if (resultSet.next()) {
                connection.close();
                checkForAccount.close();
                resultSet.close();
                return true;
            } else {
                connection.close();
                checkForAccount.close();
                resultSet.close();
                return false;
            }   
        } 
        catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return false;
        }
    }
    
    // Method to find if an account has admin rights. Since SQL stores booleans as integer of 0 or 1, it'll return the sql representation
    // which defaults to 0 (false), and changing to 1 if the account is present and it does have admin rights.
    public static int findisAdminfromAccount(String username, String password) {
        int isAdmin = 0;
         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
            System.out.println("Connected With the database successfully"); //Message after successful connection  
            
            PreparedStatement checkForAdmin = connection.prepareStatement("select isAdministrator from account where username = ? and password = ?");
            checkForAdmin.setString(1, username);
            checkForAdmin.setString(2, password);
            ResultSet resultSet = checkForAdmin.executeQuery();
            
            // If an account was found, it'll return whether the account is an admin, if not, then 0 (false).
            if (resultSet.next()) {
                isAdmin = resultSet.getInt("isAdministrator");
                connection.close();
                checkForAdmin.close();
                resultSet.close();
                return isAdmin;
            } else {
                connection.close();
                checkForAdmin.close();
                resultSet.close();
                return isAdmin;
            }   
        } 
        catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return isAdmin;
        }
    }
    
    // Method to return the course associated from an account, returning an empty string if there isn't one.
    public static String findCoursefromAccount(String username) {
        String course = "";
         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
            System.out.println("Connected With the database successfully"); //Message after successful connection  
            
            PreparedStatement checkForCourse = connection.prepareStatement("select accountCourse from account where username = ?");
            checkForCourse.setString(1, username);
            ResultSet resultSet = checkForCourse.executeQuery();
            
            // If an account was found, it'll return the course, if not, then a empty String.
            if (resultSet.next()) {
                course = resultSet.getString("accountCourse");
                connection.close();
                checkForCourse.close();
                resultSet.close();
                return course;
            } else {
                connection.close();
                checkForCourse.close();
                resultSet.close();
                return course;
            }   
        } 
        catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return course;
        }
    }
    
    // Method to see whether the course is within the database, returning boolean if it is found.
    public static boolean isCourse(String course) {
         try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
            System.out.println("Connected With the database successfully"); //Message after successful connection  
            
            PreparedStatement checkForCourse = connection.prepareStatement("select * from course where courseID = ?");
            checkForCourse.setString(1, course);
            ResultSet resultSet = checkForCourse.executeQuery();
            
            // If an course was found, it'll return true, if not, then a false.
            if (resultSet.next()) {
                connection.close();
                checkForCourse.close();
                resultSet.close();
                return true;
            } else {
                connection.close();
                return false;
            }   
        } 
        catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return false;
        }
    }
    
    
    
    // Method to conduct an insert operation into a table within the database. First it checks to see what table is being inserted into
    // then loads the statement with the inputted data, then executes. If it fails, the method returns the error message to be displayed. 
    public static String insertSQL(String table, ArrayList<String> data) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
            System.out.println("Connected With the database successfully"); //Message after successful connection  
            
            PreparedStatement insertTableData;
            if (table.equals("modules")) {
                insertTableData = connection.prepareStatement("insert into modules(moduleID, courseID, level, moduleName, credits, isCore) values (?, ?, ?, ?, ?, ?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                insertTableData.setInt(1, Integer.parseInt(data.get(0)));
                insertTableData.setString(2, data.get(1));
                insertTableData.setInt(3, Integer.parseInt(data.get(2)));
                insertTableData.setString(4, data.get(3));
                insertTableData.setInt(5, Integer.parseInt(data.get(4)));
                insertTableData.setInt(6, Integer.parseInt(data.get(5)));
            } else if (table.equals("account")) {
                insertTableData = connection.prepareStatement("insert into account(username, password, accountCourse, isAdministrator) values (?, ?, ?, ?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                insertTableData.setString(1, data.get(0));
                insertTableData.setString(2, data.get(1));
                insertTableData.setString(3, data.get(2));
                insertTableData.setInt(4, Integer.parseInt(data.get(3)));
            } else if (table.equals("course")) {
                insertTableData = connection.prepareStatement("insert into course(courseID, courseName) values (?, ?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                insertTableData.setString(1, data.get(0));
                insertTableData.setString(2, data.get(1));
            } else {connection.close(); return "Couldn't find table";}
            insertTableData.executeUpdate();
            
            
        
            connection.close();
            insertTableData.close();
            return null;
        } catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return e.getMessage();
        } catch (NumberFormatException e) {
            System.err.println("Error with parsing data.");
            return e.getMessage();
        }
    }
    
    // Method to execute an update operation into a table within the database. First it tries to convert the rowID and value into integers,
    // as the tables have different variable types for the primary key, and the value being inputted may be integers and need to be converted
    // Then a check to what table is being updated, preparing the statement in addition to passing the column to be updated. Then it executes
    // the SQL update, with a catch to get any error and return it.
    public static String updateSQL(String table, int row, int col, String colName, String rowID, String value) {
        boolean convertRowIDSuccess = false;
        boolean convertValueSuccess = false;
        int convertedRowID = 0;
        int convertedValue = 0;
        try {
            convertedRowID = Integer.parseInt(rowID);
            convertRowIDSuccess = true;
        } catch (NumberFormatException e) {}
        try {
            convertedValue = Integer.parseInt(value);
            convertValueSuccess = true;
        } catch (NumberFormatException e) {}
        
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
        System.out.println("Connected With the database successfully"); //Message after successful connection  
        

        PreparedStatement updateTableData;
        if (table.equals("modules")) {
            String query = "update modules set " + colName + "=? where moduleNO=?";
            updateTableData = connection.prepareStatement(query);
        } else if (table.equals("account")) {
            String query = "update account set " + colName + "=? where accountID=?";
            updateTableData = connection.prepareStatement(query);
        } else if (table.equals("course")) {
            String query = "update course set " + colName + "=? where courseID=?";
            updateTableData = connection.prepareStatement(query);
        } else {connection.close(); return "Error finding table.";}

        if (convertValueSuccess) {
            updateTableData.setInt(1, convertedValue);
        } else {
            updateTableData.setString(1, value);
        }
        if (convertRowIDSuccess) {
            updateTableData.setInt(2, convertedRowID);
        } else {
            updateTableData.setString(2, rowID);
        }
        
        updateTableData.executeUpdate();
        
        connection.close();
        updateTableData.close();
        return null;
        } catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return e.getMessage();
        }
        
        
    }
    
    // Method to execute a delete operation into a table within the database. First attempts to convert the rowID into an integer incase it is
    // Then prepares the statement depending on the table chosen, and executes the delete, returning any error that occurs in the process.
    public static String deleteSQL(String table, String rowID) {
        boolean convertRowIDSuccess = false;
        int convertedRowID = 0;
        try {
            convertedRowID = Integer.parseInt(rowID);
            convertRowIDSuccess = true;
        } catch (NumberFormatException e) {
        }
        
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
        System.out.println("Connected With the database successfully"); //Message after successful connection  
        

        PreparedStatement deleteTableData;
        if (table.equals("modules")) {
            deleteTableData = connection.prepareStatement("delete from modules where moduleNo=?");
        } else if (table.equals("account")) {
            deleteTableData = connection.prepareStatement("delete from account where accountID=?");
        } else if (table.equals("course")) {
            deleteTableData = connection.prepareStatement("delete from course where courseID=?");
        } else {connection.close(); return "Error finding table.";}
        
        if (convertRowIDSuccess) {
            deleteTableData.setInt(1, convertedRowID);
        } else {
            deleteTableData.setString(1, rowID);
        }
        
        deleteTableData.execute();
        
        connection.close();
        deleteTableData.close();
        return null;
        } catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
            return e.getMessage();
        }
    }

    
}
