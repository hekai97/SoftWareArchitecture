package design.model;

import design.entity.StudentGradeForTeacher;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class StudentGradeForTeacherModel implements Models<StudentGradeForTeacher> {
    @Override
    public StudentGradeForTeacher getSpecificInstance() {
        return new StudentGradeForTeacher();
    }
}
