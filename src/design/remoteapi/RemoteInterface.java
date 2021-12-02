package design.remoteapi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: hekai
 * @Date: 2021/12/1
 */
public interface RemoteInterface extends Remote {
    public void execute(String sql) throws RemoteException, SQLException;
    public ResultSet getResultSet(String sql) throws RemoteException,SQLException;
}
