package com.mycompany.softwaredevassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

/*
Class used to populate the table within the Calculator frame, and handle the queries in relation to the data the table it contains
*/
public class ModuleTableModel extends AbstractTableModel {
    //Atttributes
    private String[] columnNames;
    private Object[][] data;
    
    //Constructor
    public ModuleTableModel(String courseID, int level) {
        // Establishing a connection to the databse, then preparing the statement to select the data from the desired table.
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academiccalculator", "root", "rizz");//Establishing connection
        System.out.println("Connected With the database successfully"); //Message after successful connection  
        
        PreparedStatement getModuleTableData;
            if (level == 5 || level == 6) {
                getModuleTableData = connection.prepareStatement("select moduleName, moduleID, credits, marks from modules where courseID = ? and level =?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                getModuleTableData.setString(1, courseID);
                getModuleTableData.setInt(2, level);
            } else {
                return;
            }
        
        // Execute the prepared statement and get the result set and the metadata from the result set
        ResultSet sqlModule = getModuleTableData.executeQuery();
        ResultSetMetaData sqlModuleMD = sqlModule.getMetaData();
        
        // This segment is used to find the number of columns and then populate a string array of the column names then use that to update the columnNames
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
        while (sqlModule.next()) {
            String moduleName = sqlModule.getString("moduleName");
            int moduleID = sqlModule.getInt("moduleID");
            int credits = sqlModule.getInt("credits");
            int marks = sqlModule.getInt("marks");

            Object[] dataRow = new Object[]{moduleName, moduleID, credits, marks};

            data[i] = Arrays.copyOf(dataRow, columnNamesLength);
            i++;
        }
        // Closes the sql connections
        connection.close();
        getModuleTableData.close();
        sqlModule.close();
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
