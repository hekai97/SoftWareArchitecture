package design.factory;

import design.model.CourseWithTeacherModel;
import design.model.Models;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class CourseWithTeacherFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new CourseWithTeacherModel();
    }
}
