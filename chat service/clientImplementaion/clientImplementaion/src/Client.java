import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import javax.swing.JFrame;

public class Client {
    JFrame UI;
    SocketChannel clientChannel;
    Selector selector;
    public Client(String host, int portNum) throws IOException {
        clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false); // Set non-blocking mode
        clientChannel.connect(new InetSocketAddress(host, portNum));

        selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_CONNECT);
    }
    public static void main(String[] args) {
        new ClientLogIn();
    }
}
