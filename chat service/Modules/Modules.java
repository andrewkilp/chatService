package Modules;

import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JComponent;

public abstract class Modules extends JComponent {
    public abstract void connectToOutputStream(OutputStream out);
    public abstract void connectToInputStream(InputStream in);
    public abstract void sendData(byte[] data);
    public abstract void receiveData(int bytesToRead);
}
