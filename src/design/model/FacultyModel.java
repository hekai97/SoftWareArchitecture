package design.model;

import design.entity.Faculty;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class FacultyModel implements Models<Faculty> {
    @Override
    public Faculty getSpecificInstance() {
        return new Faculty();
    }
}
