package design.serverImp;

import design.entity.Teacher;
import design.factory.ModelFactory;
import design.factory.TeacherFactory;
import design.server.Repository;
import design.util.DBCon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */

/**
 * 这里的E传进来的是一个具体的实体类
 * 比如：Student，Teacher
 */
public class RepositoryImp<E> implements Repository<E> {
    @Override
    public List<E> getResult(ModelFactory factory,String sql) {
        Connection connection= DBCon.getInstance().getConnection();
//        RemoteFunction remoteFunction=new RemoteFunction();
        List<E> list=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.execute();
            ResultSet resultSet= preparedStatement.executeQuery();
//            ResultSet resultSet= remoteFunction.getResult(sql);
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            String[] ColumnName=getTableColumnName(resultSetMetaData);
            /**首先获取数据库列名，然后通过传进来的工厂类获取实例对应的类，通过反射
             * 然后再拿到这个类中的自定义的方法，不包括父类的
             * 然后根据数据库列名将拿到的方法进行拆分，定义一个二维的方法数组，将对应列名的
             * 方法进行拆分，比如，对于Teacher类有三列，分别是Tno，Tname，Tfaculty
             * 则拿取的方法有这三个的setter和getter，此时定义二维数组，将setter和getter方法
             * 拆分开，functions[0][]中放的是对应列名顺序的set方法，functions[1][]中
             * 存放的是对应列名顺序的getter方法*/
            Method[][] functions=Splitting(factory.getInstance().getClass().getDeclaredMethods(), ColumnName);
//            for(int i=0;i< functions.length;++i){
//                for(int j=0;j<functions[i].length;++j){
//                    System.out.println(functions[i][j]);
//                }
//            }

            while(resultSet.next()){
                E object= (E) factory.getInstance();
                for(int i=0;i< ColumnName.length;++i){
                    try {
                        functions[0][i].invoke(object,resultSet.getString(ColumnName[i]));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                list.add(object);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }
    String[] getTableColumnName(ResultSetMetaData resultSetMetaData){
        String[] ColumnName = null;
        try {
            ColumnName=new String[resultSetMetaData.getColumnCount()];
            for(int i=0;i<ColumnName.length;++i){
                ColumnName[i]= resultSetMetaData.getColumnName(i+1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ColumnName;
    }
    private Method[][] Splitting(Method[] functions,String[] columnName){
        Method[][] result=new Method[2][columnName.length];
        for(int i=0;i<columnName.length;++i){
            for(int j=0;j<functions.length;++j){
                if(functions[j].getName().toLowerCase().contains(columnName[i].toLowerCase())){
                    if(functions[j].getName().contains("get")){
                        result[1][i]=functions[j];
                    }
                    else if(functions[j].getName().contains("set")){
                        result[0][i]=functions[j];
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        RepositoryImp<Teacher> repositoryImp=new RepositoryImp<>();
        List<Teacher> list= repositoryImp.getResult(new TeacherFactory(),"select * from Teacher");
        for(int i=0;i<list.size();++i){
            System.out.println(list.get(i).getTno());
            System.out.println(list.get(i).getTname());
            System.out.println(list.get(i).getTfaculty());
        }
    }
}
