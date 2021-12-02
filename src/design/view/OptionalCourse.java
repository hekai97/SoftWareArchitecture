package design.view;

import design.controller.CourseList;
import design.util.DBCon;
import design.entity.CourseWithTeacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：OptionalCourse.java
 * 文件标识：无
 * 内容摘要：在学生窗口上显示学生可选的课程
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class OptionalCourse extends JPanel {
    JLabel title=new JLabel("选课",JLabel.CENTER);
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jScrollPane;
    JButton selectButton=new JButton("选课");
    public OptionalCourse(String id,JMenuItem item){
        title.setFont(new Font("",Font.BOLD,20));
        setLayout(new BorderLayout());
        /*OptionalCourseList optionalCourseList=new OptionalCourseList();
        List<Course> list=optionalCourseList.CourseRes(id);*/
        CourseList courseList=new CourseList();
        List<CourseWithTeacher> list=courseList.CourseRes(true,id);
        String[] name={"课程号","课程名","任课教师","学分","学时"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            CourseWithTeacher course1=list.get(i);
            res[i][0]=course1.getCno();
            res[i][1]=course1.getCname();
            res[i][2]=course1.getTname();
            res[i][3]=course1.getCcredit();
            res[i][4]=course1.getCtime();
        }
        dtm=new DefaultTableModel(res,name);
        table=new JTable(dtm);
        add(title,BorderLayout.NORTH);
        jScrollPane=new JScrollPane(table);
        jScrollPane.setViewportView(table);
        add(jScrollPane,BorderLayout.CENTER);
        AddToSouth(res,item,id);
    }
    private void AddToSouth(Object[][] res,JMenuItem item,String id){
        Connection con= DBCon.getInstance().getConnection();
        selectButton.addActionListener(e-> {
            if (JOptionPane.showConfirmDialog(null, "确认选课？") == 0) {

                String sql = "insert into grade (Sno,Cno) values(?,?)";
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, (String) dtm.getValueAt(table.getSelectedRow(), 0));
                    preparedStatement.execute();
                    item.doClick();
                    JOptionPane.showMessageDialog(null, "选课成功");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        JPanel panel=new JPanel();
        panel.add(selectButton);
        add(panel,BorderLayout.SOUTH);
    }
}
