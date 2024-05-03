import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        System.out.println("called");
        try{
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(Server.serverInstance.components);
            out.flush();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        while(true) {
            System.out.println("conSti");
            try {
                System.out.println(clientSocket.getInputStream().read());
            } catch(SocketException e) {
                System.out.println("disconnected");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
