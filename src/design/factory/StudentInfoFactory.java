package design.factory;

import design.model.Models;
import design.model.StudentInfoModel;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class StudentInfoFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new StudentInfoModel();
    }
}
