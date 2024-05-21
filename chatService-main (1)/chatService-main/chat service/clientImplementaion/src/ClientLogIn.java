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

public class ClientLogIn extends JFrame {
    private JTextField serverFeild,portFeild;
    private JButton loginButton;
    public ClientLogIn() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        JLabel serverIP = new JLabel("Server IP:");
        panel.add(serverIP);
        serverFeild = new JTextField();
        panel.add(serverFeild);
        JLabel portNumber = new JLabel("Port:");
        panel.add(portNumber);
        portFeild = new JTextField();
        panel.add(portFeild);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean connected = false;
                try {
                    String server = serverFeild.getText();
                    String port = portFeild.getText();
                    int portnum = Integer.parseInt(port);
                    Client.instance = new Client(server, portnum);
                    connected = true;
                } catch(Exception ex) {
                    System.out.println("failed to connect");
                }
                if(connected) {
                    System.out.println("connected!");
                    setVisible(false);
                    new ClientInterface();
                }
            }
        });
        portFeild.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    boolean online = false;
                    try {
                        String server = serverFeild.getText();
                        String port = portFeild.getText();
                        int portnum = Integer.parseInt(port);
                        Client.instance = new Client(server, portnum);
                        online = true;
                    } catch(Exception ex) {
                        System.out.println("failed to connect");
                    }
                    if(online) {
                        System.out.println("connected!");
                        setVisible(false);
                        new ClientInterface();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        serverFeild.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10) {
                    boolean online = false;
                    try {
                        String server = serverFeild.getText();
                        String port = portFeild.getText();
                        int portnum = Integer.parseInt(port);
                        Client.instance = new Client(server, portnum);
                        online = true;
                    } catch(Exception ex) {
                        System.out.println("failed to connect");
                    }
                    if(online) {
                        System.out.println("connected!");
                        setVisible(false);
                        new ClientInterface();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        panel.add(loginButton);
        add(panel);
        setVisible(true);
        setSize(getPreferredSize());
    }
}
