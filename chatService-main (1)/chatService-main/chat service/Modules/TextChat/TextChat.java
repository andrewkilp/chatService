package Modules.TextChat;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Modules.Modules;

public class TextChat extends Modules{
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    JTextField chatBox;
    JTextArea messages;
    JScrollPane messageScrollBox;
    int channel;
    static int numChannel = 0;
    public TextChat() {
        setLayout(new GridLayout(2, 1));
        messages = new JTextArea();
        messages.setEditable(false);
        messageScrollBox = new JScrollPane(messages);
        messageScrollBox.setPreferredSize(new Dimension(200,300));
        messageScrollBox.setSize(200,300);
        messageScrollBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(messageScrollBox);
        chatBox = new JTextField();
        chatBox.setEditable(true);
        chatBox.setPreferredSize(new Dimension(200, 50));
        chatBox.setSize(200,50);
        add(chatBox);
        setSize(new Dimension(messageScrollBox.getWidth(), messageScrollBox.getHeight() + chatBox.getHeight()));
        setPreferredSize(getSize());
        setVisible(true);
        channel = numChannel++;
    }
    
    @Override
    public void connectToOutputStream(OutputStream out) throws IOException {
        this.out = new ObjectOutputStream(out);
    }
    @Override
    public void connectToInputStream(InputStream in) throws IOException {
        this.in =new ObjectInputStream(in);
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
                    Object data = new TextChatData(chatBox.getText(), channel);
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
                    Object data = in.readObject();
                    if(data == null) continue;
                    if(data instanceof TextChatData) {
                        TextChatData txtData = (TextChatData) data;
                        if(txtData.channel == channel)
                            messages.append(txtData.data);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("disconected");
                    break;
                }
            }
        }
    }
}
