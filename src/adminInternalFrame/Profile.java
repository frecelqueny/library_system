
package adminInternalFrame;

import config.connectDB;
import config.session;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Profile extends javax.swing.JInternalFrame {
    
    public Profile() {
        initComponents();
        //remove border
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
    }
    
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
    
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
    
    public ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage;

        if (ImagePath != null) {
            MyImage = new ImageIcon(ImagePath);
        } else {
            MyImage = new ImageIcon(pic);
        }

        Image img = MyImage.getImage();

        int width = label.getWidth() > 0 ? label.getWidth() : 100; // fallback width
        int originalWidth = MyImage.getIconWidth();
        int originalHeight = MyImage.getIconHeight();

        int newHeight = (int) ((double) width / originalWidth * originalHeight);

        Image newImg = img.getScaledInstance(width, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }


        private void loadProfilePicture(String userId) {
            try (Connection con = new connectDB().getConnection();
                 PreparedStatement pst = con.prepareStatement("SELECT profile_pic FROM users WHERE user_id = ?")) {

                pst.setString(1, userId);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String fileName = rs.getString("profile_pic");
                        if (fileName != null && !fileName.isEmpty()) {
                            // Construct full image path
                            String imagePath = "src/userImages/" + fileName;

                            // Resize and set the image using your method
                            ImageIcon resizedIcon = ResizeImage(imagePath, null, image);
                            image.setIcon(resizedIcon);
                        } else {
                            image.setIcon(null); // or default
                        }
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading profile picture: " + e.getMessage());
            }
        }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        user = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        un = new javax.swing.JLabel();
        fn = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ln = new javax.swing.JTextField();
        fnn = new javax.swing.JTextField();
        unn = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        user.setBackground(new java.awt.Color(204, 153, 255));
        user.setPreferredSize(new java.awt.Dimension(680, 510));
        user.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Profile");
        user.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMouseClicked(evt);
            }
        });
        jPanel3.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 130, 110));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 150, 130));

        un.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        un.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        un.setText("Username");
        jPanel1.add(un, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 190, 240, -1));

        fn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fn.setText("name");
        jPanel1.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 250, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Admin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 250, -1));

        jButton1.setText("Upload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        user.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 250, 390));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 270, 40));
        jPanel2.add(fnn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 270, 40));

        unn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unnActionPerformed(evt);
            }
        });
        jPanel2.add(unn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 270, 40));

        em.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emActionPerformed(evt);
            }
        });
        jPanel2.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 270, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Username");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("First Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Last Name");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Email");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        user.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 330, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(null, "No file selected. Please choose a file first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Do you want to change your profile picture?",
            "Change Profile Picture",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            String fileName = selectedFile.getName();
            File destFile = new File("src/userImages/" + fileName);

            // Get old profile picture filename from database
            connectDB cn = new connectDB();
            Connection con = cn.getConnection();

            String selectSql = "SELECT profile_pic FROM users WHERE user_id = ?";
            PreparedStatement selectPst = con.prepareStatement(selectSql);
            selectPst.setString(1, session.getUserId());

            String oldFileName = null;
            try (ResultSet rs = selectPst.executeQuery()) {
                if (rs.next()) {
                    oldFileName = rs.getString("profile_pic");
                }
            }
            selectPst.close();

            // Delete old file if it exists and is different from new file
            if (oldFileName != null && !oldFileName.isEmpty() && !oldFileName.equals(fileName)) {
                File oldFile = new File("src/userImages/" + oldFileName);
                if (oldFile.exists()) {
                    boolean deleted = oldFile.delete();
                    if (!deleted) {
                        System.out.println("Warning: Could not delete old profile picture: " + oldFileName);
                    }
                }
            }

            // Copy new file, overwriting if it exists
            Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Update database with new filename
            String updateSql = "UPDATE users SET profile_pic = ? WHERE user_id = ?";
            PreparedStatement updatePst = con.prepareStatement(updateSql);
            updatePst.setString(1, fileName);
            updatePst.setString(2, session.getUserId());

            int rows = updatePst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Profile picture updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update profile picture.");
            }

            updatePst.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error uploading image: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        
        fn.setText(""+session.getFirstName() +session.getLastName());
        un.setText(""+session.getUsername());
        fnn.setText(""+session.getFirstName());
        ln.setText(""+session.getLastName());
        em.setText(""+session.getEmail());
        unn.setText(""+session.getUsername());
        
        loadProfilePicture(session.getUserId());
    }//GEN-LAST:event_formInternalFrameActivated

    private void unnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unnActionPerformed

    private void emActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emActionPerformed

    private void imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/userImages/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();


                if(FileExistenceChecker(path) == 1){
                  JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path="";
                }else{
                    image.setIcon(ResizeImage(path, null, image));
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_imageMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField em;
    private javax.swing.JLabel fn;
    private javax.swing.JTextField fnn;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField ln;
    private javax.swing.JLabel un;
    private javax.swing.JTextField unn;
    private javax.swing.JPanel user;
    // End of variables declaration//GEN-END:variables
}
