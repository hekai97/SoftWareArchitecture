package design.entity;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class CourseWithTeacher {
    private String Cno;
    private String Cname;
    private String Tname;
    private String Ccredit;
    private String Ctime;
    public String getCno() {
        return Cno;
    }
    public void setCno(String cno) {
        Cno = cno;
    }
    public String getCname() {
        return Cname;
    }
    public void setCname(String cname) {
        Cname = cname;
    }
    public String getTname() {
        return Tname;
    }
    public void setTname(String tname) {
        Tname = tname;
    }
    public String getCcredit() {
        return Ccredit;
    }
    public void setCcredit(String ccredit) {
        Ccredit = ccredit;
    }
    public String getCtime() {
        return Ctime;
    }
    public void setCtime(String ctime) {
        Ctime = ctime;
    }
}
