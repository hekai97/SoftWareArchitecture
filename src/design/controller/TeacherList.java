package design.controller;

import design.remoteapi.RemoteInterface;

import java.rmi.RemoteException;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：TeacherList.java
 * 文件标识：无
 * 内容摘要：该类返回从教师表中得到数据，将其作为数组返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class TeacherList {
//    RepositoryImp<Teacher> repositoryImp=new RepositoryImp<>();
//    TeacherFactory teacherFactory=new TeacherFactory();
    RemoteFunction remoteFunction=new RemoteFunction();
//    public List<Teacher> TeacherRes(){
    public Object[][] TeacherRes(){
        String sql= "select * from teacher";
        Object[][] res=null;
        try {
            res=remoteFunction.getResult(RemoteInterface.MYOBJECT.Teacher,sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
}
