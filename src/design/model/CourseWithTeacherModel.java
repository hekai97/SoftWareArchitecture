package design.model;

import design.entity.CourseWithTeacher;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class CourseWithTeacherModel implements Models<CourseWithTeacher>{
    @Override
    public CourseWithTeacher getSpecificInstance() {
        return new CourseWithTeacher();
    }
}
