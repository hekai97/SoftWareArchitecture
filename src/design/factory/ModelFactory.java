package design.factory;

import design.model.Models;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public abstract class ModelFactory {
    private Object instance;
    public ModelFactory(){}
    public abstract Models getModel();
    public Object getInstance(){
        this.instance=getModel().getSpecificInstance();
        return this.instance;
    }
}
