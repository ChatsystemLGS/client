/*

This Class represents User. His/her characteristics and behaviours
This class overrides equals method, in order to check wheter user having
same email already exists or not.

 */
package client.gui;

import java.util.Objects;

public class User {
    //data members
    private String userName;
    private String userEmail;
    private String latestMessage;
    private String userPassword;
    private String userProfileImage = "default.png"; // TODO: image from db

    public User(String userName, String userEmail, String userPassword, String userProfileImage) { // TODO: need password? & email?
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userProfileImage = userProfileImage;
    }

    public User(String userName, String latestMessage, String userProfileImage) {
        this.userName = userName;
        this.latestMessage = latestMessage;
        this.userProfileImage = userProfileImage;
    }

    //setters and getters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, latestMessage, userProfileImage);
    } 
}
