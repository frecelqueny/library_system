
package library;

import config.connectDB;
import config.session;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class setting extends javax.swing.JInternalFrame {
     private String originalAnswer = "";
    private boolean hasChanges = false;
    private connectDB db;
    public setting() {
        initComponents();
        
         db = new connectDB();
        
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);

        // Add change listener to track modifications
        securityAnswerField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { checkChanges(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { checkChanges(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { checkChanges(); }
        });

        // Load existing security settings
        loadSecuritySettings();
    }
    
     private void loadSecuritySettings() {
        try {
            String userId = session.getUserId();
            if (userId != null) {
                ResultSet rs = db.getData("SELECT security_question, security_answer FROM users WHERE user_id = '" + userId + "'");
                if (rs.next()) {
                    String question = rs.getString("security_question");
                    String answer = rs.getString("security_answer");
                    
                    if (question != null && !question.isEmpty()) {
                        // Find and select the matching question in the combo box
                        for (int i = 0; i < securityQuestionComboBox.getItemCount(); i++) {
                            if (securityQuestionComboBox.getItemAt(i).equals(question)) {
                                securityQuestionComboBox.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    
                    if (answer != null && !answer.isEmpty()) {
                        securityAnswerField.setText(answer);
                        originalAnswer = answer;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading security settings: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkChanges() {
        String currentAnswer = securityAnswerField.getText();
        hasChanges = !currentAnswer.equals(originalAnswer);
        applyButton.setEnabled(hasChanges);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        securityQuestionComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        securityAnswerField = new javax.swing.JTextField();
        applyButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(680, 510));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 510));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Settings");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel2.setText("Answer:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 70, 30));

        securityQuestionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What was the name of your first pet?", "What is the name of the street you grew up on?", "What was the model of your first car?", "What is your mother’s maiden name?", "What was the name of your elementary school?" }));
        securityQuestionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                securityQuestionComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(securityQuestionComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 250, -1));

        jLabel3.setText("Security question:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));
        jPanel1.add(securityAnswerField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 210, 30));

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        jPanel1.add(applyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void securityQuestionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_securityQuestionComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_securityQuestionComboBoxActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        if (hasChanges) {
            String userId = session.getUserId();
            if (userId == null) {
                JOptionPane.showMessageDialog(this, "User session not found. Please log in again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String question = (String) securityQuestionComboBox.getSelectedItem();
            String answer = securityAnswerField.getText();

            if (answer.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Security answer cannot be empty!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "UPDATE users SET security_question = '" + question + "', " +
            "security_answer = '" + answer + "' " +
            "WHERE user_id = '" + userId + "'";

            int result = db.UpdateData(query);
            if (result == 1) {
                originalAnswer = answer;
                hasChanges = false;
                applyButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Security settings updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update security settings. Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_applyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField securityAnswerField;
    private javax.swing.JComboBox<String> securityQuestionComboBox;
    // End of variables declaration//GEN-END:variables
}
