/*
this class represents Users List
support functionalities to add user and get list of all users

 */
package client.gui;

import client.ProtocolException;
import client.ServerHandler;
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
import java.util.Objects;

public class SignInController {

    //data members
    @FXML private Parent fxml;
    @FXML private TextField userEmail;
    @FXML private TextField userPassword;
    @FXML private Button signIn;

    @FXML
    private void openMainScreen(ActionEvent event){
        try {
            Alert a = new Alert(Alert.AlertType.ERROR);
            String response = ServerHandler.sh.login(userEmail.getText(), userPassword.getText());

            if (response.equals(ProtocolException.Status.OK.toString())) {

                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/ChatScreen.fxml")));

                Stage stage = new Stage();
                stage.setTitle("Home");

                Scene scene = new Scene(fxml);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                fxml.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();

            } else if (ServerHandler.sh.login(userEmail.getText(), userPassword.getText()).equals(ProtocolException.Status.EMAIL_NOT_REGISTERED.toString())) {

                a.setTitle("E-Mail nicht registriert");
                a.setContentText("E-Mail nicht registriert! Bitte erstelle zuerst einen Account.");
                a.show();

            } else if (response.equals(ProtocolException.Status.PASSWORD_INVALID.toString())) {

                a.setTitle("Passwort falsch");
                a.setContentText("Passwort falsch! Bitte probiere es erneut.");
                a.show();

            } else {
                throw new ProtocolException.UnknownException(response);
            }
        } catch (IOException | ProtocolException e) {
            e.printStackTrace();
        }
    }
}
