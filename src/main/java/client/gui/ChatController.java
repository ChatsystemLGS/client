package client.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    // data members
    @FXML private Circle userProfile;
    @FXML private Circle userSelectedProfile;

    @FXML private VBox scrollPane_inner;
    @FXML private VBox msgContainer;

    @FXML private Label selectedUserName;
    @FXML private TextField msgField;

    private UsersList users = new UsersList();
    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
    private String userProfileImage = "/6.jpg"; // TODO: get image from db
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set profile pic of user who is logged in
        userProfile.setFill(new ImagePattern(new Image(userProfileImage)));

        //selecting picture of user whose chat is opened
        userSelectedProfile.setFill(new ImagePattern(new Image(users.getUsers().get(0).getUserProfileImage())));

        //dynamically populating users on screen
        for(User user: users.getUsers()){
            scrollPane_inner.getChildren().add(createCard(user.getUserName(), "Message", user.getUserProfileImage()));
       }

        //adding action listener on User Image
        userProfile.setOnMouseClicked(e->{
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/userInfo.fxml"));
                root = loader.load();

                //we are sending profile data as arguments to controller
                UserInfoController userInfoController = loader.getController();
                userInfoController.configure(selectedUserName.getText(), users.getUsers().get(0).getUserEmail(), "/6.jpg");
                Stage stage = new Stage();
                stage.setTitle("Profile");
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                try { scene.getStylesheets().add("style.css"); } catch (Exception ignored) {} // TODO: figure out why
                stage.setScene(scene);
                stage.show();
                ((Node)(e.getSource())).getScene().getWindow().hide();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        // adding action listener for msg Field
        // msg will be send by pressing enter key
        msgField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                msgContainer.getChildren().add(msgCard());
                msgField.setText("");
            }
        } );


    }

    //this function will generate Msg Card and return
    private HBox msgCard() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(5,0,0,5));
        hbox.setSpacing(10);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.getStyleClass().add("chatLabel");
        vbox.setAlignment(Pos.TOP_LEFT);

        Circle userIcon = new Circle(25);
        userIcon.setFill(new ImagePattern(new Image("/6.jpg")));

        Label msg = new Label(msgField.getText());
        msg.setFont(new Font("Arial", 16));
        msg.setTextFill(Color.BLACK);

        String dateString = dateFormat.format(new Date()).toString();
        Label timeLabel = new Label(dateString);
        timeLabel.setFont(new Font("Arial", 12));
        timeLabel.setTextFill(Color.BLACK);
        timeLabel.setContentDisplay(ContentDisplay.RIGHT);
        timeLabel.setPadding(new Insets(5,0,0,50));
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        vbox.getChildren().addAll(msg, timeLabel);
        hbox.getChildren().addAll(userIcon, vbox);
        return hbox;
    }


    //this helper function will create user Card, which shows on left panel of screen
    private HBox createCard(String un, String lc, String img){
        HBox userCard = new HBox();

        //styling user card
        userCard.setAlignment(Pos.CENTER_LEFT);
        userCard.setPrefSize(261, 61);
        userCard.setMaxSize(261, 61);
        VBox.setMargin(userCard, new Insets(5,0,5,0));
        userCard.setPadding(new Insets(0,20,0,20));

        Circle profileImage = new Circle(25);
        profileImage.setFill(new ImagePattern(new Image(img)));

        //now we will add action listener to handle clicks on user Card
        userCard.setOnMouseClicked(e->{
            userSelectedProfile.setFill(new ImagePattern(new Image(img)));
            selectedUserName.setText(un);
            msgContainer.getChildren().removeAll();
            msgContainer.getChildren().setAll();
        });

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(0,0,0,10));
        vbox.setPrefSize(178,61);

        Label userName = new Label(un);
        userName.setFont(new Font("Arial", 16));
        userName.setTextFill(Color.WHITE);
        
        Label lastChat = new Label(lc);
        vbox.getChildren().addAll(userName, lastChat);
        userCard.getChildren().addAll(profileImage, vbox);
        return userCard;
    }
}
