package design.controller;

import design.entity.Course;
import design.entity.CourseWithTeacher;
import design.factory.CourseFactory;
import design.factory.CourseWithTeacherFactory;
import design.model.CourseWithTeacherModel;
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
 * 文件名称：CourseList.java
 * 文件标识：无
 * 内容摘要：该类返回从课程表中得到数据，将其作为数组返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class CourseList {
    RepositoryImp<Course> repositoryImp=new RepositoryImp<>();
    CourseFactory courseFactory=new CourseFactory();
    RepositoryImp<CourseWithTeacher> courseWithTeacherModelRepositoryImp=new RepositoryImp<>();
    CourseWithTeacherFactory courseWithTeacherFactory=new CourseWithTeacherFactory();
    public List<Course> CourseRes(){
        String sql= "select * from Course";
        return repositoryImp.getResult(courseFactory,sql);
    }
    public List<CourseWithTeacher> CourseRes(boolean res, String id){
        String s;
        if(res) {
             s= "select Cno,Cname,Tname,Ccredit,Ctime from Course,teacher"
                     + " where Cno not in (select grade.Cno from grade where Sno="+id
                     + ") AND course.Ctno =teacher.Tno" ;
        }
        else{
            s="select Cno,Cname,Tname,Ccredit,Ctime from Course,teacher"
                    + " where Cno in (select grade.Cno from grade where Sno="+id
                    + ") AND course.Ctno =teacher.Tno" ;
        }
        return getCourses(s);
    }

    List<CourseWithTeacher> getCourses(String s) {
        return courseWithTeacherModelRepositoryImp.getResult(courseWithTeacherFactory,s);
    }
}
