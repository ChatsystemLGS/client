package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @FXML
    private Circle userProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("resources/5.jpg");
        userProfile.setFill(new ImagePattern(image));
    }
}
