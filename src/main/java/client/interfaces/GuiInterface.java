package client.interfaces;

import client.ProtocolException;

@SuppressWarnings("all")
public interface GuiInterface {

    String register(String emailAddress, String password, String nickname) throws ProtocolException;

    String login(String emailAddress, String password) throws ProtocolException;

    void joinGroup(int channelID);

    void addFriend(int userID);

    void sendMessage(int channelID, Byte[] data, Enum dataType);

    void quit();

}
