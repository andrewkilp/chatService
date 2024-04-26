package Modules;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class TextChat extends JComponent{
    public TextChat() {
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200, 200));
        add(new JTextArea());
        setVisible(true);
    }
}
