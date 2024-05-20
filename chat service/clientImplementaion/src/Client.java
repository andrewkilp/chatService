import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Client {
    JFrame UI;
    Socket connection;
    ServerSocket server = null;
    ObjectOutputStream  outputStream = null;
    ObjectInputStream inputStream = null;
    public static Client instance;
    public Client(String host, int portNum) throws Exception{
        connection = new Socket(host, portNum);
        outputStream = new ObjectOutputStream(connection.getOutputStream());
        inputStream = new ObjectInputStream(connection.getInputStream());
    }
    public static void main(String[] args) {
        new ClientLogIn();
    }
}
