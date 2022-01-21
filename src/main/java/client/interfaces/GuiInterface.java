package client.interfaces;

import client.ProtocolException;

@SuppressWarnings("all")
public interface GuiInterface {

    void register(String emailAddress, String password, String nickname);

    boolean login(String emailAddress, String password) throws ProtocolException;

    void joinGroup(int channelID);

    void addFriend(int userID);

    void sendMessage(int channelID, Byte[] data, Enum dataType);

    void quit();

}
