package design.factory;

import design.model.CourseModel;
import design.model.Models;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class CourseFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new CourseModel();
    }
}
