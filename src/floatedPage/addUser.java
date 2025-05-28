package floatedPage;

import Authentication.login;
import config.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import library.HashUtil;

public class addUser extends javax.swing.JPanel {

    public addUser() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        fn = new javax.swing.JTextField();
        ln = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        pw = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rl = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(322, 460));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("First Name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 127, 19));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Last Name");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 127, 19));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("UserName");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 127, 19));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Role");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 127, 19));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Email");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 127, 19));

        save.setText("SAVE");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 405, 86, 31));

        cancel.setText("CANCEL");
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 405, 86, 31));
        jPanel1.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 31));
        jPanel1.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 130, 31));
        jPanel1.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 280, 31));
        jPanel1.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 280, 31));
        jPanel1.add(pw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 280, 31));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Add User");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 230, 80));

        rl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jPanel1.add(rl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Password");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 127, 19));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        // Check if any required fields are empty
        if (fn.getText().isEmpty() 
            || ln.getText().isEmpty()
            || un.getText().isEmpty()
            || em.getText().isEmpty()
            || pw.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate email format using regex
        String email = em.getText();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate password format (min 8 chars, 1 upper, 1 lower, 1 digit, and 1 special char)
        String password = pw.getText();
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        if (!password.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(null, 
                "Password must be at least 8 characters and include:\n- One uppercase letter\n- One lowercase letter\n- One number\n- One special character (@#$%^&+=!)", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hash the password before inserting it into the database
        String hashedPassword = HashUtil.sha256(password);
        if (hashedPassword == null) {
            JOptionPane.showMessageDialog(null, "Error hashing password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Connect to the database and insert the data
        connectDB con = new connectDB();
        String insertQuery = "INSERT INTO users (first_name, last_name, username, email, password, role) "
                             + "VALUES(?, ?, ?, ?, ?, ?)"; // Using placeholders for PreparedStatement

        try (Connection conn = con.getConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

           stmt.setString(1, fn.getText());
           stmt.setString(2, ln.getText());
           stmt.setString(3, un.getText());
           stmt.setString(4, em.getText());
           stmt.setString(5, hashedPassword);
           stmt.setString(6, rl.getSelectedItem().toString());

           int result = stmt.executeUpdate();

           if (result == 1) {
               // Get the generated user_id for the new user
               ResultSet generatedKeys = stmt.getGeneratedKeys();
               int newUserId = 0;
               if (generatedKeys.next()) {
                   newUserId = generatedKeys.getInt(1);
               }

               JOptionPane.showMessageDialog(null, "Inserted Successfully!");

               // Log user registration
               con.insertLog(newUserId, "New user registered: " + un.getText());

               // Close the registration form
               JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor(this);
               if (parentDialog != null) {
                   parentDialog.dispose();
               }
           } else {
               JOptionPane.showMessageDialog(null, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

               // Log failed registration attempt (userId unknown, use 0)
               con.insertLog(0, "Failed registration attempt for username: " + un.getText());
           }
       } catch (SQLException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Database error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

           // Log database error during registration
           con.insertLog(0, "Database error during registration for username: " + un.getText() + " - " + e.getMessage());
       }
    }//GEN-LAST:event_saveMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
 
    }//GEN-LAST:event_cancelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JTextField em;
    private javax.swing.JTextField fn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField ln;
    private javax.swing.JTextField pw;
    private javax.swing.JComboBox<String> rl;
    private javax.swing.JButton save;
    private javax.swing.JTextField un;
    // End of variables declaration//GEN-END:variables
}
