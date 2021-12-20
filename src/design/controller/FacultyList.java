package design.controller;

import design.remoteapi.RemoteInterface;

import java.rmi.RemoteException;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：FacultyList.java
 * 文件标识：无
 * 内容摘要：该类返回从学院表中得到数据，将其作为数组返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class FacultyList {
//    RepositoryImp<Faculty> repositoryImp=new RepositoryImp<>();
//    FacultyFactory facultyFactory=new FacultyFactory();
    RemoteFunction remoteFunction=new RemoteFunction();
//    public List<Faculty> FacultyRes(){
    public Object[][] FacultyRes(){
        String sql= "select * from faculty";
        Object[][] res=null;
        try {
            res=remoteFunction.getResult(RemoteInterface.MYOBJECT.Faculty,sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
}
