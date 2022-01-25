package client.interfaces;

import client.ProtocolException;
import client.db.Message;
import client.db.User;

import java.sql.Timestamp;

//! concept... not working just an idea
public class MessagesUpdater implements Runnable {

    User u;
    Message m;

    public MessagesUpdater(User u, Message m) {
        this.u = u;
        this.m = m;
    }

    @Override
    public void run() {
        int channelID = m.getChannel();
        Timestamp tFrom = new Timestamp(System.currentTimeMillis() - 86400000); // - 1 day
        Timestamp tUtil = new Timestamp(System.currentTimeMillis());

        String response;
        try {
            response = GuiUpdateInterface.receiveMessages(channelID, tFrom, tUtil);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        // TODO: parse recieved Messages into Objects
    }
}
