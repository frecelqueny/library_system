/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminInternalFrame;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import config.connectDB;

/**
 *
 * @author admin
 */
public class borrow extends javax.swing.JInternalFrame {
    private connectDB db;
    private javax.swing.table.DefaultTableModel tableModel;

    /**
     * Creates new form borrow
     */
    public borrow() {
        initComponents();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        // Initialize database connection
        db = new connectDB();
        
        // Initialize table model
        tableModel = (javax.swing.table.DefaultTableModel) borrowtbl.getModel();
        
        // Add key listener to search field
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBorrows();
            }
        });
        
        // Load initial data
        displayData();
    }
    
    public void displayData() {
        try {
            // Clear existing table data
            tableModel.setRowCount(0);
            
            String query = "SELECT b.id, s.Name as student_name, bk.Title as book_title, " +
                          "b.borrow_date, b.return_date, b.status " +
                          "FROM borrow b " +
                          "INNER JOIN student s ON b.student_id = s.ID " +
                          "INNER JOIN book bk ON b.book_id = bk.ID " +
                          "ORDER BY b.borrow_date DESC";
            
            ResultSet rs = db.getData(query);
            
            while(rs.next()) {
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("student_name"),
                    rs.getString("book_title"),
                    rs.getString("borrow_date"),
                    rs.getString("return_date"),
                    rs.getString("status")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading borrow records: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBorrows() {
        String searchText = searchField.getText().toLowerCase().trim();
        
        // If search field is empty or contains only "search...", show all records
        if (searchText.isEmpty() || searchText.equals("search...")) {
            displayData();
            return;
        }

        try {
            // Clear existing table data
            tableModel.setRowCount(0);
            
            String searchPattern = "'%" + searchText + "%'";
            String query = "SELECT b.id, s.Name as student_name, bk.Title as book_title, " +
                          "b.borrow_date, b.return_date, b.status " +
                          "FROM borrow b " +
                          "INNER JOIN student s ON b.student_id = s.ID " +
                          "INNER JOIN book bk ON b.book_id = bk.ID " +
                          "WHERE LOWER(s.Name) LIKE " + searchPattern + " OR " +
                          "LOWER(bk.Title) LIKE " + searchPattern + " OR " +
                          "LOWER(b.status) LIKE " + searchPattern + " " +
                          "ORDER BY b.borrow_date DESC";
            
            ResultSet rs = db.getData(query);
            
            while(rs.next()) {
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("student_name"),
                    rs.getString("book_title"),
                    rs.getString("borrow_date"),
                    rs.getString("return_date"),
                    rs.getString("status")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching borrow records: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        floatedPage.addBorrow add = new floatedPage.addBorrow();
        
        dialog.add(add);
        dialog.setSize(325, 340);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setVisible(true);
        
        // Refresh the table after dialog closes
        displayData();
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        int rowIndex = borrowtbl.getSelectedRow();
        
        if(rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a record to delete!");
            return;
        }
        
        javax.swing.table.TableModel tbl = borrowtbl.getModel();
        String borrowId = tbl.getValueAt(rowIndex, 0).toString();
        String status = tbl.getValueAt(rowIndex, 5).toString();
        
        if (!status.equals("Returned")) {
            JOptionPane.showMessageDialog(this, "Cannot delete an active borrow record. Please return the book first.");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete borrow record ID " + borrowId + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Get book ID before deleting the borrow record
                String bookQuery = "SELECT book_id FROM borrow WHERE id = " + Integer.parseInt(borrowId);
                ResultSet rs = db.getData(bookQuery);
                String bookId = null;
                
                if (rs.next()) {
                    bookId = rs.getString("book_id");
                }
                
                // Delete borrow record
                String query = "DELETE FROM borrow WHERE id = " + Integer.parseInt(borrowId);
                int result = db.UpdateData(query);
                
                if (result > 0) {
                    // Update book status to Available
                    if (bookId != null) {
                        String updateBookQuery = "UPDATE book SET Status = 'Available' WHERE ID = " + Integer.parseInt(bookId);
                        int bookResult = db.UpdateData(updateBookQuery);
                        
                        if (bookResult > 0) {
                            JOptionPane.showMessageDialog(this, "Borrow record and book status updated successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Borrow record deleted but failed to update book status.");
                        }
                    }
                    displayData();
                } else {
                    JOptionPane.showMessageDialog(this, "Record not found or deletion failed.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {
        displayData();
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        searchBorrows();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = borrowtbl.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record to print");
            return;
        }

        // Get data from the selected row
        String borrowId = borrowtbl.getValueAt(selectedRow, 0).toString();
        String studentName = borrowtbl.getValueAt(selectedRow, 1).toString();
        String bookTitle = borrowtbl.getValueAt(selectedRow, 2).toString();
        String borrowDate = borrowtbl.getValueAt(selectedRow, 3).toString();
        String returnDate = borrowtbl.getValueAt(selectedRow, 4).toString();

        // Create and show the PrintReceipt form
        floatedPage.PrintReceipt printReceipt = new floatedPage.PrintReceipt(
            borrowId, studentName, bookTitle, borrowDate, returnDate);
        printReceipt.setLocationRelativeTo(this);
        printReceipt.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        borrowtbl = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("Borrow");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 80, -1));

        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });
        jPanel1.add(returnButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 80, -1));

        jButton3.setText("Delete");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 70, -1));

        borrowtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Student", "Book Borrowed", "Borrow Date", "Return Date", "Status"
            }
        ));
        jScrollPane2.setViewportView(borrowtbl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 610, 300));

        refreshButton.setText("Refresh");
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });
        jPanel1.add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, -1, -1));

        searchField.setText("search...");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        jPanel1.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Borrow Records");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        jButton2.setText("Print");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButton2KeyTyped(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        int row = borrowtbl.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record to return");
            return;
        }

        String borrowId = borrowtbl.getValueAt(row, 0).toString();
        String status = borrowtbl.getValueAt(row, 5).toString();

        if (status.equals("Returned")) {
            JOptionPane.showMessageDialog(this, "This book is already returned");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to return this book?", 
            "Confirm Return", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // First get the book ID from the borrow record
                String getBookIdQuery = "SELECT book_id FROM borrow WHERE id = " + Integer.parseInt(borrowId);
                ResultSet rs = db.getData(getBookIdQuery);
                
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Error: Could not find borrow record");
                    return;
                }
                
                String bookId = rs.getString("book_id");
                rs.close();

                // Update borrow record
                String borrowQuery = "UPDATE borrow SET status = 'Returned', return_date = CURDATE() WHERE ID = " + Integer.parseInt(borrowId);
                int borrowResult = db.UpdateData(borrowQuery);

                if (borrowResult > 0) {
                    // Update book status
                    String bookQuery = "UPDATE book SET Status = 'Available' WHERE ID = " + Integer.parseInt(bookId);
                    int bookResult = db.UpdateData(bookQuery);

                    if (bookResult > 0) {
                        JOptionPane.showMessageDialog(this, "Book returned successfully");
                        displayData(); // Refresh the table
                    } else {
                        JOptionPane.showMessageDialog(this, "Error updating book status");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating borrow record");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_returnButtonActionPerformed

    private void jButton2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2KeyTyped

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

    }//GEN-LAST:event_jButton2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable borrowtbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
