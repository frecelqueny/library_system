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
import config.connectDB;
import floatedPage.addBook;
import floatedPage.editBook;

/**
 *
 * @author admin
 */
public class book extends javax.swing.JInternalFrame {
    private connectDB db;
    private javax.swing.table.DefaultTableModel tableModel;

    /**
     * Creates new form book
     */
    public book() {
        initComponents();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        // Initialize database connection
        db = new connectDB();
        
        // Initialize table model
        tableModel = (javax.swing.table.DefaultTableModel) bookstbl.getModel();
        
        // Add key listener to search field
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBooks();
            }
        });
        
        // Load initial data
        displayData();
    }
    
    public void displayData() {
        try {
            // Clear existing table data
            tableModel.setRowCount(0);
            
            String query = "SELECT * FROM book ORDER BY Title";
            ResultSet rs = db.getData(query);
            
            while(rs.next()) {
                Object[] row = {
                    rs.getString("ID"),
                    rs.getString("Title"),
                    rs.getString("author"),
                    rs.getString("Category"),
                    rs.getString("Status")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBooks() {
        String searchText = searchField.getText().toLowerCase().trim();
        
        // If search field is empty or contains only "search...", show all books
        if (searchText.isEmpty() || searchText.equals("search...")) {
            displayData();
            return;
        }

        try {
            // Clear existing table data
            tableModel.setRowCount(0);
            
            String searchPattern = "'%" + searchText + "%'";
            String query = "SELECT * FROM book WHERE " +
                          "LOWER(Title) LIKE " + searchPattern + " OR " +
                          "LOWER(author) LIKE " + searchPattern + " OR " +
                          "LOWER(Category) LIKE " + searchPattern + " " +
                          "ORDER BY Title";
            
            ResultSet rs = db.getData(query);
            
            while(rs.next()) {
                Object[] row = {
                    rs.getString("ID"),
                    rs.getString("Title"),
                    rs.getString("author"),
                    rs.getString("Category"),
                    rs.getString("Status")
                };
                tableModel.addRow(row);
            }
            
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching books: " + ex.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int rowIndex = bookstbl.getSelectedRow();
        
        if(rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a book to edit!");
            return;
        }
        
        try {
            javax.swing.table.TableModel tbl = bookstbl.getModel();
            // Get data directly from the table
            String id = tbl.getValueAt(rowIndex, 0).toString();
            String bookTitle = tbl.getValueAt(rowIndex, 1).toString();
            String author = tbl.getValueAt(rowIndex, 2).toString();
            String category = tbl.getValueAt(rowIndex, 3).toString();
            String status = tbl.getValueAt(rowIndex, 4).toString();
            
            // Create and show edit dialog
            javax.swing.JDialog dialog = new javax.swing.JDialog();
            editBook edit = new editBook();
            
            // Set the book data to the form fields
            edit.setData(id, bookTitle, author, category, status);
            
            dialog.add(edit);
            dialog.setSize(325, 500);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
            
            // Refresh the table after dialog closes
            displayData();
            
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        int rowIndex = bookstbl.getSelectedRow();
        
        if(rowIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select a book to delete!");
            return;
        }
        
        javax.swing.table.TableModel tbl = bookstbl.getModel();
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete book ID " + tbl.getValueAt(rowIndex, 0) + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM book WHERE ID = '" + tbl.getValueAt(rowIndex, 0) + "'";
            int result = db.UpdateData(query);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                displayData();
            } else {
                JOptionPane.showMessageDialog(this, "Book not found or deletion failed.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {
        displayData();
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        searchBooks();
    }

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        addBook add = new addBook();
        
        dialog.add(add);
        dialog.setSize(325, 450);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setVisible(true);
        
        // Refresh the table after dialog closes
        displayData();
    }

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {
        int rowIndex = bookstbl.getSelectedRow();
        
        if(rowIndex < 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "Please select a book to edit!");
            return;
        }
        
        try {
            javax.swing.table.TableModel tbl = bookstbl.getModel();
            // Get data directly from the table
            String id = tbl.getValueAt(rowIndex, 0).toString();
            String title = tbl.getValueAt(rowIndex, 1).toString();
            String author = tbl.getValueAt(rowIndex, 2).toString();
            String category = tbl.getValueAt(rowIndex, 3).toString();
            String status = tbl.getValueAt(rowIndex, 4).toString();
            
            // Create and show edit dialog
            javax.swing.JDialog dialog = new javax.swing.JDialog();
            editBook edit = new editBook();
            
            // Set the book data to the form fields
            edit.setData(id, title, author, category, status);
            
            dialog.add(edit);
            dialog.setSize(325, 450);
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

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {
        int rowIndex = bookstbl.getSelectedRow();
        
        if(rowIndex < 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "Please select a book to delete!");
            return;
        }
        
        javax.swing.table.TableModel tbl = bookstbl.getModel();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete book ID " + tbl.getValueAt(rowIndex, 0) + "?", 
            "Confirm Delete", 
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            String query = "DELETE FROM book WHERE ID = '" + tbl.getValueAt(rowIndex, 0) + "'";
            int result = db.UpdateData(query);
            
            if (result > 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                displayData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Book not found or deletion failed.", 
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        displayData();
    }

    private void searchFieldMouseClicked(java.awt.event.MouseEvent evt) {
        if (searchField.getText().equals("search...")) {
            searchField.setText("");
        }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        bookstbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Book ID", "Title", "Author", "Category", "Status"
            }
        ));
        jScrollPane2.setViewportView(bookstbl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 610, 300));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Books");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(refreshButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, -1, -1));

        searchField.setText("search...");
        searchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldMouseClicked(evt);
            }
        });
        jPanel1.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 190, -1));

        addButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addButton.setText("Add");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });
        jPanel1.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 70, -1));

        editButton.setText("Edit");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
        });
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        jPanel1.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 70, -1));

        deleteButton.setText("Delete");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });
        jPanel1.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 70, -1));

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

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable bookstbl;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
