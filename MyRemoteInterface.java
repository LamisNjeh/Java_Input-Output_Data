import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteInterface extends Remote {
    // Method to read a file
    public byte[] readFile(String filename) throws RemoteException;

    // Method to write to a file
    public void writeFile(String filename, byte[] data) throws RemoteException;
}
