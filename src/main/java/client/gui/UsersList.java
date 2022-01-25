package client.gui;

import client.ProtocolException;
import client.ServerHandler;
import client.db.Channel;
import client.db.Message;
import client.interfaces.GuiUpdateInterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UsersList {

    private ArrayList<User> users;

    public UsersList() {
        users = new ArrayList<>();

        try {
            getChannels();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }


    private void getChannels() throws ProtocolException {
        String response = GuiUpdateInterface.getChannels();
        ArrayList<Channel> channels = parseArray(response);

        assert channels != null;
        for (Channel c : channels) {

            if (c.getType().equals(Channel.Type.DM)) {
                String latestMessage = "";

                try {
                    latestMessage = c.getLatestMessage().getDataType().equals(Message.DataType.TEXT) ? Arrays.toString(c.getLatestMessage().getData()) : "Datei";
                    System.out.println(15151515);
                } catch (NullPointerException ignored) {
                    // latestMessage is null -> remains ""
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                User user = getUserForDM(c.getId(), latestMessage);
                addUser(ServerHandler.sh.base64toString(user.getUserName()), latestMessage, user.getUserProfileImage());

            } else { // assume it is public/private channel
                String channelName = ServerHandler.sh.base64toString(c.getName());
                String latestMessage = "";

                try {
                    latestMessage = c.getLatestMessage().getDataType().equals(Message.DataType.TEXT) ? Arrays.toString(c.getLatestMessage().getData()) : "Datei";
                } catch (NullPointerException ignored) {
                    // latestMessage is null -> remains ""
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                addUser(channelName, latestMessage, "default.png"); // TODO: get group image from database
            }
        }
    }

    private User getUserForDM(int channelID, String latestMessage) throws ProtocolException {
        int currentUserID = SignInController.currentUserID;
        String response = GuiUpdateInterface.getChannelMembers(channelID);

        if (response.equals(ProtocolException.Status.CHANNEL_NOT_FOUND.toString())) {
            throw new ProtocolException.ChannelNotFoundException();
        } else {
            ArrayList<String[]> users = getUsersFromResponse(response);
            assert users != null;

            if (Integer.parseInt(users.get(0)[0]) == (currentUserID)) {
                return new User(users.get(1)[1], latestMessage, users.get(1)[2]);
            } else {
                return new User(users.get(0)[1], latestMessage, users.get(0)[2]);
            }
        }
    }

    private ArrayList<String[]> getUsersFromResponse(String response) {
        if (!response.contentEquals("[]")) {
            String[] arr = trim(response);

            // Convert String[] to users ArrayList
            ArrayList<String[]> users = new ArrayList<>();
            for (String string : arr) {
                String[] arrNew = string.split(" ");
                String[] user = {arrNew[0], arrNew[1], "default.png"}; // ID, name, profileImage
                users.add(user); // TODO: image from DB
            }
            return users;
        }
        return null;
    }

    private ArrayList<Channel> parseArray(String response) throws ProtocolException {
        if (!response.contentEquals("[]")) {
            String[] arr = trim(response);

            // Convert String[] to channels ArrayList
            ArrayList<Channel> channels = new ArrayList<>();
            for (String s : arr) {
                String[] temp = s.split(" ");
                channels.add(new Channel(Integer.parseInt(temp[0]), getChannelTypeFromString(temp[1]), temp[2]));
            }
            return channels;
        }
        return null;
    }

    private String[] trim(String response) {
        response = response.split(" ", 2)[1];
        response = response.replace("[", "").replace("]", "");
        return response.split(",");
    }

    private Channel.Type getChannelTypeFromString(String channel) throws ProtocolException {
        return switch (channel) {
            case "DM" -> Channel.Type.DM;
            case "PRIVATE_GROUP" -> Channel.Type.PRIVATE_GROUP;
            case "PUBLIC_GROUP" -> Channel.Type.PUBLIC_GROUP;
            default -> throw ProtocolException.getException("");
        };
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //you can add new users in list using this method
    public void addUser(String userName, String userEmail, String userPassword, String userProfileImage) {
        if (users == null) {
            System.out.println("Please Initialize Array List");
        } else {
            User newUser = new User(userName, userEmail, userPassword, userProfileImage);
            if (!exists(newUser)) {
                users.add(new User(userName, userEmail, userPassword, userProfileImage));
            } else {
                JOptionPane.showMessageDialog(null, "Nutzer existiert bereits");
            }
        }
    }

    public void addUser(String userName, String latestMessage, String userProfileImage) {
        if (users == null) {
            System.out.println("Please Initialize Array List");
        } else {
            User newUser = new User(userName, latestMessage, userProfileImage);
            if (!exists(newUser)) {
                users.add(new User(userName, latestMessage, userProfileImage));
            } else {
                JOptionPane.showMessageDialog(null, "1 Nutzer existiert bereits");
            }
        }
    }

    //method override
    public void addUser(User user) {
        if (!exists(user)) {
            this.users.add(user);
        }
    }

    @SuppressWarnings("all")
    public boolean exists(User user) {
        for (User value : users) {
            if (value.equals(user)) return true;
        }
        return false;
    }
}
