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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import server.MapleItemInformationProvider;
import server.Start;

/**
 *
 * @author Administrator
 */
public class 商店管理控制台 extends javax.swing.JFrame {

    /**
     * Creates new form 锻造控制台
     */
    public 商店管理控制台() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Image/Icon.png"));
        setIconImage(icon.getImage());
        setTitle("商店管理控制台");
        initComponents();
        //显示数据剧中代码开始
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        游戏商店2.setDefaultRenderer(Object.class, cr);//账号中心
        //显示数据剧中代码结束
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        游戏商店2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        查询商店 = new javax.swing.JTextField();
        查询商店2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        商品序号 = new javax.swing.JTextField();
        商店代码 = new javax.swing.JTextField();
        商品物品代码 = new javax.swing.JTextField();
        商品售价金币 = new javax.swing.JTextField();
        商品名称 = new javax.swing.JTextField();
        新增商品 = new javax.swing.JButton();
        删除商品 = new javax.swing.JButton();
        修改商品 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();

        setResizable(false);

        游戏商店2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "序号", "商店ID", "物品代码", "价格", "物品名称", "图标"
            }
        ));
        游戏商店2.setRowHeight(30);
        jScrollPane1.setViewportView(游戏商店2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("查询商店"));

        jLabel1.setText("商店ID");

        查询商店2.setText("查询商店");
        查询商店2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                查询商店2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(查询商店, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(查询商店2)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(查询商店, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(查询商店2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("修改商店物品"));

        jLabel2.setText("序号");

        jLabel3.setText("商店ID");

        jLabel4.setText("物品代码");

        jLabel5.setText("出售金币");

        jLabel6.setText("物品名称");

        商品序号.setEditable(false);

        商品名称.setEditable(false);

        新增商品.setText("新增");
        新增商品.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                新增商品ActionPerformed(evt);
            }
        });

        删除商品.setText("删除");
        删除商品.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                删除商品ActionPerformed(evt);
            }
        });

        修改商品.setText("修改");
        修改商品.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                修改商品ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(商品序号, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(商店代码, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(商品物品代码, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(商品售价金币, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(商品名称, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel5)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(新增商品)
                        .addGap(18, 18, 18)
                        .addComponent(删除商品)
                        .addGap(18, 18, 18)
                        .addComponent(修改商品)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(商品序号, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(商店代码, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(商品物品代码, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(商品售价金币, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(商品名称, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(新增商品)
                    .addComponent(删除商品)
                    .addComponent(修改商品))
                .addContainerGap())
        );

        jButton19.setText("查看全部物品");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton19)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void 查询商店2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_查询商店2ActionPerformed
        查询商店(1);
    }//GEN-LAST:event_查询商店2ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        查询商店(0);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void 新增商品ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_新增商品ActionPerformed

        boolean result = this.商品物品代码.getText().matches("[0-9]+");
        boolean result1 = this.商店代码.getText().matches("[0-9]+");
        boolean result2 = this.商品售价金币.getText().matches("[0-9]+");

        if (result && result1 && result2) {
            if (Integer.parseInt(this.商店代码.getText()) < 0 && Integer.parseInt(this.商品物品代码.getText()) < 0 && Integer.parseInt(this.商品售价金币.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            try (Connection con = DBConPool.getInstance().getDataSource().getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO shopitems (shopid ,itemid ,price ,pitch ,position ,reqitem ,reqitemq) VALUES ( ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.商店代码.getText()));
                ps.setInt(2, Integer.parseInt(this.商品物品代码.getText()));
                ps.setInt(3, Integer.parseInt(this.商品售价金币.getText()));
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.executeUpdate();
                查询商店(1);
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "[信息]:新增商店商品成功。");
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:输入<商店ID><物品代码><售价>。");
        }
    }//GEN-LAST:event_新增商品ActionPerformed

    private void 删除商品ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_删除商品ActionPerformed

        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.商品序号.getText().matches("[0-9]+");
        if (result == true) {
            int 商城SN编码 = Integer.parseInt(this.商品序号.getText());
            try {
                ps1 = DBConPool.getInstance().getDataSource().getConnection().prepareStatement("SELECT * FROM shopitems WHERE shopitemid = ?");
                ps1.setInt(1, 商城SN编码);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlstr = " delete from shopitems where shopitemid =" + 商城SN编码 + "";
                    ps1.executeUpdate(sqlstr);
                    查询商店(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "[信息]:删除商店商品成功。");
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择你要删除的商品。");
        }
    }//GEN-LAST:event_删除商品ActionPerformed

    private void 修改商品ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_修改商品ActionPerformed
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean result = this.商品物品代码.getText().matches("[0-9]+");
        boolean result1 = this.商店代码.getText().matches("[0-9]+");
        boolean result2 = this.商品售价金币.getText().matches("[0-9]+");

        if (result && result1 && result2) {
            if (Integer.parseInt(this.商店代码.getText()) < 0 && Integer.parseInt(this.商品物品代码.getText()) < 0 && Integer.parseInt(this.商品售价金币.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps = DBConPool.getInstance().getDataSource().getConnection().prepareStatement("UPDATE shopitems SET itemid = ?,price = ?,shopid = ?WHERE shopitemid = ?");
                ps1 = DBConPool.getInstance().getDataSource().getConnection().prepareStatement("SELECT * FROM shopitems WHERE shopitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.商品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update shopitems set itemid='" + this.商品物品代码.getText() + "' where shopitemid=" + this.商品序号.getText() + ";";
                    PreparedStatement itemid = DBConPool.getInstance().getDataSource().getConnection().prepareStatement(sqlString1);
                    itemid.executeUpdate(sqlString1);

                    sqlString2 = "update shopitems set price='" + this.商品售价金币.getText() + "' where shopitemid=" + this.商品序号.getText() + ";";
                    PreparedStatement price = DBConPool.getInstance().getDataSource().getConnection().prepareStatement(sqlString2);
                    price.executeUpdate(sqlString2);

                    sqlString3 = "update shopitems set shopid='" + this.商店代码.getText() + "' where shopitemid=" + this.商品序号.getText() + ";";
                    PreparedStatement shopid = DBConPool.getInstance().getDataSource().getConnection().prepareStatement(sqlString3);
                    shopid.executeUpdate(sqlString3);

                    查询商店(1);
                }
                JOptionPane.showMessageDialog(null, "[信息]:商店商品修改成功。");
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:选择你要修改的商品,并填写<商店ID><物品代码><售价金币>。");
        }
    }//GEN-LAST:event_修改商品ActionPerformed
    public void 查询商店(int lx) {

        boolean result = this.查询商店.getText().matches("[0-9]+");
        if (lx == 0) {
            result = true;
        }
        if (result) {
            if (lx != 0) {
                if (Integer.parseInt(this.查询商店.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                    return;
                }
            }
            for (int i = ((DefaultTableModel) (this.游戏商店2.getModel())).getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) (this.游戏商店2.getModel())).removeRow(i);
            }
            try {
                Connection con = DBConPool.getInstance().getDataSource().getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                if (lx == 0) {
                    ps = con.prepareStatement("SELECT * FROM shopitems");
                } else {
                    ps = con.prepareStatement("SELECT * FROM shopitems WHERE shopid = " + Integer.parseInt(this.查询商店.getText()) + " ");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    int itemId = rs.getInt("itemid");
                    ((DefaultTableModel) 游戏商店2.getModel()).insertRow(游戏商店2.getRowCount(), new Object[]{
                        rs.getInt("shopitemid"),
                        rs.getInt("shopid"),
                        itemId,
                        rs.getInt("price"),
                        MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")),//读取物品名称
                        itemId
                    });
                }
                JOptionPane.showMessageDialog(null, "[信息]:商城物品查询成功。");
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            游戏商店2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int i = 游戏商店2.getSelectedRow();
                    String a = 游戏商店2.getValueAt(i, 0).toString();
                    String a1 = 游戏商店2.getValueAt(i, 1).toString();
                    String a2 = 游戏商店2.getValueAt(i, 2).toString();
                    String a3 = 游戏商店2.getValueAt(i, 3).toString();
                    String a4 = 游戏商店2.getValueAt(i, 4).toString();
                    商品序号.setText(a);
                    商店代码.setText(a1);
                    商品物品代码.setText(a2);
                    商品售价金币.setText(a3);
                    商品名称.setText(a4);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你需要查询的商店ID。");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton 修改商品;
    private javax.swing.JButton 删除商品;
    private javax.swing.JTextField 商品名称;
    private javax.swing.JTextField 商品售价金币;
    private javax.swing.JTextField 商品序号;
    private javax.swing.JTextField 商品物品代码;
    private javax.swing.JTextField 商店代码;
    private javax.swing.JButton 新增商品;
    private javax.swing.JTextField 查询商店;
    private javax.swing.JButton 查询商店2;
    private javax.swing.JTable 游戏商店2;
    // End of variables declaration//GEN-END:variables
}
