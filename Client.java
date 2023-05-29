import java.io.File;
import java.io.FileOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Client {
    private static RemoteServer remoteServer;
    private static JFrame frame;
    private static JTextField filenameField;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            remoteServer = (RemoteServer) registry.lookup("FileServer");

            frame = new JFrame("File Downloader");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            filenameField = new JTextField(20);
            JButton downloadButton = new JButton("Download");
            downloadButton.addActionListener(e -> downloadFile());

            panel.add(filenameField);
            panel.add(downloadButton);

            frame.getContentPane().add(panel);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile() {
        try {
            String filename = filenameField.getText();
            byte[] data = remoteServer.readFile(filename);
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
                JOptionPane.showMessageDialog(frame, "File saved successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Operation canceled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
