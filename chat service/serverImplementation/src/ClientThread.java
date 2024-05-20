import java.io.IOException;
import java.io.ObjectInputStream;
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
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try{
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            out.writeObject(Server.serverInstance.components);
            out.flush();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        while(true) {
            try {
                Object data = in.readObject();
                System.out.println(data);
                Server.serverInstance.sendData(data);
            } catch(SocketException e) {
                e.printStackTrace();
                System.out.println("disconnected");
                break;
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public ObjectOutputStream getOutputStream() throws IOException {
        return new ObjectOutputStream(clientSocket.getOutputStream());
    }
}
