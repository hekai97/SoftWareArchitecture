package design.model;

import design.entity.StudentGradeForStudent;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class StudentGradeModel implements Models<StudentGradeForStudent> {
    @Override
    public StudentGradeForStudent getSpecificInstance() {
        return new StudentGradeForStudent();
    }
}
