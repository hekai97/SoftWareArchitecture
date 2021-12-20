package design.view;

import design.controller.StudentList;
import design.remoteapi.RemoteInterface;
import design.util.DBCon;
import design.util.RemoteFunction;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Information.java
 * 文件标识：无
 * 内容摘要：该类单独显示学生的详细信息
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Information extends JPanel{
    JLabel spaceLabel=new JLabel("    ");
    JLabel snoLabel;
    JLabel nameLabel;
    JLabel sexLabel;
    JLabel ageLabel;
    JLabel idLabel;
    JLabel classLabel;
    JLabel facultyLabel;
    RemoteFunction remoteFunction=new RemoteFunction();
    public Information(String id, JMenuItem item){
        setLayout(new FlowLayout(FlowLayout.CENTER,1000,000));
        Connection con= DBCon.getInstance().getConnection();
//        RemoteFunction remoteFunction=new RemoteFunction();
        String sql1="select * from student where Sno="+id;
        StudentList studentList=new StudentList();
//        try {
//            PreparedStatement statement=con.prepareStatement(sql1);
//            statement.execute();
//            ResultSet st=statement.executeQuery();
////            ResultSet st= remoteFunction.getResult(sql1);
//            while(st.next()){
//                snoLabel=new JLabel("学号："+st.getString("Sno"));
//                nameLabel=new JLabel("姓名："+st.getString("Sname"));
//                sexLabel=new JLabel(Integer.parseInt(st.getString("Sid").substring(16,17))%2==0?"性别：女":"性别：男");
//                ageLabel=new JLabel("年龄："+ (Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(st.getString("Sid").substring(6,10))));
//                idLabel=new JLabel("身份证号："+st.getString("Sid"));
//                classLabel=new JLabel("班级："+st.getString("Sclassname"));
//                facultyLabel=new JLabel("学院："+getFacultyName(st.getString("Sfaculty")));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        Object[][] res= studentList.StudentRes(sql1);
        for(int i=0;i< res.length;++i){
            for(int j=0;j<res[i].length;++j){
                System.out.println(res[i][j]);
            }
        }
        snoLabel=new JLabel("学号："+res[0][0]);
        nameLabel=new JLabel("姓名："+res[0][1]);
        sexLabel=new JLabel(Integer.parseInt(((String)res[0][2]).substring(16,17))%2==0?"性别：女":"性别：男");
        ageLabel=new JLabel("年龄："+ (Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(((String)res[0][2]).substring(6,10))));
        idLabel=new JLabel("身份证号："+res[0][2]);
        classLabel=new JLabel("班级："+res[0][3]);
        facultyLabel=new JLabel("学院："+getFacultyName((String) res[0][4]));

        spaceLabel.setFont(new Font("",Font.BOLD,160));
        snoLabel.setFont(new Font("",Font.BOLD,30));
        nameLabel.setFont(new Font("",Font.BOLD,30));
        sexLabel.setFont(new Font("",Font.BOLD,30));
        ageLabel.setFont(new Font("",Font.BOLD,30));
        idLabel.setFont(new Font("",Font.BOLD,30));
        classLabel.setFont(new Font("",Font.BOLD,30));
        facultyLabel.setFont(new Font("",Font.BOLD,30));
        add(spaceLabel);
        add(snoLabel);
        add(nameLabel);
        add(sexLabel);
        add(ageLabel);
        add(idLabel);
        add(classLabel);
        add(facultyLabel);
    }
    private String getFacultyName(String Fno){
        String s = null;
        Connection con=DBCon.getInstance().getConnection();
        String sql="select Fname from faculty where Fno="+Fno;
//        try {
//            PreparedStatement preparedStatement=con.prepareStatement(sql);
//            preparedStatement.execute();
//            ResultSet st=preparedStatement.executeQuery();
////            ResultSet st= remoteFunction.getResult(sql);
//            while(st.next()){
//                s=st.getString("Fname");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        RemoteFunction remoteFunction=new RemoteFunction();
        try {
            s=remoteFunction.getStringByKey("Fname",sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return s;
    }
}
