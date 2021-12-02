package design.controller;

import design.entity.Student;
import design.factory.StudentFactory;
import design.serverImp.RepositoryImp;
import java.util.List;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：StudentList.java
 * 文件标识：无
 * 内容摘要：该类返回从学生表中得到数据，将其作为数组返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class StudentList {
    RepositoryImp<Student> repositoryImp=new RepositoryImp<>();
    StudentFactory studentFactory=new StudentFactory();
    public List<Student> StudentRes(){
        String sql= "select * from student";
        return repositoryImp.getResult(studentFactory,sql);
    }
}
