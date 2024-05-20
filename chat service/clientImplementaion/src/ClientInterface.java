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
        setVisible(true);
        ObjectInputStream modsList = null;
        List<Modules> mods = new ArrayList<Modules>();
        try {
            modsList = new ObjectInputStream(Client.instance.inputStream);
            mods = (List<Modules>)modsList.readObject();
            System.out.println(mods);
            for(Modules comp: mods) {
                panel.add(comp);
                comp.connectToInputStream(Client.instance.inputStream);
                comp.connectToOutputStream(Client.instance.outputStream);
                comp.initFunctionality();

            }
            add(panel);
            pack();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
