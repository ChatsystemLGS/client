package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon; //* not an error if displayed so

public class UserInfoController implements Initializable {

    //data members
    @FXML private Circle userProfile;

    @FXML private TextField userName;
    @FXML private TextField userEmail;
    @FXML private TextField userPassword;
    @FXML private FontAwesomeIcon back; //* not an error if displayed so


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //setting event on  back button
        back.setOnMouseClicked(e->{ //* not an error if displayed so
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/ChatScreen.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Profile");
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            root.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        });
    }

    //this function will put details into fields of user which is logged in
    public void configure(String userName, String userEmail, String userProfileImage){
        userProfile.setFill(new ImagePattern(new Image(userProfileImage)));
        this.userName.setText(userName);
        this.userEmail.setText(userEmail);

    }

    @FXML
    private void update(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erfolgreich aktualisiert");
        alert.showAndWait();
    }

}
