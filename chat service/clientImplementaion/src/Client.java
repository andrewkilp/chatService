import java.io.BufferedReader;
import java.io.IOException;
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
    public Client( int portNum){
        try {
            connection = new Socket("localhost", portNum);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client a = new Client(5000);
    }
}
