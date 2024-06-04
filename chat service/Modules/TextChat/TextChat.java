package Modules.TextChat;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.Client;
import Modules.Module;;

public class TextChat extends Module{
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    JTextField chatBox;
    JTextArea messages;
    JScrollPane messageScrollBox;
    int channel;
    String name;
    Client client = null;
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
        //TODO add a send button and a client name so message like user:message
    }
    @Override
    public void connectToClient(Client client) {
        this.client = client;
    }
    @Override
    public void sendData(Object data) {
        client.sendData(data);
    }
    @Override
    public void initFunctionality() {
        chatBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
            
        });
    }
    private void sendMessage(){
        Object data = new TextChatData(name, chatBox.getText(), channel);
        sendData(data);
        chatBox.setText("");
    }
    @Override
    public void receiveData(Object o) {
        if(o == null) return;
        if(o instanceof TextChatData) {
            TextChatData txtData = (TextChatData) o;
            if(txtData.channel == channel){
                messages.append(txtData.data);
            }
        }
    }
}
