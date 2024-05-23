package Client;
import java.io.IOException;
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
    public Socket getSocket() {
        return connection;
    }
    public void sendData(Object o) {
        try{
            outputStream.writeObject(o);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new ClientLogIn();
    }

}
