import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server {
    public static void main(String[] args) {
        try {
            RemoteServer fileServer = new RemoteServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FileServer", fileServer);
            System.out.println("File server is running...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } 
    }
}
