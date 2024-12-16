/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PostTask;


import java.sql.*; //Importing java.sql package

public class SoftwareDevL51 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
                /*
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "rizz");//Establishing connection
                System.out.println("Connected With the database successfully"); //Message after successful connection
                //Creating PreparedStatement Object
                PreparedStatement preparedStatement =connection.prepareStatement("insert into student values(?,?,?,?)");
 
                //Setting values for each parameter
                preparedStatement.setString(1,"1");
                preparedStatement.setString(2,"Mehtab");
                preparedStatement.setString(3,"Computer");
                preparedStatement.setString(4,"Ranchi");
 
                //Executing Query
                preparedStatement.executeUpdate();
                System.out.println("Data inserted Successfully");  
                */
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystem", "root", "rizz");//Establishing connection
                System.out.println("Connected With the database successfully"); //Message after successful connection
                PreparedStatement preparedStatement =connection.prepareStatement("select * from accounts where accountID=?");
                System.out.println("Select worked");
                preparedStatement.setString(1, "123456789");
                System.out.println("Set worked");
                ResultSet resultSet = preparedStatement.executeQuery();
                
                
                
                
                
            } catch (SQLException e) {
 
                    System.out.println("Error while connecting to the database"); //Message if something goes wrong while conneting to the database
 
                            }
    }
    
}
