import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import Modules.Modules;
import Modules.TextChat.TextChat;
public class Server {
    int portNum;
    ServerSocket aerverSocket = null;
    Vector<Modules> components = new Vector<>();
    public static Server serverInstance;
    public Server(int portNum) {
        components.add(new TextChat());
        try{
            aerverSocket = new ServerSocket(portNum);
            acceptClients.start();
            System.out.println(aerverSocket);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    ArrayList<ClientThread> currentClients = new ArrayList<>();
    boolean hasAcceptingClientsBeenCalled = false;
    Thread acceptClients = new Thread(()->{
        if(!hasAcceptingClientsBeenCalled) {
            while(true) {
                try{
                    Thread.sleep(1);
                    Socket clientAccept = aerverSocket.accept();
                    ClientThread client = new ClientThread(clientAccept);
                    Thread cliThread = new Thread(client);
                    cliThread.start();
                    currentClients.add(client);
                }catch(IOException ex) {
                    ex.printStackTrace();
                } catch(InterruptedException interruptedException) {
                    break;
                }
            }
        }
    });
    public static void main(String[] args) throws Exception {
        new ServerStartup();
    }
}
