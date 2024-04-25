import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel usernameLabel = new JLabel("Server IP:");
        panel.add(usernameLabel);
        serverFeild = new JTextField();
        panel.add(serverFeild);
        JLabel passwordLabel = new JLabel("Port:");
        panel.add(passwordLabel);
        portFeild = new JTextField();
        panel.add(portFeild);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String server = serverFeild.getText();
                String port = portFeild.getText();
                int portnum = Integer.parseInt(port);
                new Client(server, portnum);
                System.out.println("connected!");
            }
        });
        panel.add(loginButton);
        add(panel);
        setVisible(true);
        setSize(getPreferredSize());
    }
}
