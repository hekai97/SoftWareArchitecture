package design.server;

import design.factory.ModelFactory;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2021/11/27
 */
public interface Repository<E> {
    public List<E> getResult(ModelFactory factory,String sql);
}