import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ServerClient extends JFrame{
    public ServerClient() {
        
        JPanel panel = new JPanel();
        JLabel serverHeader = new JLabel("Edit Configuriation");
        serverHeader.setFont(new Font("SERIF", Font.BOLD, 22));
        serverHeader.setSize(125,100);
        serverHeader.setPreferredSize(serverHeader.getSize());
        panel.add(serverHeader);
        JLabel activeMods = new JLabel("Current Mods");
        JLabel nonActiveMode = new JLabel("Non active mods");
        panel.add(activeMods);
        panel.add(nonActiveMode);
        JScrollPane listOfActiveMods;
        JScrollPane listOfNonActiveMods;
        add(panel);
        pack();
        setVisible(true);
        
    }
}