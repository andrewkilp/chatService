package Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modules.Module;

public class ClientInterface extends JFrame  {
    @SuppressWarnings({ "unchecked" })
    public ClientInterface() {
        JPanel panel = new JPanel();
        setVisible(true);
        try {
            List<Module> mods = new ArrayList<Module>();
            mods = (List<Module>) Client.instance.inputStream.readObject();
            System.out.println(mods);
            for(Module comp: mods) {
                panel.add(comp);
                comp.connectToClient(Client.instance);
                // comp.connectToInputStream(Client.instance.inputStream);
                // comp.connectToOutputStream(Client.instance.outputStream);
                
            }
            add(panel);
            pack();
            for(Module comp:mods)
                comp.initFunctionality();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
