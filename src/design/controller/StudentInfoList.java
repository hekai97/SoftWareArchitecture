package design.controller;

import design.entity.StudentInfo;
import design.factory.StudentInfoFactory;
import design.serverImp.RepositoryImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：StudentInfoList.java
 * 文件标识：无
 * 内容摘要：该类得到数据库中的学生信息，并且将其放在ArrayList中返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class StudentInfoList {
    RepositoryImp<StudentInfo> repositoryImp=new RepositoryImp<>();
    StudentInfoFactory studentInfoFactory=new StudentInfoFactory();
    public List<StudentInfo> StudentRes(){
        String sql= "select * from student_info";
        return repositoryImp.getResult(studentInfoFactory,sql);
    }
}
