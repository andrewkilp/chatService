package Modules.TextChat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextField;

import Modules.Modules;

public class TextChat extends Modules{
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    JTextField chatBox;
    public TextChat() {
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200, 200));
        chatBox = new JTextField();
        chatBox.setPreferredSize(new Dimension(200, 200));
        chatBox.setSize(getPreferredSize());
        add(chatBox);
        setVisible(true);
    }
    
    @Override
    public void connectToOutputStream(ObjectOutputStream out) {
        this.out = out;
    }
    @Override
    public void connectToInputStream(ObjectInputStream in) {
        this.in = in;
    }
    @Override
    public void sendData(Object data) {
        try{
            out.writeObject(data);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void receiveData() {
        try{
            in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initFunctionality() {
        chatBox.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendData(chatBox.getText());
                    chatBox.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
            
        });
    }
    public class receiveData implements Runnable {
        @Override
        public void run() {
            while(true) {
                String string;
                List<Object> byteData = Collections.synchronizedList(new ArrayList<>());
                while (true) {
                    try {
                        byteData.add((byte) in.read());
                    } catch (IOException ex) {
                        break;
                    }
                }
                byte[] data = new byte[byteData.size()];
                int index = 0;
                for (Object byteToUse : byteData) {
                    data[index++] = (byte) byteToUse;
                }
                string = new String(data, StandardCharsets.UTF_8);
                System.out.println(string);
                try {
                    out.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
