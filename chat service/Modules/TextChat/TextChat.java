package Modules.TextChat;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JTextArea;

import Modules.Modules;

public class TextChat extends Modules{
    OutputStream out = null;
    InputStream in = null;
    public TextChat() {
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200, 200));
        add(new JTextArea());
        
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
    public void receiveData(int bytesToRead) {
        try{
            byte[] data = new byte[bytesToRead];
            in.read(data);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
}
