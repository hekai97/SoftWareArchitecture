package design.util;

import design.remoteapi.RemoteInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: hekai
 * @Date: 2021/12/1
 */
public class RemoteFunction {
    private final ConfigReader configReader=ConfigReader.getInstance();
    private final String host;
    private final int port;
    private Registry registry;
    private RemoteInterface RemoteFunctions = null;
    public RemoteFunction(){
        host=configReader.getKey("server_ip");
        port=Integer.parseInt(configReader.getKey("server_port"));
        try {
            registry=LocateRegistry.getRegistry(host,port);
            RemoteFunctions = (RemoteInterface) registry.lookup("executeSql");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
    public void execute(String sql) throws SQLException{
        try {
            RemoteFunctions.execute(sql);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getResult(String sql) throws SQLException{
        ResultSet resultSet=null;
        try {
            resultSet = RemoteFunctions.getResultSet(sql);
        } catch (RemoteException e) {
            System.out.println("远程调用失败");
        }
        return resultSet;
    }
    public int verify(int id,String user,String password) throws RemoteException{
        return RemoteFunctions.verifyUserPassword(id,user,password);
    }
}
