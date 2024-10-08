/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.pbo;

/**
 *
 * @author zahidahhanumalzahra
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class UTS_PBO extends javax.swing.JFrame {

    private JTextField jTextFieldKodeMK;
    private JTextField jTextFieldMataKuliah;
    private JTextField jTextFieldSKS;
    private JTextField jTextFieldSemesterAjar;
    private JButton jButtonSimpan;


    public UTS_PBO() {
        initComponents();
    }

    private void initComponents() {
        jTextFieldKodeMK = new JTextField();
        jTextFieldMataKuliah = new JTextField();
        jTextFieldSKS = new JTextField();
        jTextFieldSemesterAjar = new JTextField();
        jButtonSimpan = new JButton("Simpan");

        jButtonSimpan.addActionListener(evt -> {
         int kodeMK = Integer.parseInt(jTextFieldKodeMK.getText());
        String namaMK = jTextFieldMataKuliah.getText();
        int sks = Integer.parseInt(jTextFieldSKS.getText());
        int semesterAjar = Integer.parseInt(jTextFieldSemesterAjar.getText());
        simpanDataMataKuliah(kodeMK, namaMK, sks, semesterAjar);
        simpanDataMataKuliah(kodeMK, namaMK, sks, semesterAjar);
        });
        

       
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldKodeMK)
                    .addComponent(jTextFieldMataKuliah)
                    .addComponent(jTextFieldSKS)
                    .addComponent(jTextFieldSemesterAjar)
                    .addComponent(jButtonSimpan))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jTextFieldKodeMK)
                .addComponent(jTextFieldMataKuliah)
                .addComponent(jTextFieldSKS)
                .addComponent(jTextFieldSemesterAjar)
                .addComponent(jButtonSimpan)
        );

        add(panel);
        pack();
    }


    private void simpanDataMataKuliah(int kodeMK, String namaMK, int sks, int semesterAjar) {
    Connection conn = null; 
    PreparedStatement ps = null;
    
    
        try {
            String url = "jdbc:postgresql://localhost:5432/UTS PBO"; 
            String user = "postgres";
            String password = "197300";
            conn = DriverManager.getConnection(url, user, password);

             String sql = "INSERT INTO MataKuliah (KodeMK, NamaMK, SKS, SemesterAjar) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, kodeMK);        
            ps.setString(2, namaMK);   
            ps.setInt(3, sks);         
            ps.setInt(4, semesterAjar); 
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new UTS_PBO().setVisible(true));
    }
}
