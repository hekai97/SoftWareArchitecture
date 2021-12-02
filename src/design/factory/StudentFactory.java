package design.factory;

import design.model.Models;
import design.model.StudentModel;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class StudentFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new StudentModel();
    }
}
