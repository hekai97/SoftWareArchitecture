package design.remoteapi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author: hekai
 * @Date: 2021/12/20
 */
public interface RemoteInterfaceSelect extends Remote {
    public enum MYOBJECT{Student,Teacher,Faculty,Grade,Course,StudentGradeForStudent,StudentGradeForTeacher,CourseWithTeacher};
    public Object[][] getResult(MYOBJECT index, String sql) throws RemoteException;
}
