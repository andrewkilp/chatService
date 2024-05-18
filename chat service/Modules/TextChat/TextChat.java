package Modules.TextChat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextField;

import Modules.Modules;

public class TextChat extends Modules{
    OutputStream out = null;
    InputStream in = null;
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
    public void connectToOutputStream(OutputStream out) {
        this.out = out;
    }
    @Override
    public void connectToInputStream(InputStream in) {
        this.in = in;
    }
    @Override
    public void sendData(byte[] data) {
        try{
            out.write(data);
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
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String data = chatBox.getText();
                    byte[] dataToSend = new byte[data.length()*2];
                    for(int i = 0; i<data.length(); i++) {
                        char c = data.charAt(i);
                        if(i==0) {
                            dataToSend[i] = (byte) ((byte) c&0xFF);
                            dataToSend[i+1] = (byte) ((byte) (c>>8)&0xFF);
                            continue;
                        }
                        dataToSend[i*2] = (byte) ((byte) c&0xFF);
                        dataToSend[(i*2) +1] = (byte) ((byte) (c>>8)&0xFF);
                        
                    }
                    sendData(dataToSend);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
            
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
