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
            Vector<Module> mods = new Vector<Module>();
            mods = (Vector<Module>) Client.instance.inputStream.readObject();
            System.out.println(mods);
            for(Module comp: mods) {
                panel.add(comp);
                comp.connectToClient(Client.instance);
            }
            add(panel);
            pack();
            receiveData(mods);
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
    public void receiveData(Vector<Module> mods) {
        new Thread(()->{
            while (true) {
                try {
                    Object o = (Object) Client.instance.inputStream.readObject();
                    for (Module module : mods) {
                        module.receiveData(o);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();;

    }
}
