package client.gui;

import client.ProtocolException;
import client.ServerHandler;
import client.interfaces.GuiInterface;
import client.interfaces.GuiUpdateInterface;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
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
    @FXML private FontAwesomeIcon minimize;
    @FXML private FontAwesomeIcon close;
    @FXML private AnchorPane rootPane;
    @FXML private FontAwesomeIcon addUser;
    @FXML HBox selectedUserChat;
    @FXML HBox btnPanel;
    @FXML private TextField searchUser;

    // members specific to class
    private UsersList users = new UsersList();
    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
    private String userProfileImage = "default.png"; // TODO: DB
    private String selectedUserImage = "default.png"; // TODO: DB
    Rectangle2D screenBounds;

    // TODO: DB
    private String[] messages = {"Hi I am Jack!", "Hello how are you", "I am fine", "Hi Bro",
            "I am going Outside", "Well Its Ok", "Demonstration Purpose Only", "Java is good language", "I really Like this"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rootPane.setStyle(
                "-fx-background-image:url('background.jpg');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;"
        );
        screenBounds = Screen.getPrimary().getBounds();

        // set profile pic of user who is logged in
        userProfile.setFill(new ImagePattern(new Image(userProfileImage))); // TODO: DB

        // selecting picture of user whose chat is opened
        userSelectedProfile.setFill(new ImagePattern(new Image(users.getUsers().get(0).getUserProfileImage())));

        // dynamically populating users on screen
        for (User user : users.getUsers()) {
            // TODO: get latest message from DB or show note
            scrollPane_inner.getChildren().add(createCard(user.getUserName(), "Message", user.getUserProfileImage()));
        }

        // adding action listener on User Image
        userProfile.setOnMouseClicked(e -> {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/userInfo.fxml"));
                root = loader.load();

                // we are sending profile data as arguments to controller
                UserInfoController userInfoController = loader.getController();
                userInfoController.configure(selectedUserName.getText(), users.getUsers().get(0).getUserEmail(), "default.png");

                Stage stage = new Stage();
                stage.setTitle("Profile");

                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
                ((Node) (e.getSource())).getScene().getWindow().hide();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        // adding action listener for msg Field
        // msg will be sent by pressing enter key
        msgField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                msgContainer.getChildren().add(msgCard_R());
                msgContainer.getChildren().add(msgCard_L()); // TODO: remove
                msgField.setText("");
            }
        });

        // adding event listener on minimize button
        minimize.setOnMouseClicked(e -> {
            Stage stage = (Stage) ((FontAwesomeIcon) e.getSource()).getScene().getWindow();
            stage.setIconified(true);
        });


        // adding event listener on close button
        close.setOnMouseClicked(e -> {
            Stage stage = (Stage) ((FontAwesomeIcon) e.getSource()).getScene().getWindow();
            stage.close();
            try {
                GuiInterface.quit();
            } catch (ProtocolException ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        });

        // adding event listener to new Friend button
        addUser.setOnMouseClicked(e -> {
            try {
                String userEmail = searchUser.getText();
                String response = GuiUpdateInterface.getUserIdFromEmail(userEmail);
                String[] responseArr = response.split(" ");
                int userID = Integer.parseInt(responseArr[1]);
                String userName = ServerHandler.sh.base64toString(responseArr[2]);

                // try adding him as friend
                GuiInterface.addFriend(userID);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Freund hinzugefügt");
                alert.setContentText("Glückwunsch! Du bist jetzt mit " + userName + " befreundet.");
                alert.showAndWait();
            } catch (ProtocolException ex) {
                ex.printStackTrace();
            }
        });
    }

    // this function will generate Msg Card and return
    private HBox msgCard_L() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(5, 0, 0, 5));
        hbox.setSpacing(10);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getStyleClass().add("chatLabel");
        vbox.setAlignment(Pos.TOP_LEFT);

        Circle userIcon = new Circle(25);
        userIcon.setFill(new ImagePattern(new Image("default.png"))); // left

        Label msg = new Label(msgField.getText());
        msg.setFont(new Font("Arial", 16));
        msg.setTextFill(Color.BLACK);

        String dateString = dateFormat.format(new Date()).toString();
        Label timeLabel = new Label(dateString);
        timeLabel.setFont(new Font("Arial", 12));
        timeLabel.setTextFill(Color.BLACK);
        timeLabel.setContentDisplay(ContentDisplay.RIGHT);
        timeLabel.setPadding(new Insets(5, 0, 0, 50));
        timeLabel.setAlignment(Pos.CENTER_RIGHT);

        vbox.getChildren().addAll(msg, timeLabel);
        hbox.getChildren().addAll(userIcon, vbox);

        return hbox;
    }

    // this will generate message card to the right of screen
    private HBox msgCard_R() {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(5, 0, 0, 5));
        hbox.setSpacing(10);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getStyleClass().add("chatLabelR");
        vbox.setAlignment(Pos.TOP_LEFT);
        Circle userIcon = new Circle(25);
        userIcon.setFill(new ImagePattern(new Image(selectedUserImage)));
        
        // lets generate random message from the list we have defined on top
        int index = (int) (Math.random() * (messages.length));
        Label msg = new Label(messages[index]);
        msg.setFont(new Font("Arial", 16));
        msg.setTextFill(Color.BLACK);
        msg.setPadding(new Insets(5, 0, 0, 50));
        String dateString = dateFormat.format(new Date()).toString();
        Label timeLabel = new Label(dateString);
        timeLabel.setFont(new Font("Arial", 12));
        timeLabel.setTextFill(Color.BLACK);
        timeLabel.setContentDisplay(ContentDisplay.RIGHT);
        timeLabel.setPadding(new Insets(5, 50, 0, 0));
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        vbox.getChildren().addAll(msg, timeLabel);
        hbox.getChildren().addAll(userIcon, vbox);
        hbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        return hbox;
    }

    // this helper function will create user Card, which shows on left panel of screen
    private HBox createCard(String un, String lc, String img) {
        HBox userCard = new HBox();

        // styling user card
        userCard.setAlignment(Pos.CENTER_LEFT);
        userCard.setPrefSize(261, 61);
        userCard.setMaxSize(261, 61);
        VBox.setMargin(userCard, new Insets(5, 0, 5, 0));
        userCard.setPadding(new Insets(0, 20, 0, 20));
        Circle profileImage = new Circle(25);
        profileImage.setFill(new ImagePattern(new Image(img)));

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(0, 0, 0, 10));
        vbox.setPrefSize(178, 61);

        Label userName = new Label(un);
        // now we will add action listener to handle clicks on user Card
        userName.setOnMouseClicked(e -> {
            userSelectedProfile.setFill(new ImagePattern(new Image(img)));
            selectedUserImage = img;
            selectedUserName.setText(un);
            msgContainer.getChildren().removeAll();
            msgContainer.getChildren().setAll();
        });
        userName.setFont(new Font("Arial", 16));
        userName.setTextFill(Color.WHITE);

        Label lastChat = new Label(lc);
        vbox.getChildren().addAll(userName, lastChat);

        FontAwesomeIcon ban = new FontAwesomeIcon();
        ban.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Friend");
            alert.setContentText("Are you sure to block " + userName.getText());
            alert.showAndWait();
        });
        ban.setGlyphName("BAN");
        ban.setFill(Color.WHITE);
        ban.setSize("1.5em");
        userCard.getChildren().addAll(profileImage, vbox, ban);
        return userCard;
    }
}
