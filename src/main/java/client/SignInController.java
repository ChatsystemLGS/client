/*
this class represents Users List
support functionalities to add user and get list of all users

 */
package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class SignInController {

    //data members
    @FXML private Parent fxml;

    @FXML private TextField userEmail;
    @FXML private TextField userPassword;
    @FXML private Button signIn;

    //this function will first check for user's valid login credentials, and then if they are ok
    //it will open main chat window
    @FXML
    private void openMainScreen(ActionEvent event) {
        try {
            Alert a = new Alert(Alert.AlertType.ERROR);
            if (login()) { //if user is not authenticated, we will show alert else main Screen
                fxml = FXMLLoader.load(getClass().getResource("/ChatScreen.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Home");
                Scene scene = new Scene(fxml);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                fxml.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                a.setTitle("Falsche E-Mail oder Passwort");
                a.setContentText("Falsche E-Mail oder Passwort. Bitte probiere es erneut.");
                a.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method will verify credentials and return true or false
    private boolean login() {
        if (userEmail.getText().equals("mary@gmail.com") && (userPassword.getText().equals("12345"))) { // TODO: let server validate
            return true;
        } else {
            return false;
        }
    }

}
