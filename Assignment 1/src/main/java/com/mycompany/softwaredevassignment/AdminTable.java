package com.mycompany.softwaredevassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

/*
This class is used to get the SQL database's data to then populate the table within the admin table.
*/

public class AdminTable extends AbstractTableModel {
    //Atttributes
    private String[] columnNames;
    private Object[][] data;
    
    //Constructor
    public AdminTable(String table) {
        // Establishing a connection to the databse, then preparing the statement to select the data from the desired table.
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
        System.out.println("Connected With the database successfully"); //Message after successful connection  
        PreparedStatement getTableData;
        if (table.equals("modules")) {
            getTableData = connection.prepareStatement("select moduleNO, moduleID, courseID, level, moduleName, credits, isCore from modules", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } else if (table.equals("account")) {
            getTableData = connection.prepareStatement("select * from account", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } else if (table.equals("course")) {
            getTableData = connection.prepareStatement("select * from course", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } else {
            connection.close();
            return;
        }
        
        // Execute the prepared statement and get the result set and the metadata from the result set
        ResultSet sqlModule = getTableData.executeQuery();
        ResultSetMetaData sqlModuleMD = sqlModule.getMetaData();
        
        // This segment is used to find the number of columns and then populate a string array of the column names then use that to update the columnNames attribute
        int cols = sqlModuleMD.getColumnCount();
        String[] colNames = new String[cols];
        for(int i=0; i<cols; i++) {
            colNames[i]=sqlModuleMD.getColumnName(i+1);
        }
        int columnNamesLength = colNames.length;
        columnNames = Arrays.copyOf(colNames, columnNamesLength);

        
        // Finds the number of rows within the database
        int rowLength = 0;
        if (sqlModule.last()) {
            rowLength = sqlModule.getRow();
            sqlModule.beforeFirst();
        }
        
        // Now goes through the result set, getting each of the attributes within the SQL table and saving them to dataRow to then save to data
        data = new Object[rowLength][columnNamesLength];
        int i = 0;
            if (table.equals("modules")) {
                while (sqlModule.next()) {
                    int moduleNo = sqlModule.getInt("moduleNO");
                    int moduleID = sqlModule.getInt("moduleID");
                    String courseID = sqlModule.getString("courseID");
                    int level = sqlModule.getInt("level");
                    String moduleName = sqlModule.getString("moduleName");
                    int credits = sqlModule.getInt("credits");
                    int isCore = sqlModule.getInt("isCore");

                    Object[] dataRow = new Object[]{moduleNo, moduleID, courseID, level, moduleName, credits, isCore};

                    data[i] = Arrays.copyOf(dataRow, columnNamesLength);
                    i++;
                }
            } else if (table.equals("account")) {
                    while (sqlModule.next()) {
                    int accountID = sqlModule.getInt("accountID");
                    String username = sqlModule.getString("username");
                    String password = sqlModule.getString("password");
                    String accountCourse = sqlModule.getString("accountCourse");
                    int isAdministrator = sqlModule.getInt("isAdministrator");

                    Object[] dataRow = new Object[]{accountID, username, password, accountCourse, isAdministrator};

                    data[i] = Arrays.copyOf(dataRow, columnNamesLength);
                    i++;
                }
            } else if (table.equals("course")) {
                while (sqlModule.next()) {
                    String courseID = sqlModule.getString("courseID");
                    String courseName = sqlModule.getString("courseName");

                    Object[] dataRow = new Object[]{courseID, courseName};

                    data[i] = Arrays.copyOf(dataRow, columnNamesLength);
                    i++;
                }
            }
        // Closes the sql connections
        getTableData.close();
        sqlModule.close();
        connection.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to Database. \n Message: " + e.getMessage());
        }
    }
    
    //Overridden methods
    //Accessors
    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt (int row, int column) {
        return data[row][column];
    }
    
    @Override
    public void setValueAt (Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public String[] getColumnNames() {
        return columnNames;
    }
}
