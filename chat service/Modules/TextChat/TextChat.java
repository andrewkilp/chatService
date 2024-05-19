package Modules.TextChat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextField;

import Modules.Modules;

public class TextChat extends Modules{
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    JTextField chatBox;
    int channel;
    static int numChannel = 0;
    public TextChat() {
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200, 200));
        chatBox = new JTextField();
        chatBox.setPreferredSize(new Dimension(200, 200));
        chatBox.setSize(getPreferredSize());
        add(chatBox);
        setVisible(true);
        channel = numChannel++;
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
    public void initFunctionality() {
        
        new Thread(new ReceiveData()).start();
        chatBox.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    var data = new TextChatData(chatBox.getText(), channel);
                    sendData(data);
                    chatBox.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
            
        });
    }
    public class ReceiveData implements Runnable {
        @Override
        public void run() {

            while(true) {
                try {
                    if(in == null)
                        continue;
                    var data = in.readObject();
                    if(data == null) continue;
                    if(data instanceof TextChatData) {
                        sendData(new String("recived"));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("disconected");
                }
            }
        }
    }
}
