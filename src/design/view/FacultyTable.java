package design.view;

import design.controller.FacultyList;
import design.entity.Faculty;
import design.util.RemoteFunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Vector;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：FacultyTable.java
 * 文件标识：无
 * 内容摘要：该类从学院表中得到数据，然后将其以表格的形式展示在窗口上
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class FacultyTable extends JPanel {
    JLabel title=new JLabel("学院表",JLabel.CENTER);
    JTable jTable;
    JScrollPane jScrollPane;
    DefaultTableModel defaultTableModel;
    JButton newRowbutton=new JButton("新建一行");
    JButton deletebutton=new JButton("删除");
    JButton updatebutton=new JButton("确认修改");
    public FacultyTable(JMenuItem item){
        title.setFont(new Font("",Font.BOLD,30));
        setLayout(new BorderLayout());
        FacultyList facultyList =new FacultyList();
//        List<Faculty> list= facultyList.FacultyRes();
        String[] name={"学院代号","学院名"};
//        Object[][] res=new Object[list.size()][name.length];
//        for(int i=0;i<list.size();i++){
//            Faculty faculty1=list.get(i);
//            res[i][0]=faculty1.getFno();
//            res[i][1]=faculty1.getFname();
//        }
        Object[][] res= facultyList.FacultyRes();
        defaultTableModel=new DefaultTableModel(res,name);
        jTable=new JTable(defaultTableModel);
        //jTable.setRowHeight(50);
        add(title,BorderLayout.NORTH);
        jScrollPane=new JScrollPane(jTable);
        jScrollPane.setViewportView(jTable);
        add(jScrollPane,BorderLayout.CENTER);
        AddToSouth(res,item);
    }
    private void AddToSouth(Object[][] res,JMenuItem item){
        RemoteFunction remoteFunction=new RemoteFunction();
        deletebutton.addActionListener(e -> {
            int[] selectedRows = jTable.getSelectedRows();
            if(JOptionPane.showConfirmDialog(null,"确认删除？")==0) {
                if (selectedRows.length > 0) {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        try {
                            String sql = "delete from faculty where Fno=" + defaultTableModel.getValueAt(selectedRows[i], 0);
                            remoteFunction.execute(sql);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        defaultTableModel.removeRow(selectedRows[i]);
                    }
                    item.doClick();
                    JOptionPane.showMessageDialog(null, "删除成功");
                }
            }
        });
        newRowbutton.addActionListener(e->{
            defaultTableModel.addRow(new Vector<>());
        });
        /*alter+enter确定插入数据****/
        jTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    String sql="insert into faculty values('"
                            +(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),0)+"','"
                            +(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),1)+"')";
                    try {
                        remoteFunction.execute(sql);
                        item.doClick();
                        JOptionPane.showMessageDialog(null,"插入成功");
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(null,"错误\n1.检查学院编号是否重复\n2.检查是否有空值");
                    }catch (SQLException ey){
                        JOptionPane.showMessageDialog(null,"输入数据不符合规范");
                    }
                }
            }
        });
        updatebutton.addActionListener(e -> {
            String sql="update faculty set "
                    +"Fno='" +defaultTableModel.getValueAt(jTable.getSelectedRow(),0)+"',"
                    +"Fname='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),1)+"' "
                    +"where Fno="+res[jTable.getSelectedRow()][0];
            try {
                remoteFunction.execute(sql);
                JOptionPane.showMessageDialog(null,"修改成功");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        JPanel panel=new JPanel();
        panel.add(newRowbutton);
        panel.add(deletebutton);
        panel.add(updatebutton);
        add(panel,BorderLayout.SOUTH);
    }
}
