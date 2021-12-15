package design.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class DBCon {
    private static final String driver;
    private static final String url;
    private static final String user;
    private static final String password;
    //定义一个连接
    private static Connection connection=null;
    //静态代码块连接数据库，加载驱动
    //静态方法只执行一次，用于初始化变量
    static {
        ConfigReader configReader=ConfigReader.getInstance();
        driver= configReader.getKey("driver");
        url= configReader.getKey("url");
        user= configReader.getKey("database_user");
        password= configReader.getKey("database_password");
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    //单例模式获取数据库连接实例
    private volatile static DBCon instance;
    private DBCon(){}
    public synchronized static DBCon getInstance(){
        if(instance==null){
            synchronized (DBCon.class){
                if(instance==null){
                    instance=new DBCon();
                }
            }
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}
