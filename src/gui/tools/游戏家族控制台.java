/**
 * Restore By Windyboy 2020/08/14 00:49
 */
package gui.tools;

import database.DBConPool;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import server.MapleItemInformationProvider;
import server.Start;
/**
 *
 * @author 疯神
 */
public class 游戏家族控制台 extends javax.swing.JFrame {

    /**
     * Creates new form 锻造控制台
     */
    public 游戏家族控制台() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/Icon.png"));
        setIconImage(icon.getImage());
        setTitle("游戏家族控制台");
        initComponents();
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
        刷新家族信息 = new javax.swing.JButton();
        家族成员3 = new javax.swing.JTextField();
        jLabel199 = new javax.swing.JLabel();
        家族成员4 = new javax.swing.JTextField();
        jLabel200 = new javax.swing.JLabel();
        家族成员5 = new javax.swing.JTextField();
        jLabel313 = new javax.swing.JLabel();
        家族ID = new javax.swing.JTextField();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel314 = new javax.swing.JLabel();
        家族名称 = new javax.swing.JTextField();
        jLabel196 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        家族族长 = new javax.swing.JTextField();
        家族GP = new javax.swing.JTextField();
        家族成员2 = new javax.swing.JTextField();
        jLabel198 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        家族信息 = new javax.swing.JTable();

        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("游戏家族控制台"));

        刷新家族信息.setText("刷新家族信息");
        刷新家族信息.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                刷新家族信息ActionPerformed(evt);
            }
        });

        家族成员3.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族成员3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族成员3ActionPerformed(evt);
            }
        });

        jLabel199.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel199.setText("家族成员3；");

        家族成员4.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族成员4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族成员4ActionPerformed(evt);
            }
        });

        jLabel200.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel200.setText("家族成员4；");

        家族成员5.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族成员5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族成员5ActionPerformed(evt);
            }
        });

        jLabel313.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel313.setText("家族成员5；");

        家族ID.setEditable(false);
        家族ID.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族IDActionPerformed(evt);
            }
        });

        jLabel194.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel194.setText("家族ID；");

        jLabel195.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel195.setText("家族名称；");

        jLabel314.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel314.setText("家族GP；");

        家族名称.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族名称.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族名称ActionPerformed(evt);
            }
        });

        jLabel196.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel196.setText("家族族长；");

        jButton20.setText("更改家族GP点数");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        家族族长.setEditable(false);
        家族族长.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族族长.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族族长ActionPerformed(evt);
            }
        });

        家族GP.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族GP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族GPActionPerformed(evt);
            }
        });

        家族成员2.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族成员2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                家族成员2ActionPerformed(evt);
            }
        });

        jLabel198.setFont(new java.awt.Font("幼圆", 0, 12)); // NOI18N
        jLabel198.setText("家族成员2；");

        家族信息.setFont(new java.awt.Font("幼圆", 0, 15)); // NOI18N
        家族信息.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "家族ID", "家族名称", "族长/角色ID", "成员2", "成员3", "成员4", "成员5", "家族GP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        家族信息.getTableHeader().setReorderingAllowed(false);
        jScrollPane24.setViewportView(家族信息);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(刷新家族信息, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel194, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(家族ID, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(家族名称, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(家族族长, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(家族成员2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(家族成员3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(家族成员4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel195)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel196)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel198)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel199)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel200)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(家族成员5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(家族GP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel313)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel314)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel194)
                        .addComponent(jLabel195)
                        .addComponent(jLabel196)
                        .addComponent(jLabel198)
                        .addComponent(jLabel199)
                        .addComponent(jLabel200)
                        .addComponent(jLabel313)
                        .addComponent(jLabel314))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(家族ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族名称, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族族长, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族成员2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族成员3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族成员4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族成员5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(家族GP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(刷新家族信息, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    
    private void 刷新家族信息ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_刷新家族信息ActionPerformed
        刷新家族信息();
    }//GEN-LAST:event_刷新家族信息ActionPerformed

    private void 家族成员3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族成员3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族成员3ActionPerformed

    private void 家族成员4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族成员4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族成员4ActionPerformed

    private void 家族成员5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族成员5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族成员5ActionPerformed

    private void 家族IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族IDActionPerformed

    private void 家族名称ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族名称ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族名称ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {

            Connection con = DBConPool.getInstance().getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE guilds SET GP =" + 家族GP.getText().toString() + " WHERE guildid = " + 家族ID.getText().toString() + "");
            ps.executeUpdate();
            ps.close();
            System.out.println("update guild gp !");
            刷新家族信息();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void 家族族长ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族族长ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族族长ActionPerformed

    private void 家族GPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族GPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族GPActionPerformed

    private void 家族成员2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_家族成员2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_家族成员2ActionPerformed
    private void 刷新家族信息() {
        try {
            Connection con = DBConPool.getInstance().getDataSource().getConnection();
            PreparedStatement ps = null;

            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters");
            rs = ps.executeQuery();

            /*   while (rs.next()) {
                ((DefaultTableModel) 角色信息.getModel()).insertRow(角色信息.getRowCount(), new Object[]{
                    rs.getString("name")
                });
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = ((DefaultTableModel) (this.家族信息.getModel())).getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) (this.家族信息.getModel())).removeRow(i);
        }
        try {
            Connection con = DBConPool.getInstance().getDataSource().getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM guilds");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) 家族信息.getModel()).insertRow(家族信息.getRowCount(), new Object[]{
                    rs.getInt("guildid"),
                    rs.getString("name"),
                    //ooors.getInt("leader"),
                    //    NPCConversationManager.角色ID取名字(rs.getInt("leader")),
                    rs.getString("rank1title"),
                    rs.getString("rank2title"),
                    rs.getString("rank3title"),
                    rs.getString("rank4title"),
                    rs.getString("rank5title"),
                    rs.getString("GP")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        家族信息.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = 家族信息.getSelectedRow();
                String a1 = 家族信息.getValueAt(i, 0).toString();
                String a2 = 家族信息.getValueAt(i, 1).toString();
                String a3 = 家族信息.getValueAt(i, 2).toString();
                String a4 = 家族信息.getValueAt(i, 3).toString();
                String a5 = 家族信息.getValueAt(i, 4).toString();
                String a6 = 家族信息.getValueAt(i, 5).toString();
                String a7 = 家族信息.getValueAt(i, 6).toString();
                String a8 = 家族信息.getValueAt(i, 7).toString();

                家族ID.setText(a1);
                家族名称.setText(a2);
                家族族长.setText(a3);
                家族成员2.setText(a4);
                家族成员3.setText(a5);
                家族成员4.setText(a6);
                家族成员5.setText(a7);
                家族GP.setText(a8);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton20;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JButton 刷新家族信息;
    private javax.swing.JTextField 家族GP;
    private javax.swing.JTextField 家族ID;
    private javax.swing.JTable 家族信息;
    private javax.swing.JTextField 家族名称;
    private javax.swing.JTextField 家族成员2;
    private javax.swing.JTextField 家族成员3;
    private javax.swing.JTextField 家族成员4;
    private javax.swing.JTextField 家族成员5;
    private javax.swing.JTextField 家族族长;
    // End of variables declaration//GEN-END:variables
}
