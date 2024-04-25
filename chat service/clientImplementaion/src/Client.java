import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Client {
    JFrame UI;
    Socket connection;
    ServerSocket server = null;
    BufferedReader in = null;
    PrintWriter out = null;
    public Client(String host, int portNum) throws Exception{
        connection = new Socket(host, portNum);
    }

    public static void main(String[] args) {
        new ClientLogIn();
    }
}
