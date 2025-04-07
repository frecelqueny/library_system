package adminInternalFrame;

import config.connectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
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
            users_tbl.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void displayUserById(String userId) {
        try {
            connectDB dbc = new connectDB();
            ResultSet rs = dbc.getData("SELECT * FROM users WHERE user_id = '" + userId + "'");
            users_tbl.setModel(DbUtils.resultSetToTableModel(rs));
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
        users_tbl = new javax.swing.JTable();

        user.setBackground(new java.awt.Color(204, 153, 255));
        user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        users_tbl.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(users_tbl);

        jScrollPane2.setViewportView(jScrollPane1);

        user.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 610, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel user;
    private javax.swing.JTable users_tbl;
    // End of variables declaration//GEN-END:variables
}
