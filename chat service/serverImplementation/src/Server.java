import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    int portNum;
    ServerSocket aerverSocket = null;
    public Server(int portNum){
        try{
            aerverSocket = new ServerSocket(portNum);
            acceptClients();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    public void acceptClients() {
        while(true) {
            try{
                Socket clientAccept = aerverSocket.accept();
                ClientThread client = new ClientThread(clientAccept);
            }catch(IOException ex) {
                ex.printStackTrace();
            }

        }
    }
    public static void main(String[] args) throws Exception {
        new Server(5000);
    }
}
