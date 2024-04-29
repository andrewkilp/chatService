import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientInterface extends JFrame  {
    @SuppressWarnings({ "null", "unchecked" })
    public ClientInterface() {
        ObjectInputStream modsList = null;
        List<Component> mods = new ArrayList<Component>();
        JPanel panel = new JPanel();
        try {
            mods = (List<Component>) modsList.readObject();
            for(Component comp: mods) {
                panel.add(comp);
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
