package client;

import client.Session.Command;
import client.db.Channel;
import client.db.Message;

import java.sql.Timestamp;

@SuppressWarnings("all")
public class CommandController {

    public void register(String emailAddress, String password, String nickname) throws ProtocolException {
        ServerHandler.execute(Command.REGISTER, emailAddress, password, nickname);
    }

    public void login(String emailAddress, String password) throws ProtocolException {
        ServerHandler.execute(Command.LOGIN, emailAddress, password);
    }

    // public Channel[] getPublicGroups() {
    //     Channel[] publicGroups = ServerHandler.execute();
    //     return publicGroups;
    // }

    public void joinGroup(int channelID) {

    }

    // public Channel[] getChannels() {
    //     Channel[] channels = null;
    //     return channels;
    // }

    // public User[] getChannelMembers(int channelID) {
    //     User[] users = null;
    //     return users;
    // }

    public User getUser(int userID, String emailAddress) {
        User user = null;
        return user;
    }

    public void addFriend(int userID) {

    }

    // public User[] getFriends() {
    //     User[] users = null;
    //     return users;
    // }

    public void sendMessage(int channelID, String message) throws ProtocolException {
        ServerHandler.execute(Command.SENDMESSAGE, channelID, message, Message.DataType.TEXT);
    }

    public Channel createDM(int userID) {
        Channel channel = null;
        return channel;
    }

    public Message[] recieveMessages(int channelID, Timestamp tFrom, Timestamp tUntil) {
        Message[] messages = null;
        return messages;
    }

    public static void quit() throws ProtocolException {
        ServerHandler.execute(Command.QUIT);
    }
}