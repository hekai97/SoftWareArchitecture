package design.controller;

import design.remoteapi.RemoteInterface;

import java.rmi.RemoteException;

/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：GradeList.java
 * 文件标识：无
 * 内容摘要：该类根据不同的用户从数据库中筛选出不同的成绩值，然后将其返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class GradeList {
    //返回表的完整结构
//    RepositoryImp<Grade> repositoryImp=new RepositoryImp<>();
//    GradeFactory gradeFactory=new GradeFactory();
//    RepositoryImp<StudentGradeForStudent> studentGradeRepositoryImp=new RepositoryImp<>();
//    StudentGradeFactory studentGradeFactory=new StudentGradeFactory();
//    RepositoryImp<StudentGradeForTeacher> studentGradeForTeacherRepositoryImp=new RepositoryImp<>();
//    StudentGradeForTeacherFactory studentGradeForTeacherFactory=new StudentGradeForTeacherFactory();
//    public List<Grade> GradeRes(){
//        String sql= "select * from grade";
//        return repositoryImp.getResult(gradeFactory,sql);
//    }
//    public List<StudentGradeForStudent> GradeResForStudent(String id){
//        String sql= "select Cname,grade from course,grade where grade.Cno=course.Cno AND grade.Sno="+id;
//        return studentGradeRepositoryImp.getResult(studentGradeFactory,sql);
//    }
//    public List<StudentGradeForTeacher> GradeResForTeacher(String id){
//        String sql= "select Sname,grade from student,grade " +
//                "where grade.Cno in (" +
//                "select Cno from course where course.Ctno=" +id+")"+
//                "AND student.Sno=grade.Sno";
//        return studentGradeForTeacherRepositoryImp.getResult(studentGradeForTeacherFactory,sql);
//    }
    RemoteFunction remoteFunction=new RemoteFunction();
    public Object[][] GradeRes(){
        String sql= "select * from grade";
        Object[][] res=null;
        try {
            res=remoteFunction.getResult(RemoteInterface.MYOBJECT.Grade,sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
    public Object[][] GradeResForStudent(String id){
        String sql= "select Cname,grade from course,grade where grade.Cno=course.Cno AND grade.Sno="+id;
        Object[][] res=null;
        try {
            res=remoteFunction.getResult(RemoteInterface.MYOBJECT.StudentGradeForStudent,sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
    public Object[][] GradeResForTeacher(String id){
        String sql= "select Sname,grade from student,grade " +
                "where grade.Cno in (" +
                "select Cno from course where course.Ctno=" +id+")"+
                "AND student.Sno=grade.Sno";
        Object[][] res=null;
        try {
            res=remoteFunction.getResult(RemoteInterface.MYOBJECT.StudentGradeForTeacher,sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
}
