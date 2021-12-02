package design.model;

import design.entity.Course;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class CourseModel implements Models<Course> {
    @Override
    public Course getSpecificInstance() {
        return new Course();
    }
}
