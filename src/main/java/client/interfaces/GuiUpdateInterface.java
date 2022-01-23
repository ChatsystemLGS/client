package client.interfaces;

import client.ProtocolException;
import client.ServerHandler;
import client.Session;

import java.sql.Timestamp;

@SuppressWarnings("unused")
public interface GuiUpdateInterface {

    ServerHandler sh = ServerHandler.sh;
    
    static String getPublicGroups() throws ProtocolException {
        return sh.execute(Session.Command.GETPUBLICGROUPS);

        /*
        required state          : AUTHENTICATED
        parameters              : none
        return value(s)         : channels:Channel[]
        potential status codes  : none
         */
    }
    
    static String getChannels() throws ProtocolException {
        return sh.execute(Session.Command.GETCHANNELS);

        /*
        required state          : AUTHENTICATED
        parameters              : none
        return value(s)         : channels:Channel[]
        potential status codes  : none
         */
    }
    
    static String getChannelMembers(int channelID) throws ProtocolException {
        return sh.execute(Session.Command.GETCHANNELMEMBERS, channelID);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer
        return value(s)         : users:User[]
        potential status codes  : CHANNEL_NOT_FOUND
         */
    }
    
    static String getUser(String emailAddress) throws ProtocolException {
        return sh.execute(Session.Command.GETUSER, emailAddress);

        /*
        required state          : AUTHENTICATED
        parameters              : userId:Integer / email:String
        return value(s)         : user:User
        potential status codes  : USER_NOT_FOUND
         */
    }

    static String getUser(int userID) throws ProtocolException {
        return sh.execute(Session.Command.GETUSER, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : userId:Integer / email:String
        return value(s)         : user:User
        potential status codes  : USER_NOT_FOUND
         */
    }

    static String getUserIdFromEmail(String userEmail) throws ProtocolException {
        return GuiUpdateInterface.getUser(userEmail);
    }

    static String getRelatedUsers() throws ProtocolException {
        return sh.execute(Session.Command.GETRELATEDUSERS);

        /*
        required state          : AUTHENTICATED
        parameters              : none
        return value(s)         : users:User[]
        potential status codes  : none
         */
    }
    
    static String receiveMessages(int channelID, Timestamp tFrom, Timestamp tUntil) throws ProtocolException {
        return sh.execute(Session.Command.RECEIVEMESSAGES, channelID, tFrom, tUntil);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer, tFrom:Timestamp, tUntil:Timestamp
        return value(s)         : messages:Message[]
        potential status codes  : CHANNEL_NOT_FOUND, TOO_MANY_MESSAGES
         */
    }
}
