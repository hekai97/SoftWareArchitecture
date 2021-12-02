package design.factory;

import design.model.GradeModel;
import design.model.Models;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class GradeFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new GradeModel();
    }
}
