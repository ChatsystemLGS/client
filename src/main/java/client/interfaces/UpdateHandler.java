package client.interfaces;

import client.db.Channel;
import client.db.Message;
import client.db.User;

import java.sql.Timestamp;

@SuppressWarnings("all")
public class UpdateHandler implements GuiUpdateListener {

    @Override
    public Channel[] getPublicGroups() {
        return new Channel[0];
    }

    @Override
    public Channel[] getChannels() {
        return new Channel[0];
    }

    @Override
    public User[] getChannelMembers(int channelID) {
        return new User[0];
    }

    @Override
    public User getUser(int userID, String emailAddress) {
        return null;
    }

    @Override
    public User[] getFriends() {
        return new User[0];
    }

    @Override
    public Channel create(int userID) {
        return null;
    }

    @Override
    public Message[] recieveMssages(int channelID, Timestamp tFrom, Timestamp tUntil) {
        return new Message[0];
    }
}
