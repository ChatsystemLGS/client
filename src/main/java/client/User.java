/*

This Class represents User. His/her characteristics and behaviours
This class overrides equals method, in order to check wheter user having
same email already exists or not.

 */
package client;

import java.util.Objects;

public class User {
    //data members
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userProfileImage = "/tony.jpg"; // TODO: image from db

    //constructor
    public User(String userName, String userEmail, String userPassword, String userProfileImage){ // TODO: some
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userProfileImage = userProfileImage;
    }

    //setters and getters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    //overriding equals method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userEmail, user.userEmail);

    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userEmail, userPassword, userProfileImage);
    }

    // public static BufferedImage getImageFromBytes(byte[] data) throws Exception {
    //     // BufferedImage bImage = ImageIO.read(new File("client.jpg"));
    //     // ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //     // ImageIO.write(bImage, "jpg", bos );

    //     // get data from db
    //     ByteArrayInputStream bais = new ByteArrayInputStream(data);
    //     BufferedImage image = ImageIO.read(bais);
    //     // ImageIO.write(bImage2, "jpg", new File("output.jpg") );
    //     return image;
    //  }  
}
