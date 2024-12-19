package com.mycompany.softwaredevassignment;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/*
JSwing code for displaying the calculator, table of modules and handling the user inputs.
*/

public class CalculatorGUI extends javax.swing.JFrame {
    private int isAdmin;
    private AdminMenu admin = null;
    /**
     * Creates new form CalculatorGUI
     */
    public CalculatorGUI(String course, int isAdmin) {
        initComponents();
        this.isAdmin = isAdmin;
        initTable(course);
    }
    
    // Used for close processes where the window closes but not the application exitting.
    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    
    // Used to end the application.
    public void quit(){
        System.exit(0);
    }
    
    // Used to fill the Jtable with the modules, depedent on course passed in, in addition to having two seperate tables for the different levels. 
    void initTable(String course) {
        int level5 = 5; int level6 = 6;
        model = new ModuleTableModel(course, level5);
        
        level5Table.setModel(model);
        
        for (int col = 0; col < level5Table.getColumnCount(); col++) {
            TableColumn column = level5Table.getTableHeader().getColumnModel().getColumn(col);
            column.setHeaderValue(model.getColumnNames()[col]);
        }
        
        model2 = new ModuleTableModel(course, level6);
        
        level6Table.setModel(model2);
        
        for (int col = 0; col < level6Table.getColumnCount(); col++) {
            TableColumn column = level6Table.getTableHeader().getColumnModel().getColumn(col);
            column.setHeaderValue(model2.getColumnNames()[col]);
        }
    }
    
    // This method is used to start the overall calculation, first by checking whether the table rows, seeing which modules have had marks input into
    // them, then comparing the module's credits to the limit, if its over then it'll return an error response. However, if level 5's modules aren't
    // at the credit limit, It will test Method C, which runs a similar process and see if method C can be done, otherwise returning an error.
    // Assuming both level 5 and level 6 have valid modules, it will then query methods from Academic Calculator to recieve the results for the methods.
    // and retrieve those results to then output to the console.
    void updateCalculation() {
        int level5Credits = 0; int creditLimit = 120;
        ArrayList level5MarksArray = new ArrayList<Integer>(); ArrayList level5CreditsArray = new ArrayList<Integer>(); 
        try {
            for (int i = 0; i < level5Table.getRowCount(); i++) {
                for (int j = 0; j < level5Table.getColumnCount(); j++) {
                    if (level5Table.getColumnName(j).equals("marks")) {
                        if ((Integer.parseInt(level5Table.getValueAt(i, j).toString()) > 0)) {
                            for (int k = 0; k < level5Table.getColumnCount(); k++) {
                                if (level5Table.getColumnName(k).equals("credits")) {
                                    level5Credits += Integer.parseInt(level5Table.getValueAt(i, k).toString());
                                    level5MarksArray.add(Integer.parseInt(level5Table.getValueAt(i, j).toString()));
                                    level5CreditsArray.add(Integer.parseInt(level5Table.getValueAt(i, k).toString()));
                                }
                            }
                        }
                    }
                }
            }
            if (!(level5Credits == creditLimit)) {
                if (updateCalculationMethodC()) {
                    return;
                } 
                outputTextArea.setText("Error: Level 5 doesn't have " + creditLimit + " Credits worth of Modules.");
                return;
            }
         
        int level6Credits = 0;
        ArrayList level6CreditsArray = new ArrayList<Integer>(); ArrayList level6MarksArray = new ArrayList<Integer>(); 
        
            for (int i = 0; i < level6Table.getRowCount(); i++) {
                for (int j = 0; j < level6Table.getColumnCount(); j++) {
                    if (level6Table.getColumnName(j).equals("marks")) {
                        if ((Integer.parseInt(level6Table.getValueAt(i, j).toString()) > 0)) {
                            for (int k = 0; k < level6Table.getColumnCount(); k++) {
                                if (level6Table.getColumnName(k).equals("credits")) {
                                    level6Credits += Integer.parseInt(level6Table.getValueAt(i, k).toString());
                                    level6MarksArray.add(Integer.parseInt(level6Table.getValueAt(i, j).toString()));
                                    level6CreditsArray.add(Integer.parseInt(level6Table.getValueAt(i, k).toString()));
                                }
                            }
                        }
                    }
                }
            }
            if (!(level6Credits == creditLimit)) {
                outputTextArea.setText("Error: Level 6 doesn't have " + creditLimit + " Credits worth of Modules.");
                return;
            }

            ArrayList combinedMarksArray = new ArrayList<Integer>();
            combinedMarksArray.addAll(level5MarksArray);
            combinedMarksArray.addAll(level6MarksArray);
            double level5Avg = AcademicCalculator.GetCourseAverage(level5CreditsArray, level5MarksArray);
            double level6Avg = AcademicCalculator.GetCourseAverage(level6CreditsArray, level6MarksArray);
            String methodA = AcademicCalculator.CheckClassification(AcademicCalculator.MethodA(level5Avg, level6Avg));
            String methodAPercentage = String.format("%.2f", AcademicCalculator.MethodA(level5Avg, level6Avg));
            String methodB = AcademicCalculator.CheckClassification(AcademicCalculator.MethodB(level5Avg, level6Avg));
            String methodBPercentage = String.format("%.2f", AcademicCalculator.MethodB(level5Avg, level6Avg));
            String methodD = AcademicCalculator.MethodD(combinedMarksArray);
            String[] combinedMethods = {methodA, methodB, methodD};
            String highestGrade = AcademicCalculator.getHighestResult(combinedMethods);
            
            
            outputTextArea.setText("-Results-");
            outputTextArea.append("\nClassification: " + highestGrade + "\n");
            outputTextArea.append("\nMethod A: " + methodA + " | Average of Level 5 and Level 6: " + methodAPercentage);
            outputTextArea.append("\nMethod B: " + methodB + " | Average of Level 5 and Level 6 twice: " + methodBPercentage);
            outputTextArea.append("\nMethod D: " + methodD + " | Average Grade from Modules.");
            

        } catch (NumberFormatException e) {
            System.err.println("Error: Problem with converting data.");
        }
        
        
        
    }

    // This method is done after identifying that level 5 doesn't have enough credits to make method a and b be valid. It then checks if level 6
    // has the valid number of credits, if not, then it'll return false, but if it does, then it'll call academic calculator methods to get the method
    // results and output the results to the text area.
    boolean updateCalculationMethodC() {
        int level6Marks = 0; int level6Credits = 0; int creditLimit = 120;
        ArrayList level6MarksArray = new ArrayList<Integer>(); ArrayList level6CreditsArray = new ArrayList<Integer>();         
        for (int i = 0; i < level6Table.getRowCount(); i++) {
                for (int j = 0; j < level6Table.getColumnCount(); j++) {
                    if (level6Table.getColumnName(j).equals("marks")) {
                        if ((Integer.parseInt(level6Table.getValueAt(i, j).toString()) > 0)) {
                            level6Marks += Integer.parseInt(level6Table.getValueAt(i, j).toString());
                            for (int k = 0; k < level6Table.getColumnCount(); k++) {
                                if (level6Table.getColumnName(k).equals("credits")) {
                                    level6Credits += Integer.parseInt(level6Table.getValueAt(i, k).toString());
                                    level6MarksArray.add(Integer.parseInt(level6Table.getValueAt(i, j).toString()));
                                    level6CreditsArray.add(Integer.parseInt(level6Table.getValueAt(i, k).toString()));
                                }
                            }
                        }
                    }
                }
            }
        if (!(level6Credits == creditLimit)) {
            return false;
            }
        double level6Avg = AcademicCalculator.GetCourseAverage(level6CreditsArray, level6MarksArray);
        String methodC = AcademicCalculator.CheckClassification(AcademicCalculator.MethodC(level6Avg));
        String methodCPercentage = String.format("%.2f", AcademicCalculator.MethodC(level6Avg));
        String methodD = AcademicCalculator.MethodD(level6MarksArray);
        String[] combinedMethods = {methodC, methodD};
        String highestGrade = AcademicCalculator.getHighestResult(combinedMethods);
        
        outputTextArea.setText("-Results-");
        outputTextArea.append("\nClassification: " + highestGrade + "\n");
        outputTextArea.append("\nMethod C: " + methodC + " | Average of Level 6: " + methodCPercentage);
        outputTextArea.append("\nMethod D: " + methodD + " | Average Grade from Modules.");
        
        return true;
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signOutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        level5Table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        level6Table = new javax.swing.JTable();
        level5Label = new javax.swing.JLabel();
        level6Label = new javax.swing.JLabel();
        changeMarksButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        changeCourseButton = new javax.swing.JButton();
        courseChangeField = new javax.swing.JTextField();
        LTSUIcon = new javax.swing.JLabel();
        adminMenuButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        signOutButton.setText("Sign Out");
        signOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutButtonActionPerformed(evt);
            }
        });

        level5Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(level5Table);

        level6Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(level6Table);

        level5Label.setText("Level 5");

        level6Label.setText("Level 6");

        changeMarksButton.setText("Change Marks");
        changeMarksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeMarksButtonActionPerformed(evt);
            }
        });

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane3.setViewportView(outputTextArea);

        changeCourseButton.setText("Change Course");
        changeCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeCourseButtonActionPerformed(evt);
            }
        });

        LTSUIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\aws.admin\\OneDrive - Leeds Trinity University\\NetBeansProjects\\SoftwareDevAssignment\\src\\resources\\images\\LTSU Logo mini.png")); // NOI18N

        adminMenuButton.setText("Admin Menu");
        adminMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminMenuButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LTSUIcon)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(level5Label)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(level6Label))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(exitButton)
                                        .addGap(44, 44, 44)
                                        .addComponent(signOutButton)
                                        .addGap(68, 68, 68))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(changeCourseButton)
                                    .addComponent(changeMarksButton))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adminMenuButton)
                                    .addComponent(courseChangeField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LTSUIcon)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(level5Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(level6Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(changeMarksButton)
                                    .addComponent(adminMenuButton))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(changeCourseButton)
                                    .addComponent(courseChangeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(signOutButton)
                                    .addComponent(exitButton))))
                        .addGap(35, 35, 35))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutButtonActionPerformed
        // Used to close the calculator JFrame, admin menu if open, and return back to login
        close();
        if (admin != null) {
            admin.close();
        }
        LoginPage login = new LoginPage();
        login.setVisible(true);
    }//GEN-LAST:event_signOutButtonActionPerformed

    private void changeMarksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeMarksButtonActionPerformed
        // This method is used to input the user's desired marks within the modules on the table, getting the selected rows from the user and 
        // inputting the marks from the JOptionPane.
        if (level5Table.getSelectedRow() >= 0 || level6Table.getSelectedRow() >= 0) {
            String input = JOptionPane.showInputDialog("Enter your marks");
            if (input == null) {
                return;
            }

            int marks;
            try {
                marks = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Please enter a valid number.");
                return;
            }
            
            if (!(marks >= 40 && marks <= 100) && !(marks == 0)) {
                JOptionPane.showMessageDialog(null, "Error: Marks outside of range, be within 40 and 100.");
                return;
            }

                if (level5Table.getSelectedRow() >= 0) {
                    int markIndex = -1;
                    for (int i = 0; i < level5Table.getColumnCount(); i++) {
                        if (level5Table.getColumnName(i).equals("marks")) {
                            markIndex = i;
                            int[] selection = level5Table.getSelectedRows();
                            for (int j = 0; j < selection.length; j++) {
                                level5Table.setValueAt(marks, selection[j], markIndex);
                            }
                            break;
                        }
                    }
                    if (markIndex == -1) {
                        JOptionPane.showMessageDialog(null, "Error: Level 5 table doesn't have a marks column.");
                    }
                }

                if (level6Table.getSelectedRow() >= 0) {
                    int markIndex = -1;
                    for (int i = 0; i < level6Table.getColumnCount(); i++) {
                        if (level6Table.getColumnName(i).equals("marks")) {
                            markIndex = i;
                            int[] selection = level6Table.getSelectedRows();
                            for (int j = 0; j < selection.length; j++) {
                                level6Table.setValueAt(marks, selection[j], markIndex);
                            }
                            break;
                        }
                    }
                    if (markIndex == -1) {
                        JOptionPane.showMessageDialog(null, "Error: Level 6 table doesn't have a marks column.");
                    }
                }
            
            updateCalculation();
        
        } else {
            JOptionPane.showMessageDialog(null, "Error: Select a row.");
        }
    }//GEN-LAST:event_changeMarksButtonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // Used to clear the selected rows from the tables by clicking on the empty space on the frame.
        level5Table.clearSelection(); level6Table.clearSelection();
    }//GEN-LAST:event_formMouseClicked

    private void changeCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeCourseButtonActionPerformed
        // This method gets the course within the field, then tests to see whether the inputted string is a course in the database, then uses
        // that course to then populate the tables on the GUI.
        String course = courseChangeField.getText();
        if (Database.isCourse(course)) {
            initTable(course);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Course not found.");
        }
        
    }//GEN-LAST:event_changeCourseButtonActionPerformed

    private void adminMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminMenuButtonActionPerformed
        // Used to open the Admin Menu, but first checks to see if the account logged into had administrator privileges, then checks to see if there
        // is already a Admin Menu open, if so, then it doesn't create a new one and makes it visible if not already, while creating a new one if there
        // isn't already an Admin Menu.
        if (isAdmin == 1) {
            if (admin == null) {
                admin = new AdminMenu();
                admin.setVisible(true);
            } else {
                admin.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(null, "You lack the administrator privileges to access this menu.");
        }
    }//GEN-LAST:event_adminMenuButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // Used to exit from the application.
        quit();
    }//GEN-LAST:event_exitButtonActionPerformed

    private ModuleTableModel model;
    private ModuleTableModel model2;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LTSUIcon;
    private javax.swing.JButton adminMenuButton;
    private javax.swing.JButton changeCourseButton;
    private javax.swing.JButton changeMarksButton;
    private javax.swing.JTextField courseChangeField;
    private javax.swing.JButton exitButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel level5Label;
    private javax.swing.JTable level5Table;
    private javax.swing.JLabel level6Label;
    private javax.swing.JTable level6Table;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JButton signOutButton;
    // End of variables declaration//GEN-END:variables
}
