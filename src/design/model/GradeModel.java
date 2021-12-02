package design.model;

import design.entity.Grade;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public class GradeModel implements Models<Grade> {
    @Override
    public Grade getSpecificInstance() {
        return new Grade();
    }
}
