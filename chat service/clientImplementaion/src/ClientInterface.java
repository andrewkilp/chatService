import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modules.Modules;

public class ClientInterface extends JFrame  {
    @SuppressWarnings({ "unchecked" })
    public ClientInterface() {
        JPanel panel = new JPanel();
        try {
            ObjectInputStream modsList = new ObjectInputStream(Client.instance.inputStream);
            List<Modules> mods = new ArrayList<Modules>();
            mods = (List<Modules>) modsList.readObject();
            for(Modules comp: mods) {
                panel.add(comp);
                comp.connectToInputStream(Client.instance.inputStream);
                comp.connectToOutputStream(Client.instance.outputStream);
                comp.sendData(new byte[]{12,42});
            }
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
