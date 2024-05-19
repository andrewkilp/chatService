package Modules.TextChat;

import java.io.Serializable;

public class TextChatData implements Serializable{
    String data;
    int channel;
    public TextChatData(String data, int channel) {
        this.data = data;
        this.channel = channel;
    }
    @Override
    public String toString() { // for debugging purposes
        return String.format("TXTC:%d DATA:%s ", channel, data);
    }
}
