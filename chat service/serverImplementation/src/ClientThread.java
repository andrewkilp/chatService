import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
    }
}
