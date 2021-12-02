package design.view;

import design.util.Verify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Login.java
 * 文件标识：无
 * 内容摘要：登录的主界面，程序的入口位置，该界面为程序调用的第一个界面
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Login {
    private final JFrame frame=new JFrame("登录");
    private final JLabel title=new JLabel("选课系统",JLabel.CENTER);
    private final JLabel userLabel=new JLabel("用户名：",JLabel.RIGHT);
    private final JLabel passwordLabel=new JLabel("密码：",JLabel.RIGHT);
    private final JTextField userTextField=new JTextField(20);
    private final JPasswordField passwordTextField=new JPasswordField(20);
    private final JButton button=new JButton("登录");
    private final JPanel panel=new JPanel();
    private final String[] question={"<-请选择登陆角色->",
                                     "教师",
                                     "学生",
                                     "管理员"};
    private String answer=null;
    private final JComboBox<String> choice=new JComboBox<>(question);

    ActionListener actionListener= e -> {
        Verify verify=new Verify();
        String password;
        if(answer==null||answer.equals(question[0]))
        {
            JOptionPane.showMessageDialog(frame,"请选择身份","提示",JOptionPane.PLAIN_MESSAGE);
        } else if(answer.equals(question[1])) {
            password=new String(passwordTextField.getPassword());
            //Verify verify=new Verify();
            switch (verify.verifyUserPassword(1,userTextField.getText(),password)) {
                case 1 -> TeacherSuccess(userTextField.getText());
                case 2 -> PasswordFail();
                case 3 -> UserFail();
            }
        } else if(answer.equals(question[2]))
        {
            password=new String(passwordTextField.getPassword());
            //Verify verify=new Verify();
            switch (verify.verifyUserPassword(2,userTextField.getText(),password))
            {
                case 1 -> StudentSuccess(userTextField.getText());
                case 2 -> PasswordFail();
                case 3 -> UserFail();
            }
        } else if(answer.equals(question[3])) {
            switch (verify.verifyUserPassword(3,userTextField.getText(),new String(passwordTextField.getPassword())))
            {
                case 1 -> AdminSuccess();
                case 2 -> PasswordFail();
                case 3 -> UserFail();
            }
//            if(userTextField.getText().equals("1234")&&new String(passwordTextField.getPassword()).equals("1234")) {
//                JOptionPane.showMessageDialog(frame,"登录成功！","提示",JOptionPane.PLAIN_MESSAGE);
//                frame.dispose();
//                new AdminWindow();
//            } else {
//                JOptionPane.showMessageDialog(frame,"请检查用户名或密码！","提示",JOptionPane.PLAIN_MESSAGE);
//            }
        }

    };
    private void StudentSuccess(String id){
        JOptionPane.showMessageDialog(frame,"登录成功","提示", JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
        new StudentWindow(id);
    }
    private void TeacherSuccess(String id){
        JOptionPane.showMessageDialog(frame,"登陆成功","提示",JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
        new TeacherWindow(id);
    }
    private void AdminSuccess(){
        JOptionPane.showMessageDialog(frame,"登陆成功","提示",JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
        new AdminWindow();
    }
    private void PasswordFail(){
        JOptionPane.showMessageDialog(frame,"登录失败,\n请检查密码。","密码错误", JOptionPane.PLAIN_MESSAGE);
    }
    private void UserFail(){
        JOptionPane.showMessageDialog(frame,"登录失败\n请检查用户名","用户名错误",JOptionPane.PLAIN_MESSAGE);
    }
    public void CreateWindow(){
        setFrame();
    }
    private void setFrame(){
        frame.setSize(500,500);
        frame.setResizable(false);          //禁止改变大小
        frame.setLocationRelativeTo(null);    //让界面居中
        setPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void setPanel(){
        panel.setLayout(null);
        //设置panel透明，显示背景图
        panel.setBackground(null);
        panel.setOpaque(false);
        setTitle();
        setUserLabel();
        setPasswordLabel();
        setUserTextField();
        setPasswordTextField();
        setButton();
        setChoice();
        panel.add(title);
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(userTextField);
        panel.add(passwordTextField);
        panel.add(choice);
        panel.add(button);
    }
    //设置登录界面的主标题
    private void setTitle(){
        title.setBounds(100,50,300,50);
        title.setFont(new Font("",Font.BOLD,25));
    }
    //设置登录界面的用户文本，将字体调到20号字
    private void setUserLabel(){
        userLabel.setFont(new Font("",Font.BOLD,20));
        userLabel.setBounds(100,150,100,20);
    }
    //设置登录界面的密码文本，将字体调到20号字
    private void setPasswordLabel(){
        passwordLabel.setFont(new Font("",Font.BOLD,20));
        passwordLabel.setBounds(100,190,100,20);
    }
    //设置登录界面的用户账号框
    private void setUserTextField(){
        userTextField.setBounds(220,150,150,20);
        userTextField.addActionListener(actionListener);
    }
    //设置登录界面的用户密码框
    private void setPasswordTextField(){
        passwordTextField.setBounds(220,190,150,20);
        passwordTextField.addActionListener(actionListener);
    }
    private void setChoice(){
        choice.setBounds(220,250,150,20);
        choice.addItemListener(e -> answer=question[choice.getSelectedIndex()]);
}
    private void setButton(){
        button.setFont(new Font("",Font.BOLD,20));
        button.setBounds(230,300,90,30);
        button.addActionListener(actionListener);
    }
}
