package design.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class Verify {
    Connection con= DBCon.getInstance().getConnection();
    /**
     * 该方法从数据库中取值
     * 然后将取到的用户名和密码放在ArrayList中
     * */
    /**
     * 该函数的作用是用来判断传入的用户名和密码是否正确
     * 返回值：1、代表用户名正确，密码正确
     *       2、代表用户名正确但是密码错误
     *       3、代表用户名不正确
     */
    public int verifyUserPassword(int id,String user, String password){
        MD5Encryption md5encryption=new MD5Encryption();
        String sql;
        if(id==2){
            sql="select * from studentuser where Sno="+user;
            try {
                PreparedStatement preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
                ResultSet st=preparedStatement.executeQuery();
                while(st.next()){
                    if(md5encryption.MD5encrypt(password).equals(st.getString("Spassword"))){
                        return 1;
                    }else {
                        return 2;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(id==1){
            sql="select * from teacheruser where Tno="+user;
            try {
                PreparedStatement preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
                ResultSet st=preparedStatement.executeQuery();
                while(st.next()){
                    if(md5encryption.MD5encrypt(password).equals(st.getString("Tpassword"))){
                        return 1;
                    }else {
                        return 2;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            ConfigReader configReader=ConfigReader.getInstance();
            if(Objects.equals(user, configReader.getKey("admin_user")) && Objects.equals(md5encryption.MD5encrypt(password), configReader.getKey("admin_password"))){
                return 1;
            }
            else if(Objects.equals(user,configReader.getKey("admin_user"))&& !Objects.equals(md5encryption.MD5encrypt(password), configReader.getKey("admin_password"))){
                return 2;
            }
            else {
                return 3;
            }
        }
        return 3;
    }
}