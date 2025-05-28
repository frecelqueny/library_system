/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;
import Authentication.Registration;
import Authentication.login;
import adminInternalFrame.Logs;
import adminInternalFrame.Profile;
import adminInternalFrame.book;
import adminInternalFrame.borrow;
import adminInternalFrame.student;
import adminInternalFrame.userForm;
import config.connectDB;
import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class dashboard extends javax.swing.JFrame {

    private UpdateUser updateUserWindow = null;
    public dashboard() {
        initComponents();
        
        userForm us = new userForm();
 
        mainDesktop.add(us);
        us.setVisible(true);
        
    }
        Color navcolor = new Color(51,51,51);
        Color headcolor = new Color(0,0,0);
        Color bodycolor = new Color(240,240,240);
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        user_button = new javax.swing.JPanel();
        user_label = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        settingsButton = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        settingsButton1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        settingsButton2 = new javax.swing.JLabel();
        mainDesktop = new javax.swing.JDesktopPane();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Admin ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 60, 20));

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("LogOut");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 90, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/HI.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 130, 110));

        jPanel3.setBackground(new java.awt.Color(204, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 0, -1, 97));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Book");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 80, 30));

        jPanel4.setBackground(new java.awt.Color(204, 153, 255));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(204, 153, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Borrow");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 80, 30));

        user_button.setBackground(new java.awt.Color(204, 153, 255));
        user_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                user_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_buttonMouseExited(evt);
            }
        });
        user_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_label.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        user_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user_label.setText("User");
        user_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_labelMouseClicked(evt);
            }
        });
        user_button.add(user_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel2.add(user_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 80, 30));

        jPanel6.setBackground(new java.awt.Color(204, 153, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(204, 153, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Student");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 80, 30));

        jPanel7.setBackground(new java.awt.Color(204, 153, 255));
        jPanel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settingsButton.setBackground(new java.awt.Color(204, 153, 255));
        settingsButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        settingsButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsButton.setText("Settings");
        settingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsButtonMouseClicked(evt);
            }
        });
        jPanel7.add(settingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 80, -1));

        jPanel8.setBackground(new java.awt.Color(204, 153, 255));
        jPanel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settingsButton1.setBackground(new java.awt.Color(204, 153, 255));
        settingsButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        settingsButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsButton1.setText("Logs");
        settingsButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        settingsButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsButton1MouseClicked(evt);
            }
        });
        jPanel8.add(settingsButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 80, -1));

        jPanel9.setBackground(new java.awt.Color(204, 153, 255));
        jPanel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settingsButton2.setBackground(new java.awt.Color(204, 153, 255));
        settingsButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        settingsButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingsButton2.setText("Profile");
        settingsButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        settingsButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsButton2MouseClicked(evt);
            }
        });
        jPanel9.add(settingsButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 80, -1));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 120, 510);

        mainDesktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainDesktopMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainDesktopLayout = new javax.swing.GroupLayout(mainDesktop);
        mainDesktop.setLayout(mainDesktopLayout);
        mainDesktopLayout.setHorizontalGroup(
            mainDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        mainDesktopLayout.setVerticalGroup(
            mainDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jPanel1.add(mainDesktop);
        mainDesktop.setBounds(120, 0, 680, 510);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void mainDesktopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainDesktopMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mainDesktopMouseClicked

    private void user_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_buttonMouseExited
        user_label.setBackground(navcolor);
    }//GEN-LAST:event_user_buttonMouseExited

    private void user_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_buttonMouseEntered
        user_label.setBackground(bodycolor);
    }//GEN-LAST:event_user_buttonMouseEntered

    private void user_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_buttonMouseClicked
//        user us = new user(); // All users
//
//        mainDesktop.add(us).setVisible(true);
//        add.setVisible(true);
//        edit.setVisible(true);
//        delete.setVisible(true);
    }//GEN-LAST:event_user_buttonMouseClicked

    private void user_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_labelMouseClicked
     userForm us = new userForm();
 
    mainDesktop.add(us);
    us.setVisible(true);
    }//GEN-LAST:event_user_labelMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        student st = new student();
 
    mainDesktop.add(st);
    st.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void settingsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseClicked
        // TODO add your handling code here:
        mainDesktop.add(new setting()).setVisible(true);
    }//GEN-LAST:event_settingsButtonMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        mainDesktop.add(new book()).setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        mainDesktop.add(new borrow()).setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void settingsButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButton1MouseClicked
        mainDesktop.add(new Logs()).setVisible(true);
    }//GEN-LAST:event_settingsButton1MouseClicked

    private void settingsButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButton2MouseClicked
        Profile pf = new Profile();
        mainDesktop.add(pf).setVisible(true);
    }//GEN-LAST:event_settingsButton2MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        Profile pf = new Profile();
        mainDesktop.add(pf).setVisible(true);
    }//GEN-LAST:event_jPanel9MouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JDesktopPane mainDesktop;
    private javax.swing.JLabel settingsButton;
    private javax.swing.JLabel settingsButton1;
    private javax.swing.JLabel settingsButton2;
    private javax.swing.JPanel user_button;
    private javax.swing.JLabel user_label;
    // End of variables declaration//GEN-END:variables
}
