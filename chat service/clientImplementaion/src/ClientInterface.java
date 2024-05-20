import java.io.IOException;
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
        try {
            List<Modules> mods = new ArrayList<Modules>();
            mods = (List<Modules>) Client.instance.inputStream.readObject();
            System.out.println(mods);
            for(Modules comp: mods) {
                panel.add(comp);
                comp.connectToInputStream(Client.instance.inputStream);
                comp.connectToOutputStream(Client.instance.outputStream);
                
            }
            add(panel);
            pack();
            for(Modules comp:mods)
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
