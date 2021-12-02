package design.model;

import design.entity.StudentInfo;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class StudentInfoModel implements Models<StudentInfo> {
    @Override
    public StudentInfo getSpecificInstance() {
        return new StudentInfo();
    }
}
