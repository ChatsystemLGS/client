package client;

import java.sql.Timestamp;

import client.db.Channel;
import client.db.Message;

@SuppressWarnings("all")
public interface GuiInterface {

    public void register(String emailAddress, String password, String nickname);

    public void login(String emailAddress, String password);

    public Channel[] getPublicGroups();

    public void joinGroup(int channelID);

    public Channel[] getChannels();

    public User[] getChannelMembers(int channelID);

    public User getUser(int userID, String emailAddress);

    public void addFriend(int userID);

    public User[] getFriends();

    public void sendMessage(int channelID, Byte[] data, Enum dataType);

    public Channel createDM(int userID);

    public Message[] recieveMessages(int channelID, Timestamp tFrom, Timestamp tUntil);

    public void quit();

}
