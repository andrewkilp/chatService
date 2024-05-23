package Client;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modules.Module;

public class ClientInterface extends JFrame  {
    @SuppressWarnings({ "unchecked" })
    public ClientInterface() {
        JPanel panel = new JPanel();
        setVisible(true);
        try {
            Vector<Module> mods = new Vector<>();
            mods = (Vector<Module>) Client.instance.inputStream.readObject();
            System.out.println(mods);
            for(Module comp: mods) {
                panel.add(comp);
                comp.connectToClient(Client.instance);
            }
            add(panel);
            pack();
            for(Module comp:mods)
                comp.initFunctionality();
            Client.instance.startReceivingData(mods);
            } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
