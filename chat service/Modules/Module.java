package Modules;

import javax.swing.JComponent;

import Client.Client;

public abstract class Module extends JComponent {
    public abstract void sendData(Object data); // soon to be depreciated
    public abstract void initFunctionality(); // soon to be depricated
    public abstract void connectToClient(Client client);
    public abstract void receiveData(Object o);
}
