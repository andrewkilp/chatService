package Modules;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JComponent;

public abstract class Modules extends JComponent {
    public abstract void connectToOutputStream(ObjectOutputStream out);
    public abstract void connectToInputStream(ObjectInputStream in);
    public abstract void sendData(Object data);
    public abstract void initFunctionality();
}
