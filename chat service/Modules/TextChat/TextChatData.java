package Modules.TextChat;

import java.io.Serializable;

public class TextChatData implements Serializable{
    String username;
    String data;
    int channel;
    public TextChatData(String username, String data, int channel) {
        this.username =username;  // planned to do for custom coloring and stuff idk yet
        this.data = String.format("%s:%n%s%n", username, data);
        this.channel = channel;
    }
    @Override
    public String toString() { // for debugging purposes
        return String.format("TXTC:%d DATA:%s ", channel, data);
    }
}
