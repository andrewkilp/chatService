import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modules.Modules;
import Modules.TextChat.TextChat;

public class ClientInterface extends JFrame  {
    @SuppressWarnings({ "unchecked" })
    public ClientInterface() {
        JPanel panel = new JPanel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024); 
            SocketChannel clientChannel = Client.instance.getClientChannel(); 

           
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip(); 
                List<Modules> mods = new ArrayList<>();
                while (buffer.hasRemaining()) {
                    Modules module = (Modules) Serializer.deserialize(buffer.array());
                    mods.add(module);
                }
            for(Modules comp: mods) {
                panel.add(comp);
                comp.connectToInputStream(Client.instance.inputStream);
                comp.connectToOutputStream(Client.instance.outputStream);
                comp.initFunctionality();
            }
            panel.add(new TextChat());
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        add(panel);
        setVisible(true);
        setSize(getPreferredSize());
    }
}
}
