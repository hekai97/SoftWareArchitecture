package design.factory;

import design.model.Models;
import design.model.StudentGradeModel;

/**
 * @author: hekai
 * @Date: 2021/11/28
 */
public class StudentGradeFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new StudentGradeModel();
    }
}
