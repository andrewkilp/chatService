import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ClientThread implements Runnable {
    private SocketChannel clientChannel;
    private ArrayBlockingQueue<byte[]> messageQueue; 
    public ClientThread(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
        this.messageQueue = new ArrayBlockingQueue<>(10); 
    }
    @Override
    public void run() {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(Server.serverInstance.components));
            clientChannel.write(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while (true) {
            try {
                int bytesRead = clientChannel.read(readBuffer);
                if (bytesRead > 0) {
                    readBuffer.flip(); 
                    byte[] data = new byte[readBuffer.remaining()];
                    readBuffer.get(data); 
                    messageQueue.put(data);
                    readBuffer.clear();
                }
                byte[] outgoingMessage = messageQueue.poll();
                if (outgoingMessage != null) {
                    ByteBuffer writeBuffer = ByteBuffer.wrap(outgoingMessage);
                    clientChannel.write(writeBuffer);
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Client disconnected.");
                break;
            }
        }
    }
}