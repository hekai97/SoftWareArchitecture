package design.model;

import design.entity.Student;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class StudentModel implements Models<Student> {
    @Override
    public Student getSpecificInstance() {
        return new Student();
    }
}
