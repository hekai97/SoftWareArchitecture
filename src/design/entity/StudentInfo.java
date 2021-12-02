package design.entity;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class StudentInfo {
    //学生学号
    private String Sno;
    //学生姓名
    private String Name;
    //性别
    private String sex;
    //年龄
    private double age;
    //学生所在院系
    private String Faculty;

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }


    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }
}
