/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staffInternalPage;

import adminInternalFrame.*;
import config.connectDB;
import floatedPage.addStudent;
import floatedPage.addUser;
import floatedPage.editStudent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author admin
 */
public class student extends javax.swing.JInternalFrame {

    private javax.swing.table.DefaultTableModel tableModel;

    /**
     * Creates new form student
     */
    public student() {
        initComponents();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);

        // Initialize table model
        tableModel = (javax.swing.table.DefaultTableModel) studentstbl.getModel();
        
        // Add key listener to search field
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStudents();
            }
        });
        
        displayData();
    }
    
    public void displayData() {
        try {
            // Clear existing table data
            tableModel.setRowCount(0);
            
            connectDB dbc = new connectDB();
            ResultSet rs = dbc.getData("SELECT * FROM student ORDER BY name");
            
            while(rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("program"),
                    rs.getString("year"),
                    rs.getString("section")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
        } catch(SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage(), 
                "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchStudents() {
        String searchText = searchField.getText().toLowerCase().trim();
        
        // If search field is empty or contains only "search...", show all students
        if (searchText.isEmpty() || searchText.equals("search...")) {
            displayData();
            return;
        }

        try {
            // Clear existing table data
            tableModel.setRowCount(0);
            
            connectDB dbc = new connectDB();
            String searchPattern = "'%" + searchText + "%'";
            String query = "SELECT * FROM student WHERE " +
                          "LOWER(name) LIKE " + searchPattern + " OR " +
                          "LOWER(program) LIKE " + searchPattern + " OR " +
                          "LOWER(year) LIKE " + searchPattern + " OR " +
                          "LOWER(section) LIKE " + searchPattern + " " +
                          "ORDER BY name";
            
            ResultSet rs = dbc.getData(query);
            
            while(rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("program"),
                    rs.getString("year"),
                    rs.getString("section")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error searching students: " + ex.getMessage(), 
                "Database Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentstbl = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 510));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 70, -1));

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 70, -1));

        studentstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Program", "Year", "Section"
            }
        ));
        jScrollPane1.setViewportView(studentstbl);

        jScrollPane2.setViewportView(jScrollPane1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 390));

        refreshButton.setText("Refresh");
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });
        jPanel1.add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, -1, -1));

        searchField.setText("search...");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        jPanel1.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Student");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        // TODO: Create and add your addStudent panel here
        addStudent add = new addStudent();
        
        dialog.add(add);
        dialog.setSize(325, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int rowIndex = studentstbl.getSelectedRow();
        
        if(rowIndex < 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "Please select a student to edit!");
            return;
        }
        
        try {
            javax.swing.table.TableModel tbl = studentstbl.getModel();
            // Get data directly from the table
            int id = Integer.parseInt(tbl.getValueAt(rowIndex, 0).toString());
            String name = tbl.getValueAt(rowIndex, 1).toString();
            String program = tbl.getValueAt(rowIndex, 2).toString();
            int year = Integer.parseInt(tbl.getValueAt(rowIndex, 3).toString());
            String section = tbl.getValueAt(rowIndex, 4).toString();
            
            // Create and show edit dialog
            javax.swing.JDialog dialog = new javax.swing.JDialog();
            editStudent edit = new editStudent();
            
            // Set the student data to the form fields
            edit.setStudentData(id, name, program, year, section);
            
            dialog.add(edit);
            dialog.setSize(325, 500);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
            
            // Refresh the table after dialog closes
            displayData();
            
        } catch(Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        

    }

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {
        displayData();
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        searchStudents();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTable studentstbl;
    // End of variables declaration//GEN-END:variables
}
