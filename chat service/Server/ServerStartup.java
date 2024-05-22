package Server;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerStartup extends JFrame{
    private JTextField portFeild;
    private JButton startServer;
    public ServerStartup() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        JLabel portNumber = new JLabel("Port:");
        panel.add(portNumber);
        portFeild = new JTextField();
        panel.add(portFeild);
        startServer = new JButton("start Server");
        startServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean online = false;
                try {
                    String port = portFeild.getText();
                    int portnum = Integer.parseInt(port);
                    Server.serverInstance = new Server(portnum);
                    online = true;
                } catch(Exception ex) {
                    System.out.println("failed to start");
                }
                if(online) {
                    System.out.println("Started Sucessfully!");
                    setVisible(false);
                    new ServerClient();
                }
            }
        });
        portFeild.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boolean online = false;
                    try {
                        String port = portFeild.getText();
                        int portnum = Integer.parseInt(port);
                        Server.serverInstance = new Server(portnum);
                        online = true;
                    } catch(Exception ex) {
                        System.out.println("failed to start");
                        ex.printStackTrace();
                    }
                    if(online) {
                        System.out.println("Started Sucessfully!");
                        setVisible(false);
                        new ServerClient();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        panel.add(startServer);
        add(panel);
        setVisible(true);
        setSize(getPreferredSize());
    }
}
