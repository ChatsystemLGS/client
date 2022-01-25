package client.interfaces;

import client.ProtocolException;
import client.ServerHandler;
import client.Session;

@SuppressWarnings("unused")
public interface GuiInterface {

    ServerHandler sh = ServerHandler.sh;

    static String register(String emailAddress, String password, String nickname) throws ProtocolException {
        return sh.execute(Session.Command.REGISTER, emailAddress, nickname, password);

        /*
        required state          : CONNECTED
        parameters              : emailAddress:String, nickname:String, password:String
        return value(s)         : none
        potential status codes  : EMAIL_ALREADY_REGISTERED, PASSWORD_REQ_NOT_MET
         */
    }

    static String login(String emailAddress, String password) throws ProtocolException {
        return sh.execute(Session.Command.LOGIN, emailAddress, password);

        /*
        required state          : CONNECTED
        parameters              : emailAddress:String, password:String
        return value(s)         : id:Integer
        potential status codes  : EMAIL_NOT_REGISTERED, PASSWORD_INVALID
         */
    }

    static String joinGroup(int channelID) throws ProtocolException { // TODO: not implemented in GUI yet
        return sh.execute(Session.Command.JOINGROUP, channelID);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer
        return value(s)         : none
        potential status codes  : CHANNEL_NOT_FOUND
         */
    }

    static String createGoup(String channelName) throws ProtocolException {
        return sh.execute(Session.Command.CREATEGROUP, channelName);

        /*
        required state          : AUTHENTICATED
        parameters              : channelName:String
        return value(s)         : channelId:Integer
        potential status codes  : none
         */
    }

    static String addToGroup(int channelID, int userID) throws ProtocolException {
        return sh.execute(Session.Command.ADDTOGROUP, channelID, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer, userId:Integer
        return value(s)         : none
        potential status codes  : CHANNEL_NOT_FOUND, USER_NOT_FOUND, NO_PERMISSION
         */
    }

    static String removeFromGroup(int channelID, int userID) throws ProtocolException {
        return sh.execute(Session.Command.REMOVEFROMGROUP, channelID, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer, userId:Integer
        return value(s)         : none
        potential status codes  : CHANNEL_NOT_FOUND, USER_NOT_FOUND, NO_PERMISSION
         */
    }

    static String leaveGroup(int channelID, int userID) throws ProtocolException {
        return sh.execute(Session.Command.LEAVEGROUP, channelID, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer
        return value(s)         : none
        potential status codes  : CHANNEL_NOT_FOUND
         */
    }

    static String createDM(int userID) throws ProtocolException {
        return sh.execute(Session.Command.CREATEDM, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : userId:Integer
        return value(s)         : channelId:Integer
        potential status codes  : USER_NOT_FOUND
         */
    }

    static String addFriend(int userID) throws ProtocolException {
        return sh.execute(Session.Command.ADDFRIEND, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : userId:Integer
        return value(s)         : none
        potential status codes  : USER_NOT_FOUND
         */
    }

    static String removeFriend(int userID) throws ProtocolException {
        return sh.execute(Session.Command.REMOVEFRIEND, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : userId:Integer
        return value(s)         : none
        potential status codes  : USER_NOT_FOUND
         */
    }

    static String block(int userID) throws ProtocolException {
        return sh.execute(Session.Command.BLOCK, userID);

        /*
        required state          : AUTHENTICATED
        parameters              : userId:Integer
        return value(s)         : none
        potential status codes  : USER_NOT_FOUND
         */
    }

    @SuppressWarnings("rawtypes")
    static String sendMessage(int channelID, byte[] data, Enum dataType) throws ProtocolException {
        return sh.execute(Session.Command.SENDMESSAGE, channelID, data, dataType);

        /*
        required state          : AUTHENTICATED
        parameters              : channelId:Integer, data:Byte[], dataType:DataType:Enum
        return value(s)         : none
        potential status codes  : CHANNEL_NOT_FOUND, MESSAGE_TOO_LONG
         */
    }

    static void quit() throws ProtocolException {
        sh.execute(Session.Command.QUIT);
        System.exit(0);

        /*
        required state          : CONNECTED
        parameters              : none
        return value(s)         : none
        potential status codes  : none
         */
    }

}
