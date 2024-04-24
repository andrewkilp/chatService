import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClientLogIn extends JFrame {
    public ClientLogIn() {
        setName("client log in");
        setSize(250, 500);
        JTextField portNumberFeild = new JTextField("portNumber");
        portNumberFeild.setSize(150,100);
        add(portNumberFeild);

        setVisible(true);
        setLayout(getLayout());
        
    }
}
