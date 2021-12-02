package design.factory;

import design.model.Models;
import design.model.StudentGradeForTeacherModel;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class StudentGradeForTeacherFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new StudentGradeForTeacherModel();
    }
}
