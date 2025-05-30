/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floatedPage;

import config.connectDB;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class addBorrows extends javax.swing.JPanel {
        private connectDB db;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    /**
     * Creates new form addBorrows
     */
    public addBorrows() {
        initComponents();
        displayData();
        
        db = new connectDB();
        con = db.getConnection();
        
        // Set current date as borrow date
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        borrowDate.setText(currentDate);
    }
    
   public void displayData() {
        connectDB dbc = new connectDB();
        try {
            // Modify this query to match your column name and value for availability
            ResultSet rs = dbc.getData("SELECT * FROM book WHERE Status = 'Available'");

            DefaultTableModel model = (DefaultTableModel) bookstbl.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new String[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                  
                });
            }
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }
   private void clearFields() {
        studentsid.setText("");
        borrowDate.setText("");
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
        borrowDate = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookstbl = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(322, 460));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add Borrow Record");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 230, 80));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Enter Student ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 130, 19));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Select Book");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 100, 19));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Borrow Date");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 19));

        studentsid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsidActionPerformed(evt);
            }
        });
        jPanel1.add(studentsid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 220, 31));
        jPanel1.add(borrowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 220, 31));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 110, 40));

        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, -1, -1));

        bookstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Book ID", "Title", "Author", "Category"
            }
        ));
        jScrollPane2.setViewportView(bookstbl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 330, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void studentsidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsidActionPerformed

    }//GEN-LAST:event_studentsidActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
    String sid = this.studentsid.getText();
    String bid = config.session.getBookId(); // Use book ID from session
    String bdate = this.borrowDate.getText();

    if (sid.isEmpty() || bid == null || bid.isEmpty() || bdate.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill all fields or select a book");
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

            // ✅ Log action
            int userId = Integer.parseInt(config.session.getUserId());
            db.insertLog(userId, "Student ID " + sid + " borrowed book ID " + bid + " on " + bdate);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      int row = bookstbl.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a book from the table.");
            return;
        }

        javax.swing.table.TableModel model = bookstbl.getModel();
        String selectedBookId = model.getValueAt(row, 0).toString(); // column 0 should be book ID

        // Store selected book ID in session
        config.session.setBookId(selectedBookId);
        JOptionPane.showMessageDialog(this, "Book selected successfully.");

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable bookstbl;
    private javax.swing.JTextField borrowDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField studentsid;
    // End of variables declaration//GEN-END:variables
}
