package Modules.TextChat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
            
        });
    }

    
}
