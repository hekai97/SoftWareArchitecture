package design.factory;

import design.model.Models;
import design.model.TeachertModel;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class TeacherFactory extends ModelFactory{
    @Override
    public Models getModel() {
        return new TeachertModel();
    }
}
