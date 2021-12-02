package design.model;

import design.entity.Teacher;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class TeachertModel implements Models<Teacher> {
    @Override
    public Teacher getSpecificInstance() {
        return new Teacher();
    }
}
