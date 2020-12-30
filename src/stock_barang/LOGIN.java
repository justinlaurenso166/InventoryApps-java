/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock_barang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author cent91
 */
public class LOGIN extends javax.swing.JFrame {
    public static Connection conn;
    public static Statement st;
    /**
     * Creates new form LOGIN
     */
    public LOGIN() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setBackground(new Color (0,0,0,0));
        jPanel1.setBackground(new Color (0,0,0,0));
        txusername.setBackground(new Color (0,0,0,0));
        txpass.setBackground(new Color (0,0,0,0));
        hide.setVisible(false);
        koneksi();
    }
    
    void koneksi(){
         try {
            // timezone 
            String timezone = "useLegacyDatetimeCode=false&serverTimezone=UTC";
           
            //untuk mengkoneksikan dengan database penjualan
            String url ="jdbc:mysql://localhost/uas?" + timezone;
            String user="root";
            String pass="";
            
            //membuat koneksi dengan database
            conn = DriverManager.getConnection(url,user,pass);
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" + e.getMessage());
        }
    }
    
    public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
    
    void login(){
         try{
            String username = txusername.getText();
            String password = txpass.getText();
            String encryptPass = md5(password);
            
            String sql = "select * from user where username='"+username.trim()+"' and password='"+encryptPass.trim()+"'";
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                String namauser = rs.getString("name");
                String status = rs.getString("position");
                String pwd = rs.getString("password");
                String user = rs.getString("username");
                Session_Login.set_username(user);
                Session_Login.set_namauser(namauser);   
                Session_Login.set_password(pwd);
                Session_Login.set_status(status);
                
                //System.out.println(namauser + status);
                
                if(status.equals("ADMIN") || status.equals("Admin")){
                    JOptionPane.showMessageDialog(this,"Selamat datang, " + namauser, "Welcome", JOptionPane.INFORMATION_MESSAGE);
                       
                    this.dispose();
                    DASBOARD fDashboard = new DASBOARD();
                    fDashboard.setLocationRelativeTo(null);
                    fDashboard.setVisible(true);
                    
                }
                else if(status.equals("USER") || status.equals("User")){
                    JOptionPane.showMessageDialog(this,"Selamat datang, " + namauser, "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    
                    this.dispose();
                    DASBOARD fDashboard = new DASBOARD();
                    fDashboard.setLocationRelativeTo(null);
                    fDashboard.setVisible(true);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "User TIdak ada", "Informasi", JOptionPane.ERROR_MESSAGE);
                txusername.setText("");
                txpass.setText("");
                txusername.grabFocus();
            }
        }
        catch(SQLException e)
        {
            System.out.println("Koneksi gagal " + e.toString());
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
        exit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txusername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txpass = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        btnlogin = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        exit.setForeground(new java.awt.Color(204, 0, 0));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 30, -1));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USER LOGIN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock_barang/images/icons8_password_25px_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 50, 50));

        txusername.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txusername.setForeground(new java.awt.Color(255, 255, 255));
        txusername.setText("USERNAME");
        txusername.setBorder(null);
        txusername.setCaretColor(new java.awt.Color(255, 255, 255));
        txusername.setOpaque(false);
        txusername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txusernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txusernameFocusLost(evt);
            }
        });
        txusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txusernameMousePressed(evt);
            }
        });
        txusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txusernameActionPerformed(evt);
            }
        });
        txusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txusernameKeyPressed(evt);
            }
        });
        jPanel1.add(txusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 210, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock_barang/images/icons8_user_25px.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 50, 50));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 200, 20));

        txpass.setForeground(new java.awt.Color(255, 255, 255));
        txpass.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txpass.setText("ENTERPASSWORD");
        txpass.setBorder(null);
        txpass.setCaretColor(new java.awt.Color(255, 255, 255));
        txpass.setOpaque(false);
        txpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txpassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txpassFocusLost(evt);
            }
        });
        txpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpassActionPerformed(evt);
            }
        });
        txpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txpassKeyPressed(evt);
            }
        });
        jPanel1.add(txpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 160, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 200, 10));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("LOGIN");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 100, 20));

        btnlogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock_barang/images/btn-login.png"))); // NOI18N
        btnlogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnloginMouseClicked(evt);
            }
        });
        jPanel1.add(btnlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 180, 40));

        show.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock_barang/images/icons8_show_password_15px.png"))); // NOI18N
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showMousePressed(evt);
            }
        });
        jPanel1.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 40, 30));

        hide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock_barang/images/icons8_hide_15px.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        jPanel1.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 40, 30));

        BG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock_barang/images/Login_Bg.png"))); // NOI18N
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void txusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txusernameActionPerformed

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        System.exit(0);
    }//GEN-LAST:event_exitMousePressed

    private void txpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpassActionPerformed

    private void showMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMousePressed
            txpass.setEchoChar((char)0);
            show.setVisible(false);
            hide.setVisible(true);
    }//GEN-LAST:event_showMousePressed

    private void hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMousePressed
            txpass.setEchoChar('\u25cf');
            hide.setVisible(false);
            show.setVisible(true);
    }//GEN-LAST:event_hideMousePressed

    private void txusernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txusernameFocusGained
        //txusername.setText("");
    }//GEN-LAST:event_txusernameFocusGained

    private void txusernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txusernameMousePressed
        txusername.setText("");
    }//GEN-LAST:event_txusernameMousePressed

    private void txpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txpassFocusGained
        txpass.setText("");
    }//GEN-LAST:event_txpassFocusGained

    private void txusernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txusernameFocusLost

    }//GEN-LAST:event_txusernameFocusLost

    private void txpassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txpassFocusLost

    }//GEN-LAST:event_txpassFocusLost

    private void btnloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnloginMouseClicked
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnloginMouseClicked

    private void txpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpassKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_txpassKeyPressed

    private void txusernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txusernameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            txpass.grabFocus();
        }
    }//GEN-LAST:event_txusernameKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOGIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JLabel btnlogin;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel hide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel show;
    private javax.swing.JPasswordField txpass;
    private javax.swing.JTextField txusername;
    // End of variables declaration//GEN-END:variables
}