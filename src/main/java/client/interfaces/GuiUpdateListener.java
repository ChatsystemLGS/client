package client.interfaces;

import client.db.Channel;
import client.db.Message;
import client.db.User;

import java.sql.Timestamp;

@SuppressWarnings("all")
public interface GuiUpdateListener {

    Channel[] getPublicGroups();

    Channel[] getChannels();

    User[] getChannelMembers(int channelID);

    User getUser(int userID, String emailAddress);

    User[] getFriends();

    Channel create(int userID);

    Message[] recieveMssages(int channelID, Timestamp tFrom, Timestamp tUntil);

}
