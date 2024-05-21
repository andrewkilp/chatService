package Modules;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JComponent;

public abstract class Modules extends JComponent {
    public abstract void connectToOutputStream(OutputStream out)throws IOException;
    public abstract void connectToInputStream(InputStream in) throws IOException;
    public abstract void sendData(Object data);
    public abstract void initFunctionality();
}
