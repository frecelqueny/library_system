/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floatedPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import config.connectDB;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author markjay
 */
public class addBorrow extends javax.swing.JPanel {
    private connectDB db;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form addBorrow
     */
    public addBorrow() {
        initComponents();
        db = new connectDB();
        con = db.getConnection();
        
        // Set current date as borrow date
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        borrowDate.setText(currentDate);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        studentsid = new javax.swing.JTextField();
        bookId = new javax.swing.JTextField();
        borrowDate = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(322, 460));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Borrow Record");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 230, 80));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Student ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 19));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Book ID");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 80, 19));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Borrow Date");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 19));

        studentsid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsidActionPerformed(evt);
            }
        });
        jPanel1.add(studentsid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 280, 31));
        jPanel1.add(bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 280, 31));
        jPanel1.add(borrowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 280, 31));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        try {
            String sid = this.studentsid.getText();
            String bid = this.bookId.getText();
            String bdate = this.borrowDate.getText();

            if (sid.isEmpty() || bid.isEmpty() || bdate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }

            // Check if student exists
            String studentQuery = "SELECT * FROM student WHERE ID = " + Integer.parseInt(sid);
            ResultSet studentRs = db.getData(studentQuery);
            if (!studentRs.next()) {
                JOptionPane.showMessageDialog(this, "Student not found");
                return;
            }

            // Check if book exists and is available
            String bookQuery = "SELECT * FROM book WHERE ID = " + Integer.parseInt(bid) + " AND Status = 'Available'";
            ResultSet bookRs = db.getData(bookQuery);
            if (!bookRs.next()) {   
                JOptionPane.showMessageDialog(this, "Book not found or not available");
                return;
            }

            String borrowQuery = "INSERT INTO borrow (student_id, book_id, borrow_date, status) " +
                     "VALUES (" + Integer.parseInt(sid) + ", " + Integer.parseInt(bid) + ", '" + bdate + "', 'Borrowed')";

            
            int borrowResult = db.UpdateData(borrowQuery);
            
            if (borrowResult > 0) {
                // Update book status
                String updateBookQuery = "UPDATE book SET Status = 'Borrowed' WHERE ID = " + Integer.parseInt(bid);
                int bookResult = db.UpdateData(updateBookQuery);
                
                if (bookResult > 0) {
                    JOptionPane.showMessageDialog(this, "Book borrowed successfully");
                    clearFields();
                    // Close the dialog
                    Window window = (Window) this.getTopLevelAncestor();
                    window.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating book status");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error adding borrow record");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void studentsidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsidActionPerformed
        
    }//GEN-LAST:event_studentsidActionPerformed

    private void borrowButtonMouseClicked(java.awt.event.MouseEvent evt) {
//        try {
//            String sid = this.studentId.getText();
//            String bid = this.bookId.getText();
//            String bdate = this.borrowDate.getText();
//
//            if (sid.isEmpty() || bid.isEmpty() || bdate.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please fill all fields");
//                return;
//            }
//
//            // Check if student exists
//            String studentQuery = "SELECT * FROM student WHERE ID = '" + sid + "'";
//            ResultSet studentRs = db.getData(studentQuery);
//            if (!studentRs.next()) {
//                JOptionPane.showMessageDialog(this, "Student not found");
//                return;
//            }
//
//            // Check if book exists and is available
//            String bookQuery = "SELECT * FROM book WHERE ID = '" + bid + "' AND Status = 'Available'";
//            ResultSet bookRs = db.getData(bookQuery);
//            if (!bookRs.next()) {
//                JOptionPane.showMessageDialog(this, "Book not found or not available");
//                return;
//            }
//
//            // Add borrow record
//            String borrowQuery = "INSERT INTO borrow (student_id, book_id, borrow_date, return_date, status) " +
//                               "VALUES ('" + sid + "', '" + bid + "', '" + bdate + "', 'Borrowed')";
//            
//            int borrowResult = db.UpdateData(borrowQuery);
//            
//            if (borrowResult > 0) {
//                // Update book status
//                String updateBookQuery = "UPDATE book SET Status = 'Borrowed' WHERE ID = '" + bid + "'";
//                int bookResult = db.UpdateData(updateBookQuery);
//                
//                if (bookResult > 0) {
//                    JOptionPane.showMessageDialog(this, "Book borrowed successfully");
//                    clearFields();
//                    // Close the dialog
//                    Window window = (Window) this.getTopLevelAncestor();
//                    window.dispose();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Error updating book status");
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "Error adding borrow record");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }

    private void clearFields() {
        studentsid.setText("");
        bookId.setText("");
        borrowDate.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField bookId;
    private javax.swing.JTextField borrowDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField studentsid;
    // End of variables declaration//GEN-END:variables
}
