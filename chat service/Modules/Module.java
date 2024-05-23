package Modules;

import javax.swing.JComponent;

import Client.Client;

public abstract class Module extends JComponent {
    public abstract void sendData(Object data);
    public abstract void initFunctionality();
    public abstract void connectToClient(Client client);
    public abstract void receiveData(Object o);
}
