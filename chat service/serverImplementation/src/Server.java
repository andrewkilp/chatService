import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Server {
    int portNum;
    ServerSocket aerverSocket = null;
    public Server(int portNum) {
        try{
            aerverSocket = new ServerSocket(portNum);
            acceptClients.start();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(portNum);
    }
    ArrayList<ClientThread> currentClients = new ArrayList<>();
    Thread acceptClients = new Thread(()->{
        while(true) {
            try{
                System.out.println("listinging");
                Socket clientAccept = aerverSocket.accept();
                ClientThread client = new ClientThread(clientAccept);
                Thread cliThread = new Thread(client);
                cliThread.start();
                currentClients.add(client);
            }catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    });
    public static void main(String[] args) throws Exception {
        new ServerStartup();
    }
}
