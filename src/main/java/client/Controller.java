package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Circle userProfile;
    @FXML
    private Circle userProfile1;
    @FXML
    private Circle userProfile2;
    @FXML
    private Circle userProfile3;
    @FXML
    private Circle userProfile4;
    @FXML
    private Circle userProfile5;
    @FXML
    private Circle userProfile7;
    @FXML
    private Circle userProfile8;
    @FXML
    private Circle userProfile9;
    @FXML
    private Circle userProfile10;
    @FXML
    private Circle userSelectedProfile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("resources/download.jpg");
        Image image1 = new Image("resources/1.jpg");
        Image image2 = new Image("resources/2.jpg");
        Image image3 = new Image("resources/3.jpg");
        Image image4 = new Image("resources/4.jpg");
        Image image5 = new Image("resources/5.jpg");

        userProfile.setFill(new ImagePattern(image));
        userProfile.setOnMouseClicked(e->{
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("userInfo.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Profile");
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                scene.getStylesheets().add("resources/style.css");
                stage.setScene(scene);
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(e.getSource())).getScene().getWindow().hide();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        userProfile1.setFill(new ImagePattern(image1));
        userProfile2.setFill(new ImagePattern(image2));
        userProfile3.setFill(new ImagePattern(image3));
        userProfile4.setFill(new ImagePattern(image4));
        userProfile5.setFill(new ImagePattern(image5));
        userProfile7.setFill(new ImagePattern(image5));
        userProfile8.setFill(new ImagePattern(image2));
        userProfile9.setFill(new ImagePattern(image5));
        userProfile10.setFill(new ImagePattern(image2));

        userSelectedProfile.setFill(new ImagePattern(image5));


    }
}
