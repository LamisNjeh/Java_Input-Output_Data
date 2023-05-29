import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServer extends UnicastRemoteObject implements MyRemoteInterface {
    private static final long serialVersionUID = 1L;

    protected RemoteServer() throws RemoteException {
    }


    @Override
    public byte[] readFile(String filename) throws RemoteException {
        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            return data;
        } catch (IOException e) {
            throw new RemoteException("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void writeFile(String filename, byte[] data) throws RemoteException {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            throw new RemoteException("Error writing to file: " + e.getMessage());
        }
    }
}
