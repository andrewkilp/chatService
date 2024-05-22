package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import Modules.Module;
import Modules.TextChat.TextChat;
public class Server {
    int portNum;
    ServerSocket serverSocket = null;
    Vector<Module> components = new Vector<>();
    public static Server serverInstance;
    public Server(int portNum) {
        components.add(new TextChat());
        components.add(new TextChat());
        components.add(new TextChat());

        try{
            serverSocket = new ServerSocket(portNum);
            acceptClients.start();
            System.out.println(serverSocket);
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
                    Socket clientAccept = serverSocket.accept();
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
    public void sendData(Object data) throws IOException {
        for(ClientThread client: Server.serverInstance.currentClients){
            ObjectOutputStream sendTo = client.getOutputStream();
            sendTo.writeObject(data);
        }
    }
    public static void main(String[] args) throws Exception {
        new ServerStartup();
    }
}
