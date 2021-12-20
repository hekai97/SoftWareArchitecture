package design.util;

import design.factory.ModelFactory;
import design.remoteapi.RemoteInterfaceSelect;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2021/12/20
 */
public class RemoteFunction2 {
    private final ConfigReader configReader=ConfigReader.getInstance();
    private final String host;
    private final int port;
    private Registry registry;
    private RemoteInterfaceSelect RemoteFunctions = null;
    public RemoteFunction2(){
        host=configReader.getKey("server_ip");
        port=Integer.parseInt(configReader.getKey("server_port"));
        try {
            registry= LocateRegistry.getRegistry("127.0.0.1",1227);
            RemoteFunctions = (RemoteInterfaceSelect) registry.lookup("selectSql");
            System.out.println("找到了1227");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
