/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package softwaredevl5.pkg1;

import java.sql.*;

/**
 * link to guide https://www.tutorialsfield.com/how-to-connect-mysql-database-in-java-using-netbeans/
 * @author aws.admin
 */
public class JavaMySQLConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "rizz");//Establishing connection
                      System.out.println("Connected With the database successfully");
                //Using SQL DELETE query to delete data
                PreparedStatement preparedStatement =connection.prepareStatement("delete from student where STUDNAME=?");
 
               //Setting value
               preparedStatement.setString(1,"Mehtab");
               
               //Executing Query
               preparedStatement.execute();
               System.out.println("Data deleted successfully");
               
 
 
        } catch (SQLException e) {
 
                System.out.println("Error while connecting to the database");
 
                        }
                    }
 
                }
