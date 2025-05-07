package adminInternalFrame;

import Authentication.Registration;
import config.connectDB;
import floatedPage.addUser;
import floatedPage.updateUser;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class user extends javax.swing.JInternalFrame {

    public user() {
        initComponents();
        displayAllUsers();
        setupUI();
    }

    public user(String userId) {
        initComponents();
        displayUserById(userId);
        setupUI();
    }

    private void displayAllUsers() {
        try {
            connectDB dbc = new connectDB();
            ResultSet rs = dbc.getData("SELECT * FROM users");
            user_tbls.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void displayUserById(String userId) {
        try {
            connectDB dbc = new connectDB();
            ResultSet rs = dbc.getData("SELECT * FROM users WHERE user_id = '" + userId + "'");
            user_tbls.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void setupUI() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        user = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        user_tbls = new javax.swing.JTable();
        add = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        user.setBackground(new java.awt.Color(204, 153, 255));
        user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_tbls.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(user_tbls);

        jScrollPane2.setViewportView(jScrollPane1);

        user.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 610, 300));

        add.setText("ADD");
        add.setMaximumSize(new java.awt.Dimension(69, 23));
        add.setMinimumSize(new java.awt.Dimension(69, 23));
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        user.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        edit.setText("EDIT");
        edit.setMaximumSize(new java.awt.Dimension(69, 23));
        edit.setMinimumSize(new java.awt.Dimension(69, 23));
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        user.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, -1, -1));

        delete.setText("DELETE");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        user.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, -1, -1));

        jTextField1.setText("search...");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        user.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 190, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        Registration reg = new Registration(true); // Indicate from dashboard
        reg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_addMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
         JDialog dialog = new JDialog(); // Create a floating window
        addUser newPanel = new addUser();

        dialog.add(newPanel); // Add add_user to the dialog
        dialog.setSize(325, 500); // Set the size of the window
        dialog.setLocationRelativeTo(null); // Center the window
        dialog.setModal(true); // Prevent interactions with the main window until closed
        dialog.setVisible(true); // Show the floating add_user
    
    }//GEN-LAST:event_addActionPerformed

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        if (updateUserWindow != null && updateUserWindow.isShowing()) {
         updateUserWindow.thaoFront(); // Bring the existing window to front
            return;
        }

       String userId = JOptionPane.showInputDialog(this, "Enter the User ID to edit:");
//        if (userId != null && !userId.isEmpty()) {
//            connectDB con = new connectDB();
//            try {
//                ResultSet rs = con.getData("SELECT user_id, first_name, last_name, username, email, password FROM users WHERE user_id = " + userId);
//                if (rs.next()) {
//                    updateUserWindow = new UpdateUser();
//                    updateUserWindow.setUserData(
//                        rs.getString("user_id"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("username"),
//                        rs.getString("email"),
//                        rs.getString("password")
//                    );
//                    updateUserWindow.setVisible(true);
//
//                    // Reset reference when window closes
//                    updateUserWindow.addWindowListener(new java.awt.event.WindowAdapter() {
//                        @Override
//                        public void windowClosed(java.awt.event.WindowEvent e) {
//                            updateUserWindow = null;
//                        }
//
//                        @Override
//                        public void windowClosing(java.awt.event.WindowEvent e) {
//                            updateUserWindow = null;
//                        }
//                    });
//
//                } else {
//                    JOptionPane.showMessageDialog(this, "User ID not found!");
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
//            }
//        }
    }//GEN-LAST:event_editMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        int rowIndex = user_tbls.getSelectedRow();
        
        if(rowIndex < 0){
            JOptionPane.showMessageDialog(null, " Please select an Item!!! ");
        }else{
            try{
                connectDB dbc = new connectDB();
                TableModel tbl = user_tbls.getModel();
                ResultSet rs = dbc.getData("SELECT * FROM user WHERE user_id = '" + tbl.getValueAt(rowIndex, 0) + "'");
                if(rs.next()){
                    JDialog dialog = new JDialog(); // Create a floating window
                    updateUser newPanel = new updateUser();
                    
                    newPanel.id.setText(""+rs.getString("id"));
                    newPanel.fn.setText(""+rs.getString("first_name"));
                    newPanel.ln.setText(""+rs.getString("last_name"));
                    newPanel.un.setText(""+rs.getString("username"));
                    newPanel.em.setText(""+rs.getString("email"));
                    newPanel.rl.setSelectedItem(""+rs.getString("role"));
                    newPanel.status.setSelectedItem(""+rs.getString("status"));
                    
                    Font arialFont = new Font("Arial", Font.PLAIN, 12);

                    newPanel.fn.setFont(arialFont);
                    newPanel.fn.setForeground(Color.BLACK);

                    newPanel.ln.setFont(arialFont);
                    newPanel.ln.setForeground(Color.BLACK);

                    newPanel.un.setFont(arialFont);
                    newPanel.un.setForeground(Color.BLACK);

                    newPanel.em.setFont(arialFont);
                    newPanel.em.setForeground(Color.BLACK);
                    
                    dialog.add(newPanel); // Add add_user to the dialog
                    dialog.setSize(325, 500); // Set the size of the window
                    dialog.setLocationRelativeTo(null); // Center the window
                    dialog.setModal(true); // Prevent interactions with the main window until closed
                    dialog.setVisible(true); // Show the floating add_user
                    
                    
                }
            }catch(SQLException ex){
                System.out.println(""+ex);
            }    
        }
    }//GEN-LAST:event_editActionPerformed

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        String userId = JOptionPane.showInputDialog(this, "Enter the User ID to delete:");

        if (userId != null && !userId.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete user ID " + userId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                connectDB con = new connectDB();
                int result = con.UpdateData("DELETE FROM users WHERE user_id = " + userId);

                if (result == 1) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "User ID not found or deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid User ID.");
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel user;
    private javax.swing.JTable user_tbls;
    // End of variables declaration//GEN-END:variables

